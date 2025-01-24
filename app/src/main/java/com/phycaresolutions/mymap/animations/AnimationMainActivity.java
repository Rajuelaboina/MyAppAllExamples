package com.phycaresolutions.mymap.animations;

import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.phycaresolutions.mymap.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationMainActivity extends AppCompatActivity {
    Button myButton;
    View myView;
    boolean isUp;
    Spinner sp1,sp2;
    private ArrayAdapter<String> parentAdapter;
    private ArrayAdapter<String> childAdapter;
    private List<String> fruits = Arrays.asList("Apple", "Banana", "Orange");
    private List<String> vegetables = Arrays.asList("Carrot", "Broccoli", "Spinach");
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
        districtsByState.put("state3",Arrays.asList(""));
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,states);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);




        adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,new ArrayList());
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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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



    private void slideUp(View myView) {
        /*myView.setVisibility(View.VISIBLE);
        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,myView.getHeight(),0);
        translateAnimation.setDuration(500);
        translateAnimation.setFillAfter(true);
        myView.startAnimation(translateAnimation);*/
      /*  ObjectAnimator animX = ObjectAnimator.ofFloat(myView, "x", 50f);
        ObjectAnimator animY = ObjectAnimator.ofFloat(myView, "y", 100f);
        AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.playTogether(animX, animY);
        animSetXY.start();*/

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
