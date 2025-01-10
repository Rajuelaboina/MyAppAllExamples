package com.phycaresolutions.mymap.viewpager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.phycaresolutions.mymap.R;
import com.phycaresolutions.mymap.restfull.ApiService;
import com.phycaresolutions.mymap.restfull.ViewPagerAdapter;

public class ViewPagerActivity extends AppCompatActivity {
   ApiService apiService;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_pager);
        ViewPager viewPager = findViewById(R.id.viewPager);
        sliderDotspanel = findViewById(R.id.SliderDots);

         int[] images = {R.drawable.home_24, R.drawable.img, R.drawable.dashboard_24,
                R.drawable.doctor};
       /* UserViewPageAdapter adapter = new UserViewPageAdapter(getApplicationContext(),images);
        viewPager.setAdapter(adapter);*/
        ViewPagerAdapter adapter1 = new ViewPagerAdapter(getApplicationContext(),images);
        viewPager.setAdapter(adapter1);
        dotscount = adapter1.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.selected_dot));

        /*viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/
       /* apiService = ServiceInstance.getRetroFitInstance().create(ApiService.class);
        Call<Details> call = apiService.getAllUsers();
        call.enqueue(new Callback<Details>() {
            @Override
            public void onResponse(Call<Details> call, Response<Details> response) {
                Log.e("Responce >>>>>>","<>>>>>>>>>>>> :   "+response.code());
                Details users = response.body();

                UserViewPageAdapter adapter = new UserViewPageAdapter(getApplicationContext(),users.getData());
                viewPager.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Details> call, Throwable t) {
                Log.e("Responce >>>>>>","<>>>>>>>>>>>> :   "+t.getLocalizedMessage());
                Log.e("Responce >>>>>>","<>>>>>>>>>>>> :   "+t.getMessage());
            }
        });*/

    }
}