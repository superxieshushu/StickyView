package com.saltedfish.stickyview;

import android.content.Context;

/**
 * Created by SaltedFish on 2018/3/11.
 * 简单描述作用
 */

public class Utils {

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
