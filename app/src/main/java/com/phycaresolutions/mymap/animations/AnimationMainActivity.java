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
https://stuff.mit.edu/afs/sipb/project/android/docs/guide/topics/graphics/2d-graphics.html
     // INSIDE Recyclerview xml
      android:layoutAnimation="@anim/layout_animation"
        app:layout_behavior="@string/appbar_scrolling_view_behavior"

    // Inside Activity class
     int resId = 0;

        switch (v.getId()) {
            case R.id.fall_down_button:
                resId = R.anim.layout_animation_fall_down;
                break;
            case R.id.btn_slide_up:
                resId = R.anim.layout_animation_slide_up;
                break;
            case R.id.btn_rotate_in:
                resId = R.anim.layout_animation_rotate_in;
                break;
            case R.id.btn_scale_in:
                resId = R.anim.layout_animation_scale_in;
                break;
        }

        if (resId != 0) {
            // Set animation for RecyclerView
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
            recyclerView.setLayoutAnimation(animation);
            recyclerViewAdapter.notifyDataSetChanged();
        }

   ObjectAnimator:
1.
ObjectAnimator animator = ObjectAnimator.ofFloat(targetView, "translationX", 0f, 100f);
animator.setDuration(1000);  // 1 second
animator.start();
2 .Group multiple animations
ObjectAnimator scaleX = ObjectAnimator.ofFloat(targetView, "scaleX", 1f, 1.5f);
ObjectAnimator scaleY = ObjectAnimator.ofFloat(targetView, "scaleY", 1f, 1.5f);
AnimatorSet animatorSet = new AnimatorSet();
animatorSet.playTogether(scaleX, scaleY);
animatorSet.setDuration(1000);
animatorSet.start();
3. Animate multiple properties
PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.5f);
PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.5f);
ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(targetView, pvhX, pvhY);
animator.setDuration(1000);
animator.start();
4.
PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.5f);
PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.5f);
ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(targetView, pvhX, pvhY);
animator.setDuration(1000);
animator.start();
5.
Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
Keyframe kf1 = Keyframe.ofFloat(0.5f, 200f);
Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
PropertyValuesHolder pvh = PropertyValuesHolder.ofKeyframe("translationX", kf0, kf1, kf2);
ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(targetView, pvh);
animator.setDuration(2000);
animator.start();
6.
ObjectAnimator fadeOut = ObjectAnimator.ofFloat(targetView, "alpha", 1f, 0f);
fadeOut.setDuration(2000);
fadeOut.start();
7.
ObjectAnimator bounceAnim = ObjectAnimator.ofFloat(targetView, "translationY", 0f, 300f);
bounceAnim.setInterpolator(new BounceInterpolator());
bounceAnim.setDuration(2000);
bounceAnim.start();
8.
ObjectAnimator moveRight = ObjectAnimator.ofFloat(targetView, "translationX", 0f, 300f);
ObjectAnimator moveDown = ObjectAnimator.ofFloat(targetView, "translationY", 0f, 300f);
AnimatorSet set = new AnimatorSet();
set.playSequentially(moveRight, moveDown);
set.setDuration(2000);
set.start();
9 Drawable animation:
<animation-list xmlns:android="http://schemas.android.com/apk/res/android"
    android:oneshot="true">
    <item android:drawable="@drawable/rocket_thrust1" android:duration="200" />
    <item android:drawable="@drawable/rocket_thrust2" android:duration="200" />
    <item android:drawable="@drawable/rocket_thrust3" android:duration="200" />
</animation-list>
 ImageView rocketImage = (ImageView) findViewById(R.id.rocket_image);
rocketImage.setBackgroundResource(R.drawable.rocket_thrust);
AnimationDrawable  animationDrawable = (AnimationDrawable) rocketImage.getBackground();
animationDrawable.start();
10. Intent to Activity Animations
overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
----------------  OR --------------------
Intent myIntent = new Intent(context, MyActivity.class);
ActivityOptions options =
        ActivityOptions.makeCustomAnimation(context, R.anim.in_from_right, R.anim.out_to_left);
context.startActivity(myIntent, options.toBundle());
slide_in_left.xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
                    android:duration="@android:integer/config_mediumAnimTime"
                    android:fromXDelta="-100%p"
                    android:toXDelta="0" />
</set>
slide_in_right.xml

<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
	<translate
		android:duration="@android:integer/config_mediumAnimTime"
		android:fromXDelta="100%p"
		android:toXDelta="0" />
</set>


slide_out_left.xm
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
	<translate
		android:duration="@android:integer/config_mediumAnimTime"
		android:fromXDelta="0"
		android:toXDelta="-100%p" />
</set>


slide_out_right.xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
	<translate
		android:duration="@android:integer/config_mediumAnimTime"
		android:fromXDelta="0"
		android:toXDelta="100%p" />
</set>

OR---------
<item name="android:windowAnimationStyle">
                 @style/MyCustomActivityAnimation</item>

<style name="MyCustomActivityAnimation"
                    parent="@android:style/Animation.Activity">
        <item name="android:activityOpenEnterAnimation">
                    @anim/slide_in_right</item>
        <item name="android:activityOpenExitAnimation">
                    @anim/slide_out_left</item>
        <item name="android:activityCloseEnterAnimation">
                    @anim/slide_in_left</item>
        <item name="android:activityCloseExitAnimation">
                    @anim/slide_out_right</item>
    </style>




*/
