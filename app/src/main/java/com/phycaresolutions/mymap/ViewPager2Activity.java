package com.phycaresolutions.mymap;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.common.api.Api;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.phycaresolutions.mymap.restfull.ApiService;
import com.phycaresolutions.mymap.restfull.Details;
import com.phycaresolutions.mymap.restfull.ServiceInstance;
import com.phycaresolutions.mymap.utility.CheckNetWork;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPager2Activity extends AppCompatActivity {
    int currentPage = 0;
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
        arrayList.add(R.drawable.doctor);
        arrayList.add(R.drawable.doctor);
        arrayList.add(R.drawable.dashboard_24);
        arrayList.add(R.drawable.baseline_account_circle_24);
        arrayList.add(R.drawable.doctor);

        ViewPager2 viewPager2 = findViewById(R.id.view_Pager);
        TabLayout tabLayout = findViewById(R.id.into_tab_layout);
        ViewPagerRecyclerAdapter adapter = new ViewPagerRecyclerAdapter(getApplicationContext(),arrayList);
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout,viewPager2,(tab, position) -> {

        }).attach();

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPage == adapter.getItemCount()){
                    currentPage = 0;
                }
                viewPager2.setCurrentItem(currentPage++, true);
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
             handler.post(runnable);
            }
        },500,1000);
       // tabLayout.setupWithViewPager(viewPager2);
        if (CheckNetWork.getNetWorkState(getApplicationContext())){
            Toast.makeText(getApplicationContext(),"Network  available",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(),"Network not available",Toast.LENGTH_LONG).show();
        }

       //ApiService apiService = ServiceInstance.getRetroFitInstance().create(ApiService.class).getAllUsers();
        Call<Details> call = ServiceInstance.getRetroFitInstance().create(ApiService.class).getAllUsers();
        call.enqueue(new Callback<Details>() {
            @Override
            public void onResponse(Call<Details> call, Response<Details> response) {

            }

            @Override
            public void onFailure(Call<Details> call, Throwable t) {

            }
        });

    }
}