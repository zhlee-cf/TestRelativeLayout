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
 * 自定义相对布局，里面自动添加自定义的子相对布局，子相对布局获得焦点变大
 * Created by 振辉 on 2016/10/1.
 */
public class CustomRelativeLayout extends RelativeLayout {

    private Context ctx;
    // 添加进来的Views
    private ArrayList<View> views;
    // 最小控件宽度
    private int singleSizeWidth;
    // 最小控件高度
    private int singleSizeHeight;
    // 内边距
    private int padding_10;
    // 底部标题
    private String[] bottomTitles = {"电视台", "电影", "电视剧", "推荐", "少儿", "应用", "观看历史", "搜索"};
    // 底部图标id
    private int[] bottomIcons = {R.mipmap.dst, R.mipmap.dy, R.mipmap.dsj, R.mipmap.tj, R.mipmap.se, R.mipmap.yy, R.mipmap.gkls, R.mipmap.ss};
    // 中间标题
    private String[] middleTitles = {"我是中间标题1", "我是中间标题2", "我是中间标题3", "我是中间标题4"};
    // 中间图标id
    private int[] middleIcons = {R.mipmap.middle_1, R.mipmap.middle_2, R.mipmap.middle_3, R.mipmap.middle_4};
    // 顶部标题 (轮播图使用自身设置)
    private String[] topTitles = {"我是顶部标题1", "", "我是顶部标题3"};
    // 顶部图标 (轮播图使用自身设置)
    private int[] topIcons = {R.mipmap.top_1, -1, R.mipmap.top_3};

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
                // 普通 上 中 布局
                view = new RelativeLayoutView(ctx);
            } else if (i == 1) {
                // 轮播图布局
                view = new CarouselRelativeLayoutView(ctx);
            } else {
                // 底部布局
                view = new BottomRelativeLayoutView(ctx);
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
        int selfSizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfSizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        // 最小控件宽高
        singleSizeWidth = selfSizeWidth / 8;
        singleSizeHeight = selfSizeHeight / 6;

        padding_10 = DensityUtil.dip2px(ctx, 10f);

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
        if (position < 3) {  // 上
            setTopLayout(position);
        } else if (position > 6) {  // 下
            setBottomLayout(position);
        } else {    // 中
            setMiddleLayout(position);
        }
    }

    /**
     * 设置顶部布局
     *
     * @param position
     */
    private void setTopLayout(int position) {
        RelativeLayoutView relativeLayoutView = (RelativeLayoutView) views.get(position);
        ImageView iv_top = relativeLayoutView.getImageView();
        TextView tv_top = relativeLayoutView.getTextView();
        switch (position) {
            case 0:
                relativeLayoutView.layout(0, 0, DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                iv_top.layout(0, 0, DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                tv_top.layout(0, DensityUtil.dip2px(ctx, singleSizeHeight * 3 - tv_top.getHeight()), DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                tv_top.setText(topTitles[position]);
                iv_top.setImageResource(topIcons[position]);
                break;
            case 1:
                relativeLayoutView.layout(DensityUtil.dip2px(ctx, singleSizeWidth * 2), 0, DensityUtil.dip2px(ctx, singleSizeWidth * 6), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                iv_top.layout(0, 0, DensityUtil.dip2px(ctx, singleSizeWidth * 4), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                tv_top.layout(0, DensityUtil.dip2px(ctx, singleSizeHeight * 3 - tv_top.getHeight()), DensityUtil.dip2px(ctx, singleSizeWidth * 4), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                break;
            case 2:
                relativeLayoutView.layout(DensityUtil.dip2px(ctx, singleSizeWidth * 6), 0, DensityUtil.dip2px(ctx, singleSizeWidth * 8), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                iv_top.layout(0, 0, DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                tv_top.layout(0, DensityUtil.dip2px(ctx, singleSizeHeight * 3 - tv_top.getHeight()), DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 3));
                tv_top.setText(topTitles[position]);
                iv_top.setImageResource(topIcons[position]);
                break;
        }
        tv_top.setTextColor(Color.WHITE);
        tv_top.setPadding(padding_10, padding_10, padding_10, padding_10);
    }

    /**
     * 设置中间布局
     *
     * @param position
     */
    private void setMiddleLayout(int position) {
        RelativeLayoutView relativeLayoutView = (RelativeLayoutView) views.get(position);
        relativeLayoutView.layout(DensityUtil.dip2px(ctx, singleSizeWidth * (position - 3) * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 3), DensityUtil.dip2px(ctx, singleSizeWidth * ((position - 3) * 2 + 2)), DensityUtil.dip2px(ctx, singleSizeHeight * 5));
        ImageView iv_middle = relativeLayoutView.getImageView();
        TextView tv_middle = relativeLayoutView.getTextView();
        iv_middle.layout(0, 0, DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 2));
        tv_middle.layout(0, DensityUtil.dip2px(ctx, singleSizeHeight * 2 - tv_middle.getHeight()), DensityUtil.dip2px(ctx, singleSizeWidth * 2), DensityUtil.dip2px(ctx, singleSizeHeight * 2));
        tv_middle.setTextColor(Color.WHITE);
        tv_middle.setPadding(padding_10, padding_10, padding_10, padding_10);
        tv_middle.setText(middleTitles[position - 3]);
        iv_middle.setImageResource(middleIcons[position - 3]);
    }

    /**
     * 设置底部布局
     *
     * @param position
     */
    private void setBottomLayout(int position) {
        BottomRelativeLayoutView bottomRelativeLayoutView = (BottomRelativeLayoutView) views.get(position);
        bottomRelativeLayoutView.layout(DensityUtil.dip2px(ctx, singleSizeWidth * (position - 7)), DensityUtil.dip2px(ctx, singleSizeHeight * 5), DensityUtil.dip2px(ctx, singleSizeWidth * (position - 7 + 1)), DensityUtil.dip2px(ctx, singleSizeHeight * 6));
        ImageView iv_icon = bottomRelativeLayoutView.getImageView();
        TextView tv_title = bottomRelativeLayoutView.getTextView();
        iv_icon.layout(0, 0, DensityUtil.dip2px(ctx, singleSizeWidth), DensityUtil.dip2px(ctx, singleSizeHeight - tv_title.getHeight()));
        tv_title.layout(0, DensityUtil.dip2px(ctx, singleSizeHeight - tv_title.getHeight()), DensityUtil.dip2px(ctx, singleSizeWidth), DensityUtil.dip2px(ctx, singleSizeHeight));
        tv_title.setTextColor(Color.WHITE);
        tv_title.setPadding(padding_10, padding_10, padding_10, padding_10);
        tv_title.setText(bottomTitles[position - 7]);
        iv_icon.setImageResource(bottomIcons[position - 7]);
    }

}
