package com.xander.comp2impl;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.xander.comp.core.AppTool;

/**
 * @author xander
 */
public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void openComp2(View view) {
    AppTool.comp2Service().openComp2Activity(this);
  }
}
