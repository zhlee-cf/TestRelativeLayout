package com.exiu.testrelativelayout;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;

/**
 * 自定义的 子相对布局——底部
 * Created by 振辉 on 2016/10/2.
 */
public class BottomRelativeLayoutView extends RelativeLayoutView {
    public BottomRelativeLayoutView(Context context) {
        super(context);
    }

    public BottomRelativeLayoutView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public BottomRelativeLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    /**
     * 设置底部布局的子布局的属性  与 普通顶部和中部不同 所以重写了此方法
     */
    protected void setProperty() {
        textView.setBackgroundColor(Color.TRANSPARENT);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setPadding(0, 10, 0, 0);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        setBackgroundColor(Color.GREEN);
    }
}
