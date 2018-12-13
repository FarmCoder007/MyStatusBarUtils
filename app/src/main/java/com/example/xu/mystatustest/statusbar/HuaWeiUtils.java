package com.example.xu.mystatustest.statusbar;

import android.content.Context;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 华为手机工具类
 */
public class HuaWeiUtils {
    private static final String TAG = "HuaWeiUtils";

    //显示内容到凹槽
    public static void setFullScreenWindowLayoutInDisplayCutout(Window window) {
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        try {
            Class layoutParamsExCls = Class.forName("com.huawei.android.view.LayoutParamsEx");
            Constructor con = layoutParamsExCls.getConstructor(WindowManager.LayoutParams.class);
            Object layoutParamsExObj = con.newInstance(layoutParams);
            Method method = layoutParamsExCls.getMethod("addHwFlags", int.class);
            method.invoke(layoutParamsExObj, 0x00010000);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | Fragment.InstantiationException | InvocationTargetException e) {
            Log.e("test", "hw notch screen flag api error");
        } catch (Exception e) {
            Log.e("test", "other Exception");
        }
    }

    public static void setNotFullScreenWindowLayoutInDisplayCutout(Window window) {
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        try {
            Class layoutParamsExCls = Class.forName("com.huawei.android.view.LayoutParamsEx");
            Constructor con = layoutParamsExCls.getConstructor(WindowManager.LayoutParams.class);
            Object layoutParamsExObj = con.newInstance(layoutParams);
            Method method = layoutParamsExCls.getMethod("clearHwFlags", int.class);
            method.invoke(layoutParamsExObj, 0x00010000);
        } catch (Exception e) {
            Log.e("test", "other Exception");
        }
    }

    /**
     * 是否设置了显示凹槽区域
     */
    public static boolean isDisplayNotch(Context context) {
        if (context == null)
            return false;
        return Settings.Secure.getInt(context.getContentResolver(), "display_notch_status", 0) == 0;
    }

    /**
     * 凹槽高度
     */
    public static int getNotchHeight(Context context) {
        if (context == null)
            return 0;
        int notchHeight = 0;
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            if (HwNotchSizeUtil != null) {
                Log.e(TAG, "华为带刘海");
                Method get = HwNotchSizeUtil.getMethod("getNotchSize");
                if (get != null) {
                    //华为刘海高度
                    notchHeight = ((int[]) get.invoke(HwNotchSizeUtil))[1];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notchHeight;
    }

    /**
     * 华为刘海屏    凹槽宽度
     */
    public static int getNotchWidth(Context context) {
        int notchWidth = 0;
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            if (HwNotchSizeUtil != null) {
                Log.e(TAG, "华为带刘海");
                Method get = HwNotchSizeUtil.getMethod("getNotchSize");
                if (get != null) {
                    //华为刘海宽度
                    notchWidth = ((int[]) get.invoke(HwNotchSizeUtil))[0];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notchWidth;
    }
}
