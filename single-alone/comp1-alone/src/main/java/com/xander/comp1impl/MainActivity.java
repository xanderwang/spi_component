package com.xander.comp1impl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.xander.comp.core.AppTool;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void openComp1(View view) {
    AppTool.getComp1Service().openComp1(this);
  }
}
