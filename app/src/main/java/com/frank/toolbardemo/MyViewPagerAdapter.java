package com.frank.toolbardemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class MyViewPagerAdapter extends FragmentPagerAdapter {
    public final int PAGE_COUNT = 8;
    private String[] titles;

    public MyViewPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MyFragment.newInstance(position);
            case 1:
                return Fragment_2.newInstance(position);
            case 2:
                return Fragment_2.newInstance(position);
            case 3:
                return Fragment_2.newInstance(position);
            case 4:
                return Fragment_2.newInstance(position);
            case 5:
                return Fragment_2.newInstance(position);
            case 6:
                return Fragment_2.newInstance(position);
            case 7:
                return Fragment_2.newInstance(position);

        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
