package com.xander.comp.server2impl;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xander.comp.core.AppTool;

/**
 * @author xander
 */
public class Comp2Activity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_comp2);
  }

  public void openComp1(View v) {
    AppTool.comp1Service().openComp1Activity(this);
  }
}
