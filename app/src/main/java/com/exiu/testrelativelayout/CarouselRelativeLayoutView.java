package com.exiu.testrelativelayout;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;

/**
 * 自定义的 子相对布局——顶部轮播图
 */
public class CarouselRelativeLayoutView extends RelativeLayoutView {
    // 需要轮播的图片id
    private int[] imageIds = {R.mipmap.a1, R.mipmap.ic_launcher, R.mipmap.middle_1, R.mipmap.dst};
    // 图片对应的标题
    private String[] titles = {"我是第一张图片", "我是第二张图片", "我是第三张图片", "我是第四张图片"};
    // 当前轮播次数
    private int position;

    /**
     * 获取当前是第几张图片在展示
     *
     * @return
     */
    public int getPosition() {
        return position % imageIds.length != 0 ? position % imageIds.length : imageIds.length;
    }

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

    /**
     * 开启轮播任务  handler延迟消息
     */
    private void startTask() {
        handler.sendEmptyMessageDelayed(10086, 3000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getImageView().setImageResource(imageIds[position % imageIds.length]);
            getTextView().setText(titles[position % titles.length]);
//            Toast.makeText(getContext(), "收到轮播::" + position % imageIds.length + "图片id::" + imageIds[position % imageIds.length], Toast.LENGTH_SHORT).show();
            position++;
            handler.sendEmptyMessageDelayed(10086, 3000);
        }
    };
}