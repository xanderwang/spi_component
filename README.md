[TOC]

# 一个基于 SPI 的 Android 组件化框架

详细的源码请移步 [github:spi_component](https://github.com/XanderWang/spi_component)

首先，我们思考下什么是组件化，什么是插件化？什么时候我们用组件化，什么时候我们用插件化？

## 什么是组件化，什么是插件化。

所谓的组件化就是将整个项目拆分为几个组件，单个组件高内聚，组件间低耦合，这些组件拼到一起，编译成一个目标 `app` 。

所谓的插件化就是将整个项目拆分为几个模块，分别打包编译，在主 `app` 需要用到某个模块的时候按需加载这些模块。

## 什么时候我们用组件化，什么时候我们用插件化

这是一个策略问题，用何种方式需要根据实际情况来。

刚刚说了，组件化是将项目拆分为几个组件，单个组件高内聚，组件间低耦合。所以复用会比较方便，如果公司产品多可以考虑这种方式。

另外，组件化框架一般会允许组件单独编译，单独调试，由于组件一般不会太大，所以编译调试单个组件的时间较整体编译会少很多。而且组件化方式编译的 app 是常规模式编译的，可以较好的适配新的 Android 版本，相较插件化更稳定。

插件化是将整个项目拆分为几个模块，和组件化类似，有相似的优势，但是一般插件化方案都涉及 Hook 技术，而这些技术随着 Android 版本迭代，可能会被封杀，存在一些适配的风险，可能会不稳定。

随着我们的业务发展，我们的主工程越来越复杂，不得不考虑组件化和插件化来简化我们的开发工作，考虑到稳定性，我们决定改造我们的工程，后续切换到组件化开发。

## 组件化的一些思考

### 内部需求

在改造的时候，我们采集了一下内部对于组件化需求：

1. 学习成本低，希望比较容易上手(接入简单)。
2. 现有的代码改动少，调用方式最好不变。
3. 组件之间互相调用(希望可以直接使用其他组件的资源，相对显式打开其他组件的页面等)方便、高效。
4. 希望不要引入太多无关的代码。
5. 希望新增组件的方式简单点。

### 开源方案待解决问题

参考一些开源的组件化方案，有很多借鉴的地方，但是感觉还是有些不是很符合我们的需求，比如：

1. 组件间的调用不是很方便，Activity 的跳转还好，但是涉及到 Fragment 的调用的时候，
就不是很方便了，基本无解。
2. 有些组件的初始化需要放到 Application 里面，或者需要 Application 里面的一些方法，
这些都不是很适合。
3. 新增组件相对麻烦，需要修改编译脚本，相对不友好。

## 架构介绍

### 模块介绍
鉴于以上的一些特点，我们希望可以在现有的基础上做一些优化，希望组件化开发更简单，更方便。

于是，我设想了这个基于 `SPI` 的组件化框架。框架的大致架构如下：

|模块名称 | 作用|
| :--: | :-- |
|:baselibs:imageloader  | 这个是基础的图片加载库，目前没有实际的实现，只是为了演示|
|:baselibs:net  | 这个是基础的网络框架库，目前没有实际的实现，只是为了演示|
|:baselibs:compbase  | 这个是基础的框架库，抽象了组件基本的功能，一个对外的接口，一个类似 Application 的接口。|
|:baselibs:compX | 这个是组件库，抽象了组件具体的功能|
|:baselibs:core  | 框架的核心，就是 `app` 的骨架，统一管理各个组件对外暴露的部分。可通过 `compXXX` 方式暴露出去。而且比较灵活， `Fragment` 资源图片等都可以暴露。 |
|:components:compXImpl | 某个组件，依赖 `:baselibs:compbase`、`:baselibs:core`、`:baselibs:compX` ，实现 `:baselibs:compX` 里面这个组件对外暴露的业务逻辑等。|
|:apps:app | `app` 的承载容器，就是对外发布的时候，用这个项目来编译，包含所有的组件 |
|:apps:compx-alone | 组件单独调试编译入口，就是编译出来的 app 只有这一个组件实现了。|

`:baselibs:compX` 模块定义了各种组件承担的业务和对外暴露的一些资源等，主要是通过接口暴露。同时提供各个模块的默认实现。

`:baselibs:core` 模块整合了各个组件对外暴露的部分。

`:components:compXImpl` 模块是一个组件的实现，实现的是 `:baselibs:compX` 模块里面定义的的组件.
模块。

`:apps:app` 模块，就是我们对外发布的 `app` ，把所有的组件放到一起编译。 依赖 `:baselibs:core` 和所有的 `:components:compXImpl` 
模块。

`:app/compx-alone` 某个组件的 `app` ，即依赖 `:baselibs:core` 和某个 `:components:compXImpl` 模块，
方便单独调试某个模块。

### 组件是如何工作的

首先，一个组件需要在 `:baselibs:compX` 模块里面定义，我们暂用 `ICompXService` 来表示，其实就是一个接口，定义了这个组件对外暴露的东西，比如可以获取某个值(getXXX)，可以获取一个 Fragment (getXXXFragment)。同时提供默认实现。

然后，我们需要新建一个 `:components:compXImpl` 模块，依赖 `:baselibs:compX` 模块，然后实现 `:baselibs:compX` 模块里面的 `ICompXService` 接口，我们记为 `CompXServiceImpl` ，这样我们就有了一个组件。

然后，在 `:baselibs:core` 模块的 `AppTool` 类里面注册这个 `ICompXService` ，这样我们的 `:baselibs:core` 里面就拿到了这个组件对外的部分，当 `AppTool` 类被加载的时候，通过 `SPI` ，我们就可以把 `:components:compXImpl` 里面实现的 
`CompXServiceImpl` 和刚刚 `AppTool` 里定义的 `ICompXService` 绑定起来。( `SPI` 的原理大家可以参考
[Java SPI机制原理和使用场景](https://blog.csdn.net/codingtu/article/details/79004657))

然后 `:apps:app` 模块都依赖 `:baselibs:core` 和 `:components:compXImpl` 模块，然后通过 `AppTool` 类，我们可以就可以使用 `CompXServiceImpl` 了。

组件的原理大概就是这样的。
![架构图](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdzfpd07guj31260rgmz7.jpg)

## 疑难点是如何解决的

### 1 如何直接使用 Fragment 或者其他资源

 `:components:compXImpl` 通过实现 `:baselibs:compX` 里面的 `ICompXService` 定义的对外的接口，可以暴露自己的资源和能力。比如有 `Fragment` 需要对外暴露，直接在 `ICompXService`  里面定义一个方法暴露就好了，然后实现 ~的时候，暴露出需要暴露的 `Fragment` 就好了，其他组件通过 `AppTool` 类拿到 `ICompXService`  时候，就可以通过接口暴露的方法使用 `Fragment` 了。

### 2 组件的初始化需要放到 Application 里面

有时候，我们需要在 `Application` 初始化一些资源，一般情况下，我们的做法是，在主 app 里面的 `Application` 里面添加初始化的代码，然后在组件 app 的 `Application` 里面添加初始化的代码。
这样有一个问题就是如果需要改动代码，主 App 和组件的 App 里面都需要修改，同时，主 App 的 `Application` 代码是比较复杂的，因为主 App 里面包含所有的组件，在修改主 App 的 `Application` 的风险是比较大的。

为了解决这个问题，我们的做法是抽象出来一个类 `ICompApplication` ，这个类和 `Application` 类类似，把主要的方法，比如 `onCreate` 方法提取出来，通过 `ICompXService` 的 `getCompApplication` 方法，在初始化 `AppTool` 的时候，就可以拿到组件的 `XApplication` ，然后，在 `App` 定义的 `Application` 里面的回调方法里面同步调用组件的 `ICompApplication` 里面对于的方法。

通过这种方法，我们可以仅在组件里面处理自己的初始化逻辑就好了。

可能碰到的情况是组件的初始化有先有后，有的初始化依赖某个组件初始化完成了才可以初始化。这个问题暂时考虑会在 `ICompApplication` 里面通过一个优先级的参数来表示这个组件初始化的顺序，在 App 的 `Application` 方法回调的时候根据优先级来依次回调 `ICompApplication` 里面的方法。

### 3 新增一个组件

新增一个组件的话，需要做如下的事情，
1. 新建一个 `:baselibs:compX` 库，定义新的组件需要对外的部分，同时提供默认实现，并让 `SPI` 可以加载到这个默认实现。 
2. 新建一个 `:components:compXImpl` 库，实现刚刚的组件库，同时让 `SPI` 可以识别到这库。 
3. 在 `:baselibs:core` 里面注册这个组件对外暴露的 `CompXService`。
4. `:apps:app` 模块或者组件独立入口 `:apps:compx-alone` 模块依赖这个新建的 `library`。

经过上面四步，新增一个组件完成了，中间不需要特别修改编译脚本，只需要修改一些引用库之间的关系就可以使用了。感觉还是很方便的。

## 一些需要注意的事项

1. `:baselibs:core`  模块会通过  `SPI`  来加载组件的实现，也就是组件  `library`  里面实现 `ICompXService` 接口的类，所以组件  `library`  里面要按照 `SPI` 的要求新建入口文件。





