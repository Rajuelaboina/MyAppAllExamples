package com.phycaresolutions.mymap.tablayout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.phycaresolutions.mymap.R;
import com.phycaresolutions.mymap.userfragmnet.DashBoardFragment;
import com.phycaresolutions.mymap.userfragmnet.NotificationFragment;

public class UserTabLayoutActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tab_layout);
         tabLayout = findViewById(R.id.tabLayout2);
         viewPager2 = findViewById(R.id.viewPager2);
         CustomAdapter adapter = new CustomAdapter(this);
         viewPager2.setAdapter(adapter);
         new TabLayoutMediator(tabLayout,viewPager2,(tab, position) -> {
            if (position ==0){
                tab.setText("Movies");
                tab.setIcon(R.drawable.home_24);
            }else if (position == 1){
                 tab.setText("News");
                tab.setIcon(R.drawable.ic_menu_camera);
             }else if (position == 2){
                 tab.setText("TV Shows");
                tab.setIcon(R.drawable.notifications_24);
             }
         }).attach();

    }
    private class CustomAdapter extends FragmentStateAdapter{

        public CustomAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position ==0){
                return new MovieFragment();
            } else if (position == 1) {
                return new NotificationFragment();
            } else if (position == 2) {
                return new DashBoardFragment();
            }
            return null;
        }

        @Override
        public int getItemCount() {
            return 3;
        }

    }
}