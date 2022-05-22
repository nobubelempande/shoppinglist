package com.viiishoppinglistapp.doit.ui.inventory;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.viiishoppinglistapp.doit.Fragments.fragmentInventoryItems;
import com.viiishoppinglistapp.doit.Fragments.fragmentInventoryStats;
import com.viiishoppinglistapp.doit.R;
import com.viiishoppinglistapp.doit.TabbedInventoryActivity;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class InventorySectionsPagerAdapter extends FragmentPagerAdapter {

    private TabbedInventoryActivity activity;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public InventorySectionsPagerAdapter(Context context, FragmentManager fm, TabbedInventoryActivity activity) {
        super(fm);
        mContext = context;
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //return PlaceholderFragment.newInstance(position + 1);
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new fragmentInventoryItems(mContext, activity);
                break;
            case 1:
                fragment = new fragmentInventoryStats(mContext, activity);
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}