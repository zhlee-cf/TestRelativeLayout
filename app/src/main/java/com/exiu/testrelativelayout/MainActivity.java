package com.exiu.testrelativelayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
            View rootView = getWindow().getDecorView();
            View focusView = rootView.findFocus();
            MyUtils.showToast(this, "当前焦点在:;" + focusView.getId());
        }
        return super.onKeyDown(keyCode, event);
    }
}
