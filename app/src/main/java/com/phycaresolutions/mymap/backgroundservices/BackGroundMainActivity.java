package com.phycaresolutions.mymap.backgroundservices;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.model.LatLng;
import com.phycaresolutions.mymap.R;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BackGroundMainActivity extends AppCompatActivity implements MyCallBack {
    MyService myService;
    ServiceConnection serviceConnection;
    MyReceiver myReceiver;
    TextView tv;
    LocationListener locationListener;
    LocationManager locationManager;
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



        /* serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyService.MyBinder binder = (MyService.MyBinder) service;
                 myService = binder.getMyservice();
                Log.i ("DATA", "DATA<<<>><<>><><<>: "+  myService.getCount());

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);*/

        new MyAsyn().execute();


       /* myReceiver = new MyReceiver();
        myReceiver.setListener(this);
        IntentFilter filter = new IntentFilter("YourAction");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(myReceiver, filter,RECEIVER_EXPORTED);
        }else {
                    registerReceiver(myReceiver, filter);
        }

        Intent serviceIntent = new Intent(this, MyService.class);
        startService(serviceIntent);*/



    }
    @Override
    protected void onStart() {
        super.onStart();
      //  Intent intent = new Intent(this, MyService.class);
      //  bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }


    @Override
    public void getResult(int result, double latitude, double longitude) {
        //String addressLine="";



        /*if (latitude!=0) {
            tv.append("\n" + "latitude= " + latitude + "\n" + "longitude= " + longitude + "\n");
           *//* new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Your Code

                }
            }, 10000);*//*
            *//*ScheduledExecutorService backgroundExecutor = Executors.newSingleThreadScheduledExecutor();
            // Execute a task in the background thread after 3 seconds.
            backgroundExecutor.schedule(new Runnable() {
                @Override
                public void run() {
                    // Your code logic goes here
                    try {
                        Geocoder geocoder = new Geocoder(getApplicationContext());
                        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                        Address address = addresses.get(0);
                        String add = address.getLocality();
                        tv.append("\n" + "latitude= " + latitude + "\n" + "longitude= " + longitude + "\n" + add);
                    }  catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }, 15, TimeUnit.SECONDS);*//*

            // Create an executor that executes tasks in the main thread.
           *//* Executor mainExecutor = ContextCompat.getMainExecutor(this);
             // Create an executor that executes tasks in a background thread.
            ScheduledExecutorService backgroundExecutor = Executors.newSingleThreadScheduledExecutor();
             // Execute a task in the background thread.
            backgroundExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    // Your code logic goes here.

                    // Update UI on the main thread
                    mainExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            // You code logic goes here.
                            try {
                                Geocoder geocoder = new Geocoder(getApplicationContext());
                                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                                Address address = addresses.get(0);
                                String add = address.getLocality();
                                tv.append("\n" + "latitude= " + latitude + "\n" + "longitude= " + longitude + "\n" + add);
                            }  catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
            });*//*

           *//* ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {

            });*//*

           *//* try {
                Geocoder geocoder = new Geocoder(getApplicationContext());
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                Address address = addresses.get(0);
                String add = address.getAddressLine(0);
                tv.append("\n" + "latitude= " + latitude + "\n" + "longitude= " + longitude + "\n" + add);
            } catch (SocketTimeoutException e) {
                // Handle the timeout exception
                Log.e("MyService", "Timeout exception: " + e.getMessage());
                // Retry the operation or inform the user
            } catch (IOException e) {
                throw new RuntimeException(e);
            }*//*


        }*/
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdate(){

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                if (location!=null) {

                   // Latitude=location.getLatitude();
                    //Longitude =  location.getLongitude();
                    // intent.putExtra("Latitude",location.getLatitude());
                    // intent.putExtra("Longitude",location.getLongitude());
                    Log.e("LOCATION", "Location<>>>>>>>: " + location.getLatitude());
                    Log.e("LOCATION", "Location<>>>>>>>: " + location.getLongitude());
                    String string = location.getProvider();
                    Log.e("LOCATION", "Location<>>>>>>>: " + string);
                    try {
                        Geocoder geocoder = new Geocoder(getApplicationContext());
                        List<Address> addresses = geocoder.getFromLocation(17.406496666666666, 78.47724333333333, 1);
                        Address address = addresses.get(0);
                        String add = address.getLocality();
                        Log.e("LOCATION", "Location<>>>>>>>: " + add);
                    }  catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        };
        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
        } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);
        }
    }

    class MyAsyn extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            try {
                Geocoder geocoder = new Geocoder(getApplicationContext());
                List<Address> addresses = geocoder.getFromLocation(17.406496666666666, 78.47724333333333, 1);
                Address address = addresses.get(0);
                String add = address.getLocality();
                Log.e("LOCATION", "Location<>>>>>>>: " + add);
            }  catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "";
        }
    }
}