package com.example.xu.mystatustest.secondtest;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.xu.mystatustest.R;
import com.example.xu.mystatustest.statusbar.StatusBarCompat;
import com.example.xu.mystatustest.statusbar.StatusbarUtils;

/**
 * 沉浸式状态栏测试2
 */
public class OtherTestStatusActivity extends FragmentActivity {
    /**
     * 状态栏设置image替代padding
     */
    private ImageView mStatusbarImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_test_status);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBarIcon();
        initStatusBar(this);
    }

    /**
     * 设置沉浸状态栏后 自定义与状态栏等高的view
     */
    protected void setStatusBarIcon() {
        mStatusbarImage = findViewById(R.id.home_statusbar_image);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.height = StatusbarUtils.getStatusBarHeight(this);
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        mStatusbarImage.setLayoutParams(layoutParams);
        mStatusbarImage.setBackgroundColor(getResources().getColor(R.color.colorAccent));
//        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
//        viewGroup.addView(mStatusbarImage, 0);
    }

    /**
     * 设置状态栏高度
     */
    protected void setStatusbarSize() {
        if (mStatusbarImage != null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
            layoutParams.height = StatusbarUtils.getStatusBarHeight(this);
            mStatusbarImage.setLayoutParams(layoutParams);
        }
    }

    public void setStatusBarResource(int res) {
        if (mStatusbarImage != null) {
            mStatusbarImage.setBackgroundResource(res);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportFragmentManager()
                .addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                    @Override
                    public void onBackStackChanged() {
                        setStatusBar(OtherTestStatusActivity.this);
                    }
                });
    }

    /**
     * 初始化下状态栏
     *
     * @param activity
     */
    protected void initStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            setStatusBar(activity);
        } else {
            setStatusBarEnable(false);
        }
    }

    /**
     * 横竖屏切换处理
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setStatusBarEnable(false);
        } else {
            initStatusBar(this);
        }
    }

    /**
     * 隐藏透明状态栏
     */
    public void hideStatusBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setStatusBarEnable(false);
    }

    /**
     * 展示状态栏
     */
    public void showStatusBar() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setStatusBarEnable(true);
    }

    /**
     * 设置状态栏是否启用   控制view的显示隐藏
     *
     * @param enable
     */
    protected void setStatusBarEnable(boolean enable) {
        if (mStatusbarImage != null) {
            mStatusbarImage.setVisibility(enable ? View.VISIBLE : View.GONE);
        }
    }


    /**
     * 适配状态栏
     *
     * @param activity
     */
    protected void setStatusBar(Activity activity) {
        if (StatusbarUtils.MIUISetStatusBarLightMode(activity, false)) {
            setStatusBarEnable(true);
            setStatusbarSize();
        } else if (StatusbarUtils.FlymeSetStatusBarLightMode(activity, false)) {
            setStatusBarEnable(true);
            setStatusbarSize();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarCompat.translucentStatusBar(activity, false);
            setStatusBarEnable(true);
            setStatusbarSize();
        } else {
            setStatusBarEnable(false);
        }
    }
}
