package com.phycaresolutions.mymap.toolbar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.phycaresolutions.mymap.R;

public class ToolBarMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tool_bar_main);
      /*  ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
        /* Toolbar toolbar= findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);

         CollapsingToolbarLayout collapsingToolbar= findViewById(R.id.collapsingToolbar);
          collapsingToolbar.setTitle("Your Title");*/
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setSubtitle("ToolBar Demo");
        toolbar.inflateMenu(R.menu.main_menu);
        //toolbar.setLogo(R.drawable.baseline_account_circle_24);

       /* DrawerLayout drawer ;
        // Create an ActionBarDrawerToggle to handle
        // the drawer's open/close state
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.nav_open, R.string.nav_close);

        // Add the toggle as a listener to the DrawerLayout
        drawer.addDrawerListener(toggle);

        // Synchronize the toggle's state with the linked DrawerLayout
        toggle.syncState();*/
    }

    // 535-590 = 55
    // 14998-11990=3008
}