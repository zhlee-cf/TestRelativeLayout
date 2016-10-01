package com.exiu.testrelativelayout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

public class FocusImageView extends ImageView {

    private int co = Color.GREEN;
    private int borderwidth = DensityUtil.dip2px(getContext(), 5);
    private AnimatorSet mAnimatorSetZoomIn;
    private AnimatorSet mAnimatorSetZoomOut;

    public FocusImageView(Context context) {
        super(context);
        initView();
    }

    public FocusImageView(Context context, AttributeSet attrs,
                          int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public FocusImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        setScaleType(ScaleType.FIT_XY);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    //设置颜色  
    public void setColour(int color) {
        co = color;
    }

    //设置边框宽度  
    public void setBorderWidth(int width) {
        borderwidth = width;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画边框  
        Rect rec = canvas.getClipBounds();
        rec.bottom--;
        rec.right--;
        Paint paint = new Paint();
        //设置边框颜色  
        paint.setColor(co);
        paint.setStyle(Paint.Style.STROKE);
        //设置边框宽度  
        paint.setStrokeWidth(borderwidth);
        canvas.drawRect(rec, paint);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (gainFocus) {
            zoomOut();
            bringToFront();
        } else {
            zoomIn();
        }
    }


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