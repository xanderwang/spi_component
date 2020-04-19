package com.xander.comp1impl;

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

  public void openComp1(View view) {
    AppTool.comp1Service().openComp1Activity(this);
  }
}
