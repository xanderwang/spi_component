package com.xander.comp.server1impl;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xander.comp.core.AppTool;


/**
 * @author xander
 */
public class Comp1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp1);
    }

    public void openComp2(View view) {
        AppTool.comp2Service().openComp2Activity(this);
    }
}
