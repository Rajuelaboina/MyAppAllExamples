package com.phycaresolutions.mymap;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bottom_test);


        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_dashboard,R.id.nav_notification
        ).build();

        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navView,navController);


      /*  navView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home){
                HomeFragment blankFragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main,blankFragment).commit();
            } else if (item.getItemId() == R.id.nav_dashboard) {
                DashBoardFragment blankFragment = new DashBoardFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main,blankFragment).commit();

            }
            else if (item.getItemId() == R.id.nav_notification) {
                HomeFragment blankFragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main,blankFragment).commit();

            }
            return true;
        });*/

    }
     /*@Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {
      *//*  if (item.getItemId() == R.id.Home){
            BlankFragment blankFragment = new BlankFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main,blankFragment).commit();
        } else if (item.getItemId() == R.id.Dashboard) {
            BlankFragment blankFragment = new BlankFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main,blankFragment).commit();

        }
        else if (item.getItemId() == R.id.Notification) {
            BlankFragment blankFragment = new BlankFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main,blankFragment).commit();

        }*//*

    }*/
}