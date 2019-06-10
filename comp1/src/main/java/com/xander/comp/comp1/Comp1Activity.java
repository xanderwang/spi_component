package com.xander.comp.comp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.xander.comp.core.AppTool;

public class Comp1Activity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_comp1);
  }

  public void openModule2(View view) {
    AppTool.getComp2Service().openModule2(this);
  }
}
