package com.example.xu.mystatustest;



import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.xu.mystatustest.adapter.TestAdapter;
import com.example.xu.mystatustest.statusbar.StatusBarCompat;

public class MainActivity extends FragmentActivity {
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarCompat.translucentStatusBar(this);
        initView();
    }

    private void initView() {
        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new TestAdapter(this));
    }
}
