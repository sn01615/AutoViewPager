package com.liuguilin.autoviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.liuguilin.autoviewpager.entity.Constans;
import com.liuguilin.autoviewpager.view.AutoViewPager;

public class MainActivity extends AppCompatActivity {

    private AutoViewPager mAutoViewPager;
    private TextView tv_now_count;
    private TextView tv_all_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        tv_now_count = (TextView) findViewById(R.id.tv_now_count);
        tv_all_count = (TextView) findViewById(R.id.tv_all_count);

        mAutoViewPager = (AutoViewPager) findViewById(R.id.mAutoViewPager);
        //设置轮播开关
        mAutoViewPager.setStartAuto(true);
        //设置轮播间隔时长 默认3s
        mAutoViewPager.setAutoTime(2000);

        //开始填充数据(模拟)
        for (int i = 0; i < Constans.TITLE.length; i++) {
            View view = View.inflate(this, R.layout.layout_pager_item, null);
            ImageView iv_special_bg = (ImageView) view.findViewById(R.id.iv_special_bg);
            //解析图片
            Glide.with(this).load(Constans.IMG_URL[i]).centerCrop().into(iv_special_bg);
            TextView tv_special_name = (TextView) view.findViewById(R.id.tv_special_name);
            tv_special_name.setText(Constans.TITLE[i]);
            TextView tv_special_desc = (TextView) view.findViewById(R.id.tv_special_desc);
            tv_special_desc.setText(Constans.CONTENT[i]);

            //点击事件
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, Constans.TITLE[finalI], Toast.LENGTH_SHORT).show();
                }
            });
            //装载
            mAutoViewPager.setView(view);
        }

        //监听滑动
        mAutoViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setPosition(int position) {
        tv_all_count.setText(Constans.TITLE.length + "");
        tv_now_count.setText(position + 1 + "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁
        if (mAutoViewPager != null) {
            mAutoViewPager.setStartAuto(false);
        }
    }
}
