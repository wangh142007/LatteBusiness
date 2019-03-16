package com.wh.festec.utils.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.wh.festec.app.Latte;

/**
 * 获取屏幕长度和宽度
 */
public final class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
