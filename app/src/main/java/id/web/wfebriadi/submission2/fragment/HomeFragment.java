package id.web.wfebriadi.submission2.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.web.wfebriadi.submission2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ViewPager mViewPager;
    TabLayout mTabLayout;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mViewPager = (ViewPager)view.findViewById(R.id.view_pager);
        mViewPager.setAdapter(new sliderViewAdapter(getChildFragmentManager()));

        mTabLayout = (TabLayout)view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.post(new Runnable(){
            @Override
            public void run(){
                mTabLayout.setupWithViewPager(mViewPager);
            }
        });

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;

    }

    private class sliderViewAdapter extends FragmentPagerAdapter {

        String sedang_tayang = getResources().getString(R.string.now_plying);
        String akan_datang = getResources().getString(R.string.upcomming);
        String search = getResources().getString(R.string.search);
        final String tabs[] = {sedang_tayang, akan_datang, search};

        public sliderViewAdapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new NowPlaying();
                case 1:
                    return new Upcomming();
                case 2:
                    return new SearchFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabs.length;
        }
        @Override
        public CharSequence getPageTitle(int position){
            return tabs[position];
        }
    }

}

