package com.exiu.testrelativelayout;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.Toast;

public class CarouselRelativeLayoutView extends RelativeLayoutView {

    private int[] imageIds = {R.mipmap.a1, R.mipmap.ic_launcher};
    private String[] titles = {"我是第一张图片", "我是第二张图片"};

    private int position;

    public CarouselRelativeLayoutView(Context context) {
        super(context);
        startTask();
    }

    public CarouselRelativeLayoutView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        startTask();
    }

    public CarouselRelativeLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        startTask();
    }

    private void startTask() {
        handler.sendEmptyMessageDelayed(10086, 3000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getImageView().setImageResource(imageIds[position % imageIds.length]);
            getTextView().setText(titles[position % titles.length]);
            Toast.makeText(getContext(), "收到轮播::" + position % imageIds.length + "图片id::" + imageIds[position % imageIds.length], Toast.LENGTH_SHORT).show();
            position++;
            handler.sendEmptyMessageDelayed(10086, 3000);
        }
    };
}