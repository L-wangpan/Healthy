package com.healthy.wp.Messure.View;

import android.content.Context;

/**
 * Created by wan_g_000 on 2016/5/1.
 */
public class DipTopX {
    public static int dipTopx(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
