package com.phycaresolutions.mymap.animations;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.phycaresolutions.mymap.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationMainActivity extends AppCompatActivity {

    Spinner sp1,sp2;
    private Map<String, List<String>> districtsByState;
    ArrayAdapter<String> adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_main);
        sp1 = findViewById(R.id.spinner1);
        sp2 = findViewById(R.id.spinner2);
        List<String> states = Arrays.asList("state1","state2","state3");
        districtsByState = new HashMap<>();
        districtsByState.put("state1",Arrays.asList("District-1","District-2","District-3"));
        districtsByState.put("state2",Arrays.asList("District - s2-1","District -s2-2"));
        districtsByState.put("state3", Collections.singletonList(""));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,states);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);




        adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,new ArrayList<>());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter2);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              // if (position!=0)
             String item = parent.getSelectedItem().toString();
             List<String> dis = districtsByState.get(item);
             if (dis!=null){
                 adapter2.clear();
                 adapter2.addAll(dis);
                 adapter2.notifyDataSetChanged();
             }
                //getPopup();
               /* BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(AnimationMainActivity.this);
                bottomSheetDialog.setContentView(R.layout.item_sub_child);
                bottomSheetDialog.show();*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ImageView imageView = findViewById(R.id.imageView6);
        imageView.setBackgroundResource(R.drawable.vct);
        AnimationDrawable myAnimation = (AnimationDrawable) imageView.getBackground();

//Triggers in somewhere else in a thread
        myAnimation.start();
       /* myButton = findViewById(R.id.my_button);
        myView = findViewById(R.id.my_view);
        myView.setVisibility(View.INVISIBLE);
        myButton.setText("Slide Up");
        isUp=false;
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUp){
                    slideDown(myView);
                    myButton.setText("Slide up");
                }else {
                    slideUp(myView);
                    myButton.setText("Slide Down");
                }
                isUp=!isUp;

            }
        });*/
    }

    private void getPopup() {
        PopupMenu popupMenu = new PopupMenu(this,sp2);
        popupMenu.getMenuInflater().inflate(R.menu.main_menu2,popupMenu.getMenu());
        popupMenu.show();
    }


    private void slideUp(View myView) {
        myView.setVisibility(View.VISIBLE);
        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,myView.getHeight(),0);
        translateAnimation.setDuration(500);
        translateAnimation.setFillAfter(true);
        myView.startAnimation(translateAnimation);


    }

    private void slideDown(View myView) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,myView.getHeight());
        translateAnimation.setDuration(500);
        translateAnimation.setFillAfter(true);
        myView.startAnimation(translateAnimation);
    }
}
/*
https://stuff.mit.edu/afs/sipb/project/android/docs/guide/topics/graphics/2d-graphics.html*/
