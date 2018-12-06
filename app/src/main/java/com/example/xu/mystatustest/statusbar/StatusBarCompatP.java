package com.example.xu.mystatustest.statusbar;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author : xu
 * date : 2018/12/6 13:34
 * description : 谷歌原生适配刘海屏  9.0以上
 * sdk.version>=28   9.0以上
 */
@TargetApi(Build.VERSION_CODES.P)
public class StatusBarCompatP {
    /**
     * layoutInDisplayCutoutMode 适配方案
     * LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT：默认情况下，全屏窗口不会使用到刘海区域，非全屏窗口可正常使用刘海区域
     * LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER：窗口不允许和刘海屏重叠 [是不允许使用刘海屏区域]
     * LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES：
     * 加沉浸式状态栏 [带有状态栏]
     *
     * @param window
     */
    public static void setFullScreenWindowLayout(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            //设置页面全屏显示
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            //设置页面延伸到刘海区显示
            window.setAttributes(lp);
        }
    }

    /**
     *  设置全屏 状态栏消失
     */
    public static void setFullScreen(Window window) {
        View decorView = window.getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        int flags = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        systemUiVisibility |= flags;
        window.getDecorView().setSystemUiVisibility(systemUiVisibility);
    }
}
