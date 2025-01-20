package com.phycaresolutions.mymap.backgroundservices;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
     Context context;
     Timer timer;
     TimerTask timerTask;
     int counter = 0;
     IBinder iBinder = new MyBinder();
    MyCallBack myCallBack;
    public void registerCallBack(MyCallBack myCallBack) {
          this.myCallBack = myCallBack;
    }

    public class MyBinder extends Binder {
         MyService getMyservice(){
             return MyService.this;
         }
     }
    public MyService(Context applicationContext) {
        super();
        context = applicationContext;
        Log.i("HERE", "here service created!");
    }
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
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Run your long task here
        startTimer();

        return START_STICKY;  // Restart the service if it's killed
    }

    private void startTimer() {
       timer = new Timer();
        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, to wake up every 1 second
        timer.schedule(timerTask, 1000, 1000); //
    }

    private void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {
                Log.i("in timer", "in timer ++++  " + (counter++));
                if (myCallBack!=null){
                myCallBack.getResult(counter);
                }
                Intent intent = new Intent("YourAction");
                intent.putExtra("COUNT",counter);
                sendBroadcast(intent);
            }
        };
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("EXIT", "ondestroy!");

      /*  Intent broadcastIntent = new Intent("ac.in.ActivityRecognition.RestartSensor");
        sendBroadcast(broadcastIntent);
        stoptimertask();*/
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
        return iBinder;
    }
    public int getCounter(){
        return counter;
    }
}