package com.phycaresolutions.mymap;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PatternActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pattern);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
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