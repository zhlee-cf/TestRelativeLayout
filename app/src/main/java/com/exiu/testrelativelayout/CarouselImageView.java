package com.exiu.testrelativelayout;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.Toast;

/**
 * Created by 振辉 on 2016/10/1.
 */
public class CarouselImageView extends FocusImageView {

    private int[] imageIds = {R.mipmap.a1, R.mipmap.ic_launcher};

    private int position;

    public CarouselImageView(Context context) {
        super(context);
        startTask();
    }

    public CarouselImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        startTask();
    }

    public CarouselImageView(Context context, AttributeSet attrs) {
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
            setImageResource(imageIds[position % imageIds.length]);
            Toast.makeText(getContext(), "收到轮播::" + position % imageIds.length + "图片id::" + imageIds[position % imageIds.length], Toast.LENGTH_SHORT).show();
            position++;
            handler.sendEmptyMessageDelayed(10086, 3000);
        }
    };
}
