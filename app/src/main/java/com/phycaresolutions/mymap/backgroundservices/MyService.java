package com.phycaresolutions.mymap.backgroundservices;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyService extends Service {
     Context context;
     Timer timer;
     TimerTask timerTask;
     int counter = 0;

    double Latitude;
    double Longitude;
    String addressLine;
    LocationListener locationListener;
    LocationManager locationManager;
    public MyService() {
    }

    private static final String CHANNEL_ID = "ForegroundServiceChannel";

    @SuppressLint("ForegroundServiceType")
    @Override
    public void onCreate() {
        super.onCreate();


       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Service Running")
                .setContentText("Long-running task in progress")
                .setSmallIcon(android.R.drawable.ic_notification_overlay)
                .build();
           startForeground(2,notification);*/
        //startForeground(1, notification);  // Start the service in the foreground
       // startTimer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Run your long task here
       // locationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        startTimer();

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Service Running")
                .setContentText("")
                .setSmallIcon(android.R.drawable.ic_notification_overlay)
                .build();
        startForeground(2,notification);*/
        return START_STICKY;  // Restart the service if it's killed
    }

    private void startTimer() {
       timer = new Timer();
        //initialize the TimerTask's job
        initializeTimerTask();
        // startLocationUpdate();

        //schedule the timer, to wake up every 1 second
        timer.schedule(timerTask, 1000, 1000); //
    }

    private void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {
                Log.i("in timer", "in timer ++++  " + (counter++));
                Intent intent = new Intent("YourAction");
                intent.putExtra("COUNT",counter);
                intent.putExtra("Latitude",Latitude);
                intent.putExtra("Longitude",Longitude);
                sendBroadcast(intent);
            }
        };
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("EXIT", "ondestroy!");
      //  locationManager.removeUpdates(locationListener);
        stoptimertask();
    }

    private void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdate(){

         locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                if (location!=null) {

                    Latitude=location.getLatitude();
                    Longitude =  location.getLongitude();
                   // intent.putExtra("Latitude",location.getLatitude());
                   // intent.putExtra("Longitude",location.getLongitude());
                    Log.e("LOCATION", "Location<>>>>>>>: " + location.getLatitude());
                    Log.e("LOCATION", "Location<>>>>>>>: " + location.getLongitude());
                   String string = location.getProvider();
                    Log.e("LOCATION", "Location<>>>>>>>: " + string);


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
}