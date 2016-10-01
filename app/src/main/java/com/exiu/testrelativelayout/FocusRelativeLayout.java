package com.exiu.testrelativelayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by 振辉 on 2016/10/1.
 */
public class FocusRelativeLayout extends RelativeLayout {

    private Context ctx;
    private int selfSizeWidth;
    private int selfSizeHeight;
    private ArrayList<FocusImageView> views;
    private int singleSizeWidth;
    private int singleSizeHeight;
    private int padding_10;
    private int padding_20;

    public FocusRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = context;
        initView();
    }

    public FocusRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ctx = context;
        initView();
    }

    public FocusRelativeLayout(Context context) {
        super(context);
        ctx = context;
        initView();
    }

    /**
     * 初始化 创建需要的ImageView
     * 左下角请求焦点
     */
    private void initView() {
        views = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            FocusImageView imageView;
            if (i == 1) {
                imageView = new CarouselImageView(ctx);
            } else {
                imageView = new FocusImageView(ctx);
            }
            // 动态创建的View需要手动设置id，不然没有id，getId的时候返回的都是-1
            // 通过ID判断当点击OK键时，焦点在哪儿
            imageView.setId(i);
            views.add(imageView);
            addView(imageView, i);
            if (i == 7) {
                imageView.requestFocus();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    /**
     * 测量本身 及所有子控件的尺寸
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 此控件宽高
        selfSizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        selfSizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        // 最小控件宽高
        singleSizeWidth = selfSizeWidth / 8;
        singleSizeHeight = selfSizeHeight / 6;

        padding_10 = DensityUtil.dip2px(ctx, 10f);
        padding_20 = DensityUtil.dip2px(ctx, 20f);

        // 测量所有的子view  的大小
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    /**
     * 分配所有子控件位置
     */
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        for (int i = 0; i < views.size(); i++) {
            addFocusView(i);
        }
    }

    /**
     * 添加View到此控件中
     *
     * @param position
     */
    private void addFocusView(int position) {
        switch (position) {
            case 0:
                views.get(0).layout(0, 0, DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                views.get(0).setImageResource(R.mipmap.ic_launcher);
                break;
            case 1:
                views.get(1).layout(DensityUtil.dip2px(ctx, singleSizeWidth * 2), 0, DensityUtil.dip2px(ctx, singleSizeWidth * 6), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
//                views.get(1).setImageResource(R.mipmap.ic_launcher);
                break;
            case 2:
                views.get(2).layout(DensityUtil.dip2px(ctx, singleSizeWidth * 6), 0, DensityUtil.dip2px(ctx, singleSizeWidth * 8), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                views.get(2).setImageResource(R.mipmap.ic_launcher);
                break;
            case 3:
                views.get(3).layout(0, DensityUtil.dip2px(ctx, singleSizeHeight * 3), DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 5));
                views.get(3).setImageResource(R.mipmap.ic_launcher);
                break;
            case 4:
                views.get(4).layout(DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 3), DensityUtil.dip2px(ctx, singleSizeWidth * 4), DensityUtil.dip2px(ctx, singleSizeHeight * 5));
                views.get(4).setImageResource(R.mipmap.ic_launcher);
                break;
            case 5:
                views.get(5).layout(DensityUtil.dip2px(ctx, singleSizeWidth * 4), DensityUtil.dip2px(ctx, singleSizeHeight * 3), DensityUtil.dip2px(ctx, singleSizeWidth * 6), DensityUtil.dip2px(ctx, singleSizeHeight * 5));
                views.get(5).setImageResource(R.mipmap.ic_launcher);
                break;
            case 6:
                views.get(6).layout(DensityUtil.dip2px(ctx, singleSizeWidth * 6), DensityUtil.dip2px(ctx, singleSizeHeight * 3), DensityUtil.dip2px(ctx, singleSizeWidth * 8), DensityUtil.dip2px(ctx, singleSizeHeight * 5));
                views.get(6).setImageResource(R.mipmap.ic_launcher);
                break;
            case 7:
                views.get(7).layout(0, DensityUtil.dip2px(ctx, singleSizeHeight * 5), DensityUtil.dip2px(ctx, singleSizeWidth), DensityUtil.dip2px(ctx, singleSizeHeight * 6));
                views.get(7).setImageResource(R.mipmap.ic_launcher);
                break;
            case 8:
                views.get(8).layout(DensityUtil.dip2px(ctx, singleSizeWidth), DensityUtil.dip2px(ctx, singleSizeHeight * 5), DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 6));
                views.get(8).setImageResource(R.mipmap.ic_launcher);
                break;
            case 9:
                views.get(9).layout(DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 5), DensityUtil.dip2px(ctx, singleSizeWidth * 3), DensityUtil.dip2px(ctx, singleSizeHeight * 6));
                views.get(9).setImageResource(R.mipmap.ic_launcher);
                break;
            case 10:
                views.get(10).layout(DensityUtil.dip2px(ctx, singleSizeWidth * 3), DensityUtil.dip2px(ctx, singleSizeHeight * 5), DensityUtil.dip2px(ctx, singleSizeWidth * 4), DensityUtil.dip2px(ctx, singleSizeHeight * 6));
                views.get(10).setImageResource(R.mipmap.ic_launcher);
                break;
            case 11:
                views.get(11).layout(DensityUtil.dip2px(ctx, singleSizeWidth * 4), DensityUtil.dip2px(ctx, singleSizeHeight * 5), DensityUtil.dip2px(ctx, singleSizeWidth * 5), DensityUtil.dip2px(ctx, singleSizeHeight * 6));
                views.get(11).setImageResource(R.mipmap.ic_launcher);
                break;
            case 12:
                views.get(12).layout(DensityUtil.dip2px(ctx, singleSizeWidth * 5), DensityUtil.dip2px(ctx, singleSizeHeight * 5), DensityUtil.dip2px(ctx, singleSizeWidth * 6), DensityUtil.dip2px(ctx, singleSizeHeight * 6));
                views.get(12).setImageResource(R.mipmap.ic_launcher);
                break;
            case 13:
                views.get(13).layout(DensityUtil.dip2px(ctx, singleSizeWidth * 6), DensityUtil.dip2px(ctx, singleSizeHeight * 5), DensityUtil.dip2px(ctx, singleSizeWidth * 7), DensityUtil.dip2px(ctx, singleSizeHeight * 6));
                views.get(13).setImageResource(R.mipmap.ic_launcher);
                break;
            case 14:
                views.get(14).layout(DensityUtil.dip2px(ctx, singleSizeWidth * 7), DensityUtil.dip2px(ctx, singleSizeHeight * 5), DensityUtil.dip2px(ctx, singleSizeWidth * 8), DensityUtil.dip2px(ctx, singleSizeHeight * 6));
                views.get(14).setImageResource(R.mipmap.ic_launcher);
                break;

        }
    }
}
