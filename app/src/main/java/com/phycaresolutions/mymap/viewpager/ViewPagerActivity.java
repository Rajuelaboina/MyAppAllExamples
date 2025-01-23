package com.phycaresolutions.mymap.viewpager;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.phycaresolutions.mymap.R;
import com.phycaresolutions.mymap.restfull.ApiService;

import java.util.Timer;
import java.util.TimerTask;

public class ViewPagerActivity extends AppCompatActivity {
    int currentPage = 0;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_pager);
        ViewPager viewPager = findViewById(R.id.viewPager);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager,true);

         int[] images = {R.drawable.home_24, R.drawable.img, R.drawable.dashboard_24,
                R.drawable.doctor};

        ViewPagerAdapter adapter1 = new ViewPagerAdapter(getApplicationContext(),images);
        viewPager.setAdapter(adapter1);
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPage == adapter1.getCount()){
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },500,1000);

    }
}