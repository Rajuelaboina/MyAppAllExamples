package com.phycaresolutions.mymap;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class ViewPager2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_pager2);
        ArrayList<Integer> arrayList = new ArrayList();
       /* arrayList.add("https://cdn.pixabay.com/photo/2021/01/09/02/13/manhattan-5901178_960_720.jpg");
        arrayList.add("https://cdn.pixabay.com/photo/2020/11/22/20/45/venice-5767937_960_720.jpg");
        arrayList.add("https://cdn.pixabay.com/photo/2021/01/10/12/00/road-5904909_640.jpg");*/
        /*arrayList.add("https://reqres.in/img/faces/1-image.jpg");
        arrayList.add("https://reqres.in/img/faces/1-image.jpg");
        arrayList.add("https://reqres.in/img/faces/1-image.jpg");*/
        arrayList.add(R.drawable.img);
        arrayList.add(R.drawable.doctor);
        arrayList.add(R.drawable.dashboard_24);
        arrayList.add(R.drawable.baseline_account_circle_24);
        arrayList.add(R.drawable.img);

        ViewPager2 viewPager2 = findViewById(R.id.view_Pager);
        TabLayout tabLayout = findViewById(R.id.into_tab_layout);
        ViewPagerRecyclerAdapter adapter = new ViewPagerRecyclerAdapter(getApplicationContext(),arrayList);
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout,viewPager2,(tab, position) -> {

        }).attach();
       // tabLayout.setupWithViewPager(viewPager2);

    }
}