package com.phycaresolutions.mymap.animations;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import com.phycaresolutions.mymap.R;

public class SlideAnimationActivity extends AppCompatActivity {
    boolean isShowing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slide_animation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViewById(R.id.testBtn).setOnClickListener(v -> {
            showSlidingPanel(!isShowing);
        });
    }
    private void showSlidingPanel(boolean show) {
        ViewGroup parent = findViewById(R.id.main);
        View layout = findViewById(R.id.layout);
        //hide
        Transition transition = new Slide(Gravity.START);
        transition.setDuration(450);
        transition.addTarget(R.id.layout);
        transition.setInterpolator(new AccelerateDecelerateInterpolator());
        TransitionManager.beginDelayedTransition(parent, transition);
        layout.setVisibility(show ? View.VISIBLE : View.GONE);
        isShowing = show;
    }
}