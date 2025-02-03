package com.phycaresolutions.mymap.slideNavigation;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.phycaresolutions.mymap.R;
import com.phycaresolutions.mymap.userfragmnet.HomeFragment;

public class SlideNavActivity extends AppCompatActivity {
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_slide_nav);
        drawer = findViewById(R.id.main);
       Toolbar toolbar = drawer.findViewById(R.id.toolbar);

        // Create an ActionBarDrawerToggle to handle
        // the drawer's open/close state
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.nav_open, R.string.nav_close);

        // Add the toggle as a listener to the DrawerLayout
        drawer.addDrawerListener(toggle);

        // Synchronize the toggle's state with the linked DrawerLayout
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        // default fragment
        navigationView.setCheckedItem(R.id.nav_home);
        Fragment fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,fragment).commit();

        // navigation select item action
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               /* Fragment fragment = null;
                if (item.getItemId() == R.id.nav_home){
                    fragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,fragment).commit();


                }
                else if (item.getItemId() == R.id.nav_gallery){
                    fragment = new GalleryFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,fragment).commit();

                }
                else if (item.getItemId() == R.id.nav_slideshow){
                    fragment = new SlideshowFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,fragment).commit();

                }*/
                drawer.closeDrawers();
                return true;
            }
        });
    }
}