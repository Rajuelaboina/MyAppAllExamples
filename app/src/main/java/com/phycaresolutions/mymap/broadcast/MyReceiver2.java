package com.phycaresolutions.mymap.broadcast;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MyReceiver2 extends BroadcastReceiver {
    public String phoneNumber = "";
    public String contactName = "";
    //private static SmsListener mListener;
    public Pattern p = Pattern.compile("(|^)\\d{6}");
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        boolean status = isNetworkAvailable(context);
        showLog("" + status);
        try {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING) && phoneNumber!=null){
                Toast.makeText(context,"Ringing State Number is - " + phoneNumber, Toast.LENGTH_SHORT).show();
                contactName = getContactName(context, phoneNumber);

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String action = intent.getAction();
        if (action != null && action.equals(Intent.ACTION_BATTERY_CHANGED)) {

            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
            int percentage = level * 100 / scale;
            Log.e("NetworkChangeReceiver", "level: " + level);
            if(percentage <=  20){
                // doing something like getting location
            }else {

            }
        }

        //  ---------
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;
            String msg_from;
            if (bundle != null){
                //---retrieve the SMS message received---
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();
                        Log.d("Exception caught",msg_from);
                        Log.d("Exception caught",msgBody);
                    }
                }catch(Exception e){
//                            Log.d("Exception caught",e.getMessage());
                }
            }
        }
    }

    public String getContactName(Context context, String phoneNumber) {
        if(phoneNumber == null){
            return null;
        }

        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        Cursor cursor = cr.query(uri, new String[] {ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);
        if(cursor == null) {
            return null;
        }
        String contactName = "Contacto no registrado";
        if(cursor.moveToFirst()) {
            contactName = cursor.getString(0);
        }
        if(!cursor.isClosed()) {
            cursor.close();
        }
        return contactName;
    }
    private Boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            return (activeNetwork != null && activeNetwork.isConnectedOrConnecting());
        } else {
            return false;
        }
    }

    private void showLog( String message) {
        Log.e("NetworkChangeReceiver", "" + message);
    }
}
// ad to MainActivity
/*
MyReceiver2 myReceiver = new MyReceiver2();
registerReceiver(myReceiver,new IntentFilter(new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)));
registerReceiver(myReceiver,new IntentFilter(new IntentFilter(Intent.ACTION_BATTERY_CHANGED)));
registerReceiver(myReceiver,new IntentFilter(new IntentFilter("android.provider.Telephony.SMS_RECEIVED")));
  */
