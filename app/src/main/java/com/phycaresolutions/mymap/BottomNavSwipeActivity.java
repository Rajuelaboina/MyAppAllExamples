package com.phycaresolutions.mymap;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavSwipeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView navigationView;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bottom_nav_swipe);
        navigationView = findViewById(R.id.buttom_navigation);
        viewPager = findViewById(R.id.view_pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        navigationView.setOnNavigationItemSelectedListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
              switch (position){
                  case 0:
                      navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                      break;
                  case 1:
                      navigationView.getMenu().findItem(R.id.nav_dashboard).setChecked(true);
                      break;
                  case 2:
                      navigationView.getMenu().findItem(R.id.nav_notification).setChecked(true);
                      break;
              }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        if (menuItem.getItemId() == R.id.nav_home) {
            viewPager.setCurrentItem(0);
           // fragment = new HomeFragment();
        } else if ( menuItem.getItemId() == R.id.nav_dashboard) {
            viewPager.setCurrentItem(1);
            //fragment = new DashBoardFragment();
        }else if ( menuItem.getItemId() == R.id.nav_notification) {
            viewPager.setCurrentItem(0);
         //   fragment = new NotificationFragment();
        }

        return loadFragment(fragment);
    }
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container_frame_layout, fragment);
            ft.commit();
            return true;
        }
        return false;
    }
}