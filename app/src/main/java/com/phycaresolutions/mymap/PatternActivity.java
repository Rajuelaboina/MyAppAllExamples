package com.phycaresolutions.mymap;

import android.graphics.Point;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.List;

public class PatternActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* ConnectDotsView dotToDotView = new ConnectDotsView(this, null);
        setContentView(dotToDotView);*/
        setContentView(R.layout.activity_pattern);
      /*  EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pattern);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

       /* List<Point> points = new ArrayList<Point>();
        Point p1 = new Point(20, 20);
        points.add(p1);
        Point p2 = new Point(100, 100);
        points.add(p2);
        Point p3 = new Point(200, 250);
        points.add(p3);
        Point p4 = new Point(280, 400);
        points.add(p4);
        Point p5 = new Point(350, 600);
        points.add(p5);
        Point p6 = new Point(400, 500);
        points.add(p6);
        Point p7 = new Point(650, 800);
        points.add(p7);
        Point p8 = new Point(650, 400);
        points.add(p8);
        Point p9 = new Point(650, 800);
        points.add(p9);
        ConnectDotsView connectDotsView = (ConnectDotsView) findViewById(R.id.connect_dots_view);
        connectDotsView.setPoints(points);*/
    }
}




///1 https://github.com/geftimov/android-patternview?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=1495
//2 https://github.com/aritraroy/PatternLockView
//3 https://github.com/zhanghai/PatternLock
// 4 https://github.com/xyxyLiu/PatternLockView
// https://github.com/l7naive/pattern-lock
// https://github.com/maxwroc/vanilla-pattern-lock
//22  https://github.com/imanshul/ConnectingDotsProblem
// https://www.google.com/search?q=dot%20joining%20drawing&udm=2&rlz=1C1CHBF_enIN880IN880&hl=en&sa=X&ved=0CCMQtI8BKAJqFwoTCJjZ4tGX0ooDFQAAAAAdAAAAABAQ&biw=1366&bih=641&dpr=1
// https://github.com/AnjayGoel/dots/tree/master/app/src/main
// https://github.com/lecho/android_samples/blob/master/connectdots/src/lecho/sample/connectdots/MainActivity.java
// https://github.com/frideosapps/pair_game/tree/master/android/app
// app store link
// https://codecanyon.net/checkout/106780018/create_account

// Dot connect game code in android studio java github

// https://github.com/irfaan008/IRBottomNavigationView

//https://github.com/zagori/BottomNavBar?tab=readme-ov-file