package com.example.xu.mystatustest;


import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.xu.mystatustest.adapter.TestAdapter;
import com.example.xu.mystatustest.statusbar.StatusBarCompat;
import com.example.xu.mystatustest.statusbar.StatusBarCompatLollipop;

import java.lang.reflect.Field;

public class MainActivity extends FragmentActivity {
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.translucentStatusBar(this);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new TestAdapter(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportFragmentManager()
                .addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                    @Override
                    public void onBackStackChanged() {
                        StatusBarCompat.translucentStatusBar(MainActivity.this);
                    }
                });
    }

}
