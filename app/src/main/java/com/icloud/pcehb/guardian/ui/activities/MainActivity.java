package com.icloud.pcehb.guardian.ui.activities;


import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.icloud.pcehb.guardian.R;
import com.icloud.pcehb.guardian.ui.fragments.*;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public ViewPagerAdapter adapter;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            viewPager = (ViewPager) findViewById(R.id.viewpager);
            setupViewPager(viewPager);

            tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);

            isNetworkConnected();
        }


        private boolean isNetworkConnected() {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            if (cm.getActiveNetworkInfo() != null){
                System.out.println("CONNECTED");
            }
            else {
                System.out.println("NOT CONNECTED");
            }

            return cm.getActiveNetworkInfo() != null;
        }

        //sets up tab pages/categories
        private void setupViewPager(ViewPager viewPager) {
            adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(new NewsFragment(), "NEWS");
            adapter.addFragment(new SportFragment(), "SPORT");
            adapter.addFragment(new BusinessFragment(), "BUSINESS");
            adapter.addFragment(new CultureFragment(), "CULTURE");
            adapter.addFragment(new EducationFragment(), "EDUCATION");
            adapter.addFragment(new ScienceFragment(), "SCIENCE");
            adapter.addFragment(new TravelFragment(), "TRAVEL");

            viewPager.setAdapter(adapter);
        }

        class ViewPagerAdapter extends FragmentPagerAdapter {
            private final List<Fragment> mFragmentList = new ArrayList<>();
            private final List<String> mFragmentTitleList = new ArrayList<>();

            public ViewPagerAdapter(FragmentManager manager) {
                super(manager);
            }

            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            public void addFragment(Fragment fragment, String title) {
                mFragmentList.add(fragment);
                mFragmentTitleList.add(title);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentTitleList.get(position);
            }
        }
    }