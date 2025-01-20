package com.phycaresolutions.mymap.backgroundservices;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.phycaresolutions.mymap.R;

import kotlin.Suppress;

public class BackGroundMainActivity extends AppCompatActivity implements MyCallBack {
    MyService myService;
    ServiceConnection serviceConnection;
    MyReceiver myReceiver;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_back_ground_main);
        tv= findViewById(R.id.textView4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        /*OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyBackgroundWorker.class).build();
        WorkManager.getInstance(this).enqueue(workRequest);*/

       /* PeriodicWorkRequest periodicWorkRequest =
                new PeriodicWorkRequest.Builder(MyBackgroundWorker.class, 1, TimeUnit.SECONDS)
                        .build();

        WorkManager.getInstance(this).enqueue(periodicWorkRequest);*/



         /*serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyService.MyBinder binder = (MyService.MyBinder) service;
                 myService = binder.getMyservice();
                 myService.registerCallBack(BackGroundMainActivity.this);

                Log.i ("MyService DATA", "DATA: "+ myService.getCounter());
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);*/

        myReceiver = new MyReceiver();
        myReceiver.setListener(this);
        IntentFilter filter = new IntentFilter("YourAction");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(myReceiver, filter,RECEIVER_EXPORTED);
        }else {
                    registerReceiver(myReceiver, filter);
        }
        Intent serviceIntent = new Intent(this, MyService.class);
        startService(serviceIntent);
    }
    @Override
    protected void onStart() {
        super.onStart();
      //  Intent intent = new Intent(this, MyService.class);
      //  bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }


    @Override
    public void getResult(int result) {
          tv.append("\n"+result);
    }
}