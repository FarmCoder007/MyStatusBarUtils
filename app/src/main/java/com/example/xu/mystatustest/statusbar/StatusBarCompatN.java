package com.example.xu.mystatustest.statusbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
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
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
                Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
                field.setAccessible(true);
                field.setInt(activity.getWindow().getDecorView(), Color.TRANSPARENT);  //改为透明
            } catch (Exception e) {
            }
        }
    }
}
