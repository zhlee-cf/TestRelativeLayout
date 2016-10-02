package com.exiu.testrelativelayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.rl_1)
    RelativeLayout rl_1;
    @Bind(R.id.rl_2)
    CustomRelativeLayout rl_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 遥控器OK键 和 电脑空格键
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_SPACE) {
            doOnOkKeyClick();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 遥控器OK键点击时，根据焦点的position 跳转到不同的界面
     */
    private void doOnOkKeyClick() {
        View rootView = getWindow().getDecorView();
        View focusView = rootView.findFocus();
        int focusViewId = focusView.getId();
        MyUtils.showToast(this, "当前焦点在:;" + focusViewId);
//        Intent intent = new Intent("com.gitv.tv.launcher.activity.GitvInterfaceActivity");
        // 优朋客户端
        Intent intent = new Intent("com.voole,VOOLEWEBEPG");
        String paramHnMUrl = "";
        switch (focusViewId) {
            case 0:
                break;
            case 1:  // 轮播图
                CarouselRelativeLayoutView carouselRelativeLayoutView = (CarouselRelativeLayoutView) focusView;
                int carouselPosition = carouselRelativeLayoutView.getPosition();
                MyUtils.showToast(this, "当前轮播到第:;" + carouselPosition + "张图片");
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:  // 电视台
                break;
            case 8:  // 电影
                break;
            case 9:  // 电视剧
                break;
            case 10:  // 推荐
                break;
            case 11:  // 少儿
                break;
            case 12:  // 应用
                break;
            case 13:  // 观看历史
                break;
            case 14:  // 搜索

                break;
        }
        if (!TextUtils.isEmpty(paramHnMUrl)) {  // 如果URL不为空则进行跳转
            intent.putExtra("paramHnMUrl", paramHnMUrl);
            startActivity(intent);
        }
    }
}
