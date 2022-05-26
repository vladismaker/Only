package com.onlyapp.only;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CatalogFragment extends Fragment {
    View view;
    MainActivity ma;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_catalog, container, false);

        if(getActivity()!=null){
            ma = (MainActivity)getActivity();
        }
        int select = ma.selectSection;

        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
        selectPage(select, tabLayout, pager);
/*        TabLayout.Tab tab = tabLayout.getTabAt(select);
        tab.select();*/

        return view;
    }
    public void selectPage(int pageIndex, TabLayout tabLayout, ViewPager viewPager){
        tabLayout.setScrollPosition(pageIndex,0f,true);
        viewPager.setCurrentItem(pageIndex);
    }


    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm){
            super(fm);
        }
        public int getCount(){
            return 2;
        }

        public Fragment getItem(int position){
            switch (position){
                case 0:
                    return new ForHimFragment();
                case 1:
                    return new ForHerFragment();
            }
            return null;
        }
        public CharSequence getPageTitle (int position){
            switch (position){
                case 0:
                    return getResources().getText(R.string.tab_for_him);
                case 1:
                    return getResources().getText(R.string.tab_for_her);
            }
            return null;
        }
    }
}