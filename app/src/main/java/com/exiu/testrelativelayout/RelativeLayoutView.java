package com.exiu.testrelativelayout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 自定义的  子相对布局——普通顶部和中部
 */
public class RelativeLayoutView extends RelativeLayout {
    // 默认边框颜色
    private int co = getResources().getColor(R.color.colorPrimaryDark);
    // 默认边框宽度
    private int borderWidth = DensityUtil.dip2px(getContext(), 8);
    // 缩小动画
    private AnimatorSet mAnimatorSetZoomIn;
    // 放大动画
    private AnimatorSet mAnimatorSetZoomOut;
    // 此相对布局里面的两个子控件
    protected ImageView imageView;
    protected TextView textView;

    public RelativeLayoutView(Context context) {
        super(context);
        initView();
    }

    public RelativeLayoutView(Context context, AttributeSet attrs,
                              int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public RelativeLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    /**
     * 设置子控件的属性  底部布局会重写
     */
    protected void setProperty() {
        textView.setBackgroundColor(Color.argb(150, 0, 0, 0));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    void initView() {
        setFocusable(true);
        setFocusableInTouchMode(true);
//        setPadding(5, 5, 5, 5);
        textView = new TextView(getContext());
        imageView = new ImageView(getContext());
        imageView.setImageResource(R.mipmap.ic_launcher);
        textView.setText("开始轮播");
        // 子类可以给里面的控件设置不同 属性
        setProperty();
        addView(imageView);
        addView(textView);
    }

    //设置颜色  
    public void setColour(int color) {
        co = color;
    }

    //设置边框宽度  
    public void setBorderWidth(int width) {
        borderWidth = width;
    }

    @Override
    /**
     * 画边框 不知道为嘛 在onDraw方法里无效，ImageView画边框是在onDraw方法里写的
     */
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        // 画边框
        Rect rec = canvas.getClipBounds();
        rec.bottom--;
        rec.right--;
        Paint paint = new Paint();
        //设置边框颜色
        paint.setColor(co);
        paint.setStyle(Paint.Style.STROKE);
        //设置边框宽度
        paint.setStrokeWidth(borderWidth);
        canvas.drawRect(rec, paint);
    }

    /**
     * 获得当前控件的子控件 ImageView 供外部使用
     *
     * @return
     */
    public ImageView getImageView() {
        return this.imageView;
    }

    /**
     * 获得当前控件的子控件 TextView 供外部使用
     *
     * @return
     */
    public TextView getTextView() {
        return this.textView;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        // 此控件宽高
//        int selfSizeWidth = MeasureSpec.getSize(widthMeasureSpec);
//        int selfSizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        // 测量所有的子view  的大小
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    /**
     * 焦点变化时，会调用此方法
     */
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (gainFocus) { // 获得焦点  放大 边框变颜色
            zoomOut();
            co = getResources().getColor(R.color.colorWhite);
            bringToFront();  // 移到前端显示 在线性布局中会有bug，会把该控件放到最后
        } else {
            zoomIn();
            co = getResources().getColor(R.color.colorPrimaryDark);
        }
    }

    /**
     * 方法  缩小动画 属性动画
     */
    private void zoomIn() {
        //缩小动画
        if (mAnimatorSetZoomIn == null) {
            mAnimatorSetZoomIn = new AnimatorSet();
            ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "scaleX", 1.2f, 1.0f);
            ObjectAnimator animatorY = ObjectAnimator.ofFloat(this, "scaleY", 1.2f, 1.0f);
            animatorX.setDuration(300);
            animatorY.setDuration(300);
            mAnimatorSetZoomIn.playTogether(animatorX, animatorY);
        }
        mAnimatorSetZoomIn.start();
    }

    /**
     * 方法 放大动画 属性动画
     */
    private void zoomOut() {
        //放大动画
        if (mAnimatorSetZoomOut == null) {
            mAnimatorSetZoomOut = new AnimatorSet();
            ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "scaleX", 1.0f, 1.2f);
            ObjectAnimator animatorY = ObjectAnimator.ofFloat(this, "scaleY", 1.0f, 1.2f);
            animatorX.setDuration(300);
            animatorY.setDuration(300);
            mAnimatorSetZoomOut.playTogether(animatorX, animatorY);
        }
        mAnimatorSetZoomOut.start();
    }
}