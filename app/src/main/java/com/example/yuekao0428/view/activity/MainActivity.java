package com.example.yuekao0428.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.yuekao0428.R;
import com.example.yuekao0428.view.fragment.GouFragment;
import com.example.yuekao0428.view.fragment.HomeFragment;
import com.example.yuekao0428.view.fragment.MyFragment;
import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends AppCompatActivity {

    private BottomTabBar buttomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttomBar = findViewById(R.id.buttomBar);
        buttomBar.init(getSupportFragmentManager()).setImgSize(50,50)
                .setFontSize(10)
                .setTabPadding(4,6,10)
                .addTabItem("首页",R.drawable.ic_ac_unit_black_24dp,HomeFragment.class)
                .addTabItem("购物车",R.drawable.ic_ac_unit_black_24dp,GouFragment.class)
                .addTabItem("我的",R.drawable.ic_ac_unit_black_24dp,MyFragment.class);


    }
}
