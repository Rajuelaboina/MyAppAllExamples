package com.phycaresolutions.mymap.backgroundservices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    static MyCallBack myCallBack;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
       /* Bundle b = intent.getExtras();
        String yourValue = b.getString("counter");*/
        int count = intent.getIntExtra("COUNT",0);
        double Latitude = intent.getDoubleExtra("Latitude",0);
        double Longitude = intent.getDoubleExtra("Longitude",0);
        //Log.e("MyReceiver","COUNT: " + count + "\n" + Latitude + "\n" + Longitude);
        myCallBack.getResult(count,Latitude,Longitude);
    }
    public static void setListener(MyCallBack myCallBack2){
        myCallBack = myCallBack2;
    }


}