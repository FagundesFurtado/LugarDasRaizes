package com.example.memo.lugardasraizes.Adapter;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;


public class MyTabsListener implements ActionBar.TabListener {

    private ViewPager viewPager;
    private int index;
    private final String TAG = "ListenerTabs";

    public MyTabsListener(ViewPager vp, int index) {
        this.viewPager = vp;
        this.index = index;
    }
    public void onTabSelected(ActionBar.Tab tab,
                              FragmentTransaction ft) {

        viewPager.setCurrentItem(index);
    }
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

}