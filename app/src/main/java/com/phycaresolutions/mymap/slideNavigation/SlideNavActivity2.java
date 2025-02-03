package com.phycaresolutions.mymap.slideNavigation;

import android.os.Bundle;
import android.view.animation.OvershootInterpolator;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.phycaresolutions.mymap.R;
import com.phycaresolutions.mymap.userfragmnet.DashBoardFragment;
import com.phycaresolutions.mymap.userfragmnet.HomeFragment;
import com.phycaresolutions.mymap.userfragmnet.NotificationFragment;

import java.util.ArrayList;
import java.util.List;

public class SlideNavActivity2 extends AppCompatActivity implements ItemClickListener {
    DrawerLayout drawer;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_slide_nav2);
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
       /* navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               *//* Fragment fragment = null;
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

                }*//*
                drawer.closeDrawers();
                return true;
            }
        });*/
        rv=findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // add divider line between rows
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration( rv.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.horizontal_divider));
        rv.addItemDecoration(dividerItemDecoration);
        rv.setItemAnimator(new MyItemAnimator());
        ItemAdapter2 adapter = new ItemAdapter2(getListData());
        rv.setAdapter(adapter);
        ItemAdapter2.setOnItemClickListener(this);
    }
    private List<UserDetails> getListData() {
        List<UserDetails> list = new ArrayList();
        list.add(new UserDetails("Android","1234567890","In Summary PendingIntents are a powerful mechanism for deferring actions and delegating them to other apps or system services. They are commonly used in notifications, alarms, widgets, and other scenarios where you need to trigger actions at a later time or from another context. I hope this explanation is helpful! Let me know if you have any other questions."));
        list.add(new UserDetails("Java","99999999999","In Summary PendingIntents are a powerful mechanism for deferring actions and delegating them to other apps or system services. They are commonly used in notifications, alarms, widgets, and other scenarios where you need to trigger actions at a later time or from another context. I hope this explanation is helpful! Let me know if you have any other questions."));
        list.add(new UserDetails("Kotlin","8888888888","In Summary PendingIntents are a powerful mechanism for deferring actions and delegating them to other apps or system services. They are commonly used in notifications, alarms, widgets, and other scenarios where you need to trigger actions at a later time or from another context. I hope this explanation is helpful! Let me know if you have any other questions."));
        list.add(new UserDetails("Angular","7777777777","In Summary PendingIntents are a powerful mechanism for deferring actions and delegating them to other apps or system services. They are commonly used in notifications, alarms, widgets,"));
        list.add(new UserDetails("Reactive","5555555555","In Summary PendingIntents are a powerful mechanism for deferring actions and delegating them to other apps or system services. They are commonly used in notifications, alarms, widgets,"));
        list.add(new UserDetails("Dot Net","3333333333","In Summary PendingIntents are a powerful mechanism for deferring actions and delegating them to other apps or system services. They are commonly used in notifications, alarms, widgets, and other scenarios where you need to trigger actions at a later time or from another context. I hope this explanation is helpful! Let me know if you have any other questions."));
        list.add(new UserDetails("Android","1234567890","In Summary PendingIntents are a powerful mechanism for deferring actions and delegating them to other apps or system services. They are commonly used in notifications, alarms, widgets, and other scenarios where you need to trigger actions at a later time or from another context. I hope this explanation is helpful! Let me know if you have any other questions."));
        list.add(new UserDetails("Java","99999999999","In Summary PendingIntents are a powerful mechanism for deferring actions and delegating them to other apps or system services. They are commonly used in notifications, alarms, widgets, and other scenarios where you need to trigger actions at a later time or from another context. I hope this explanation is helpful! Let me know if you have any other questions."));
        list.add(new UserDetails("Kotlin","8888888888","In Summary PendingIntents are a powerful mechanism for deferring actions and delegating them to other apps or system services. They are commonly used in notifications, alarms, widgets, and other scenarios where you need to trigger actions at a later time or from another context. I hope this explanation is helpful! Let me know if you have any other questions."));
        list.add(new UserDetails("Angular","7777777777","In Summary PendingIntents are a powerful mechanism for deferring actions and delegating them to other apps or system services. They are commonly used in notifications, alarms, widgets,"));
        list.add(new UserDetails("Reactive","5555555555","In Summary PendingIntents are a powerful mechanism for deferring actions and delegating them to other apps or system services. They are commonly used in notifications, alarms, widgets,"));
        list.add(new UserDetails("Dot Net","3333333333","In Summary PendingIntents are a powerful mechanism for deferring actions and delegating them to other apps or system services. They are commonly used in notifications, alarms, widgets, and other scenarios where you need to trigger actions at a later time or from another context. I hope this explanation is helpful! Let me know if you have any other questions."));

        return list;
    }

    @Override
    public void onItemClick(UserDetails userDetails, int position) {
        drawer.closeDrawers();
        Fragment fragment = null;
        if (position == 0){
            fragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,fragment).commit();


        }
        else if (position == 1){
            fragment = new NotificationFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,fragment).commit();

        }
        else if (position == 2){
            fragment = new DashBoardFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,fragment).commit();

        }
    }


    // model class



    private class MyItemAnimator extends DefaultItemAnimator {
        @Override
        public boolean animateAdd(RecyclerView.ViewHolder holder) {
            /*holder.itemView.getAlpha();
            holder.itemView.animate().alpha(1f).setDuration(500).start();
            return true;*/
            holder.itemView.setTranslationY(holder.itemView.getHeight());
            holder.itemView.animate()
                    .translationY(0f)
                    .alpha(1f)
                    .setDuration(500)
                    .setInterpolator(new OvershootInterpolator())
                    .start();
            return true;
        }
    }
}