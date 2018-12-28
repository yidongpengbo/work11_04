package com.example.lenovo.work11_04.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lenovo.work11_04.fragment.Fragment_Details;
import com.example.lenovo.work11_04.fragment.Fragment_Evaluate;
import com.example.lenovo.work11_04.fragment.Fragment_Shop;

public class Login_ViewPager_Adapter extends FragmentPagerAdapter {
    private String[] menus={"商品","详情","评价"};
    public Login_ViewPager_Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new Fragment_Shop();
            case 1:
                return new Fragment_Details();
            case 2:
                return new Fragment_Evaluate();
            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return menus.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return menus[position];
    }
}
