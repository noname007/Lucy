package com.lqcode.lucytv.tools;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.lqcode.lucytv.R;
import com.lqcode.lucytv.entity.ViewSize;

/**
 * Created by Administrator on 2016/9/7.
 */
public class UiTool {
    public static ViewSize getSizeByView(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        return new ViewSize(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public static void initSrl(SwipeRefreshLayout srl){
        srl.setColorSchemeResources(R.color.colorPrimaryDark,R.color.colorPrimaryDark, R.color.colorPrimaryDark,
                R.color.colorPrimaryDark);
    }
}
