package com.crossbox.multiplefragmentstest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    FragmentPagerAdapter pagerAdapter;

    public static class MyPagerAdapter extends FragmentPagerAdapter{

        private static int NUM_ITEMS = 3;
        private Context context;
        private String tabTitles[] = new String[]{"Tab1","Tab2","Tab3"};

        public MyPagerAdapter(@NonNull FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }


        @NonNull
        @Override // Returns the fragment to display for that page
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return FirstFragment.newInstance(0,"Page#1");
                case 1:
                    return FirstFragment.newInstance(1,"Page#2");
                case 2:
                    return SecondFragment.newInstance(2,"Page#3");
                default:
                    return null;
            }
        }

        @Override // Returns total number of pages
        public int getCount() {
            return NUM_ITEMS;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
//            return "Page " + position;
            return tabTitles[position];
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ViewPager viewPager = findViewById(R.id.vpPager);
//        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(pagerAdapter);

        ViewPager viewPager = findViewById(R.id.vpPager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),MainActivity.this));

        TabLayout tabLayout = findViewById(R.id.slidingTabs);
        tabLayout.setupWithViewPager(viewPager);

    }
}
