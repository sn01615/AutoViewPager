package com.liuguilin.autoviewpager.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/*
 *  项目名：  AutoViewPager 
 *  包名：    com.liuguilin.autoviewpager.view
 *  文件名:   AutoViewPager
 *  创建者:   刘某人程序员
 *  创建时间:  2017/4/10 20:00
 *  描述：    重写ViewPager来实现自动轮播 by 刘某人
 */
public class AutoViewPager extends ViewPager {

    //轮播的Handler
    private static final int AUTO_WHAT = 8888;

    //View集合
    private List<View> mList = new ArrayList<>();
    //是否开启轮播
    private boolean isStartAuto = true;
    //轮播间隔时长
    private int autoTime = 3000;

    public AutoViewPager(Context context) {
        super(context);
    }

    public AutoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setView(View view) {
        mList.add(view);
        setAdapter(new AutoViewPagerAdapter());
    }

    public int getAutoTime() {
        return autoTime;
    }

    public void setAutoTime(int autoTime) {
        this.autoTime = autoTime;
    }

    public boolean isStartAuto() {
        return isStartAuto;
    }

    public void setStartAuto(boolean startAuto) {
        isStartAuto = startAuto;
        mHandler.sendEmptyMessage(AUTO_WHAT);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AUTO_WHAT:
                    if (isStartAuto) {
                        //获取当前的下标
                        int index = getCurrentItem();
                        //如果是最后一个
                        if (index >= mList.size() - 1) {
                            //那就恢复第一个
                            setCurrentItem(0);
                        } else {
                            //每次+1
                            setCurrentItem(index + 1);
                        }
                        //继续发送
                        mHandler.sendEmptyMessageDelayed(AUTO_WHAT, autoTime);
                    }
                    break;
            }
        }
    };

    //适配器
    class AutoViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(mList.get(position));
        }
    }
}
