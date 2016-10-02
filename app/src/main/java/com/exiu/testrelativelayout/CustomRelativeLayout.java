package com.exiu.testrelativelayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 振辉 on 2016/10/1.
 */
public class CustomRelativeLayout extends RelativeLayout {

    private Context ctx;
    private int selfSizeWidth;
    private int selfSizeHeight;
    private ArrayList<View> views;
    private int singleSizeWidth;
    private int singleSizeHeight;
    private int padding_10;
    private int padding_20;

    public CustomRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = context;
        initView();
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ctx = context;
        initView();
    }

    public CustomRelativeLayout(Context context) {
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
            View view;
            if (i < 7 && i != 1) {
                view = new RelativeLayoutView(ctx);
            } else if (i == 1) {
                view = new CarouselRelativeLayoutView(ctx);
            } else {
                view = new FocusImageView(ctx);
            }
            // 动态创建的View需要手动设置id，不然没有id，getId的时候返回的都是-1
            // 通过ID判断当点击OK键时，焦点在哪儿
            view.setId(i);
            views.add(view);
            addView(view, i);
        }
        views.get(7).requestFocus();
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
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int j = 0; j < viewGroup.getChildCount(); j++) {
                    View v = viewGroup.getChildAt(j);
                    v.measure(widthMeasureSpec, heightMeasureSpec);
                }
            }
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
                RelativeLayoutView rlv_0 = (RelativeLayoutView) views.get(0);
                rlv_0.layout(0, 0, DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                ImageView img_0 = rlv_0.getImageView();
                img_0.layout(0, 0, DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                TextView tv_0 = rlv_0.getTextView();
                tv_0.layout(0, DensityUtil.dip2px(ctx, singleSizeHeight * 3 - tv_0.getHeight()), DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                tv_0.setTextColor(Color.WHITE);
                tv_0.setPadding(padding_10, padding_10, padding_10, padding_10);
                tv_0.setText("居中显示0");
                break;
            case 1:
                RelativeLayoutView rlv_1 = (RelativeLayoutView) views.get(1);
                rlv_1.layout(DensityUtil.dip2px(ctx, singleSizeWidth * 2), 0, DensityUtil.dip2px(ctx, singleSizeWidth * 6), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                ImageView img_1 = rlv_1.getImageView();
                img_1.layout(0, 0, DensityUtil.dip2px(ctx, singleSizeWidth * 4), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                TextView tv_1 = rlv_1.getTextView();
                tv_1.layout(0, DensityUtil.dip2px(ctx, singleSizeHeight * 3 - tv_1.getHeight()), DensityUtil.dip2px(ctx, singleSizeWidth * 4), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                tv_1.setTextColor(Color.WHITE);
                tv_1.setPadding(padding_10, padding_10, padding_10, padding_10);
//                tv_1.setText("居中显示1");
//                views.get(1).setImageResource(R.mipmap.ic_launcher);

                break;
            case 2:
                RelativeLayoutView rlv_2 = (RelativeLayoutView) views.get(2);
                rlv_2.layout(DensityUtil.dip2px(ctx, singleSizeWidth * 6), 0, DensityUtil.dip2px(ctx, singleSizeWidth * 8), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                ImageView img_2 = rlv_2.getImageView();
                img_2.layout(0, 0, DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                TextView tv_2 = rlv_2.getTextView();
                tv_2.layout(0, DensityUtil.dip2px(ctx, singleSizeHeight * 3 - tv_2.getHeight()), DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                tv_2.setTextColor(Color.WHITE);
                tv_2.setPadding(padding_10, padding_10, padding_10, padding_10);
                tv_2.setText("居中显示2");
                break;
            case 3:
                RelativeLayoutView rlv_3 = (RelativeLayoutView) views.get(3);
                rlv_3.layout(0, DensityUtil.dip2px(ctx, singleSizeHeight * 3), DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 5));
                ImageView img_3 = rlv_3.getImageView();
                img_3.layout(0, 0, DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 2));
                TextView tv_3 = rlv_3.getTextView();
                tv_3.layout(0, DensityUtil.dip2px(ctx, singleSizeHeight * 2 - tv_3.getHeight()), DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 2));
                tv_3.setTextColor(Color.WHITE);
                tv_3.setPadding(padding_10, padding_10, padding_10, padding_10);
                tv_3.setText("居中显示3");
                break;
            case 4:

                RelativeLayoutView rlv_4 = (RelativeLayoutView) views.get(4);
                rlv_4.layout(DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 3), DensityUtil.dip2px(ctx, singleSizeWidth * 4), DensityUtil.dip2px(ctx, singleSizeHeight * 5));
                ImageView img_4 = rlv_4.getImageView();
                img_4.layout(0, 0, DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 2));
                TextView tv_4 = rlv_4.getTextView();
                tv_4.layout(0, DensityUtil.dip2px(ctx, singleSizeHeight * 2 - tv_4.getHeight()), DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 2));
                tv_4.setTextColor(Color.WHITE);
                tv_4.setPadding(padding_10, padding_10, padding_10, padding_10);
                tv_4.setText("居中显示4");
                break;
            case 5:
                RelativeLayoutView rlv_5 = (RelativeLayoutView) views.get(5);
                rlv_5.layout(DensityUtil.dip2px(ctx, singleSizeWidth * 4), DensityUtil.dip2px(ctx, singleSizeHeight * 3), DensityUtil.dip2px(ctx, singleSizeWidth * 6), DensityUtil.dip2px(ctx, singleSizeHeight * 5));
                ImageView img_5 = rlv_5.getImageView();
                img_5.layout(0, 0, DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 2));
                TextView tv_5 = rlv_5.getTextView();
                tv_5.layout(0, DensityUtil.dip2px(ctx, singleSizeHeight * 2 - tv_5.getHeight()), DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 2));
                tv_5.setTextColor(Color.WHITE);
                tv_5.setPadding(padding_10, padding_10, padding_10, padding_10);
                tv_5.setText("居中显示5");
                break;
            case 6:
                RelativeLayoutView rlv_6 = (RelativeLayoutView) views.get(6);
                rlv_6.layout(DensityUtil.dip2px(ctx, singleSizeWidth * 6), DensityUtil.dip2px(ctx, singleSizeHeight * 3), DensityUtil.dip2px(ctx, singleSizeWidth * 8), DensityUtil.dip2px(ctx, singleSizeHeight * 5));
                ImageView img_6 = rlv_6.getImageView();
                img_6.layout(0, 0, DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 2));
                TextView tv_6 = rlv_6.getTextView();
                tv_6.layout(0, DensityUtil.dip2px(ctx, singleSizeHeight * 2 - tv_6.getHeight()), DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 2));
                tv_6.setTextColor(Color.WHITE);
                tv_6.setPadding(padding_10, padding_10, padding_10, padding_10);
                tv_6.setText("居中显示5");
                break;
            case 7:
                views.get(7).layout(0, DensityUtil.dip2px(ctx, singleSizeHeight * 5), DensityUtil.dip2px(ctx, singleSizeWidth), DensityUtil.dip2px(ctx, singleSizeHeight * 6));
                ImageView iv_7 = (ImageView) views.get(7);
                iv_7.setImageResource(R.mipmap.ic_launcher);
                break;
            case 8:
                views.get(8).layout(DensityUtil.dip2px(ctx, singleSizeWidth), DensityUtil.dip2px(ctx, singleSizeHeight * 5), DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 6));
                ImageView iv_8 = (ImageView) views.get(8);
                iv_8.setImageResource(R.mipmap.ic_launcher);
                break;
            case 9:
                views.get(9).layout(DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 5), DensityUtil.dip2px(ctx, singleSizeWidth * 3), DensityUtil.dip2px(ctx, singleSizeHeight * 6));
                ImageView iv_9 = (ImageView) views.get(9);
                iv_9.setImageResource(R.mipmap.ic_launcher);
                break;
            case 10:
                views.get(10).layout(DensityUtil.dip2px(ctx, singleSizeWidth * 3), DensityUtil.dip2px(ctx, singleSizeHeight * 5), DensityUtil.dip2px(ctx, singleSizeWidth * 4), DensityUtil.dip2px(ctx, singleSizeHeight * 6));
                ImageView iv_10 = (ImageView) views.get(10);
                iv_10.setImageResource(R.mipmap.ic_launcher);
                break;
            case 11:
                views.get(11).layout(DensityUtil.dip2px(ctx, singleSizeWidth * 4), DensityUtil.dip2px(ctx, singleSizeHeight * 5), DensityUtil.dip2px(ctx, singleSizeWidth * 5), DensityUtil.dip2px(ctx, singleSizeHeight * 6));
                ImageView iv_11 = (ImageView) views.get(11);
                iv_11.setImageResource(R.mipmap.ic_launcher);
                break;
            case 12:
                views.get(12).layout(DensityUtil.dip2px(ctx, singleSizeWidth * 5), DensityUtil.dip2px(ctx, singleSizeHeight * 5), DensityUtil.dip2px(ctx, singleSizeWidth * 6), DensityUtil.dip2px(ctx, singleSizeHeight * 6));
                ImageView iv_12 = (ImageView) views.get(12);
                iv_12.setImageResource(R.mipmap.ic_launcher);
                break;
            case 13:
                views.get(13).layout(DensityUtil.dip2px(ctx, singleSizeWidth * 6), DensityUtil.dip2px(ctx, singleSizeHeight * 5), DensityUtil.dip2px(ctx, singleSizeWidth * 7), DensityUtil.dip2px(ctx, singleSizeHeight * 6));
                ImageView iv_13 = (ImageView) views.get(13);
                iv_13.setImageResource(R.mipmap.ic_launcher);
                break;
            case 14:
                views.get(14).layout(DensityUtil.dip2px(ctx, singleSizeWidth * 7), DensityUtil.dip2px(ctx, singleSizeHeight * 5), DensityUtil.dip2px(ctx, singleSizeWidth * 8), DensityUtil.dip2px(ctx, singleSizeHeight * 6));
                ImageView iv_14 = (ImageView) views.get(14);
                iv_14.setImageResource(R.mipmap.ic_launcher);
                break;

        }
    }
}
