package com.phycaresolutions.mymap;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.JsonObject;
import com.phycaresolutions.mymap.adapter.SectionsPagerAdapter;
import com.phycaresolutions.mymap.databinding.ActivityTabsBottomBinding;

import org.json.JSONObject;

public class TabsBottomActivity extends AppCompatActivity {
       ActivityTabsBottomBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTabsBottomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewPager viewPager = binding.viewPager;


        TabLayout tabLayout = binding.tabs;
        tabLayout.addTab(tabLayout.newTab().setText("Home").setIcon(R.drawable.home_24));
        tabLayout.addTab(tabLayout.newTab().setText("Chats").setIcon(R.drawable.dashboard_24));
        tabLayout.addTab(tabLayout.newTab().setText("Calls").setIcon(R.drawable.notifications_24));

        tabLayout.setupWithViewPager(viewPager);

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager(),getApplicationContext(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
              viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.optString("");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
/*
When your app has exited, the coroutine that launched from GlobalScope can still run in the background until the process death (e.g. killed by the operating system)*/

/*
Model: This layer is responsible for the abstraction of the data sources.
Model and ViewModel work together to get and save the data.

View: The purpose of this layer is to inform the ViewModel about the user’s action.
 This layer observes the ViewModel and does not contain any kind of application logic.

ViewModel: It exposes those data streams which are relevant to the View. Moreover,
it serves as a link between the Model and the View .*/
