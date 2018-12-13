package com.example.xu.mystatustest.statusbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by xu on 2018/7/24.
 * sdk.version>=24   6.0以上
 */
@TargetApi(Build.VERSION_CODES.N)
public class StatusBarCompatN {
    /**
     * 6.0 以上透明状态栏 适配6.0以上
     *
     * @param activity
     */
    public static void transparentStatusBarN(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                // 6.0 以上设置字体变黑色
                Window window = activity.getWindow();
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                // 设置 状态栏透明   【适配6.0以上状态栏有蒙层】
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
                Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
                field.setAccessible(true);
                //改为透明
                field.setInt(activity.getWindow().getDecorView(), Color.TRANSPARENT);
            } catch (Exception e) {
            }
        }
    }
}
