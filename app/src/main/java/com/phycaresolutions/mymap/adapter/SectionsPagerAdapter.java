package com.phycaresolutions.mymap.adapter;



import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.phycaresolutions.mymap.userfragmnet.DashBoardFragment;
import com.phycaresolutions.mymap.userfragmnet.HomeFragment;
import com.phycaresolutions.mymap.userfragmnet.NotificationFragment;
import com.phycaresolutions.mymap.R;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.home, R.string.dashboard,R.string.settings};
    private final Context mContext;
    int tabCount;
    public SectionsPagerAdapter(@NonNull FragmentManager fm, Context mContext, int tabCount) {
        super(fm);
        this.mContext = mContext;
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new DashBoardFragment();
            case 2:
                return new NotificationFragment();
        }
        return null;
    }

   /* @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }*/


    @Override
    public int getCount() {
        return  tabCount;
    }
}
