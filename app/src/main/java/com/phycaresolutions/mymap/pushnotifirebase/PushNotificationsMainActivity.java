package com.phycaresolutions.mymap.pushnotifirebase;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.phycaresolutions.mymap.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PushNotificationsMainActivity extends AppCompatActivity {
    TextView tv;
   String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notifications_main);
        tv = findViewById(R.id.textView21);
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                Log.e("TOKEN", "Token>>>>>:  " + s);


                tv.setText("" + s);
            }
        });
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // pushFCMNotification(s);
                    String strServerKey = "AIzaSyDV0XQouw3OLtIDciygyMadnnoVBWiLhF0";  //""AIzaSyDgwyzuFBpSzLHEYdvdTqOy7t8VCNX0SdY";
                    String strAuthorization = "key=" + strServerKey;
                   // String strFcmUrl="https://fcm.googleapis.com/fcm/send";
                    String strFcmUrl = "https://fcm.googleapis.com/v1/projects/mymap-a31dc/messages:send";

                    //String strFcmUrl="https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages/send";
                    //Put JSON data...

                    JSONObject jsonMain = new JSONObject();
                    jsonMain.put("to", "ejxUHcTcS3G2Cj3qsY-N4w:APA91bFxLZPr8IbEfH9rV5gkji4jf5NVmwJgtsX-120m3anK83M6NuWhx0CFt3fllNl718BnWD2eO09eGSsLALJ9yDjBhVqehTwyq6EGkh45MODn8t5TLfc");

                    JSONObject jsonNotification = new JSONObject();
                    jsonNotification.put("title", "Notification Title");
                    jsonNotification.put("body", "Notification Body");
                    jsonMain.put("notification", jsonNotification);

                    JSONObject jsonData = new JSONObject();
                    jsonData.put("title", "Data Payload Title");
                    jsonData.put("body", "Data Payload Body");
                    jsonMain.put("data", jsonData);

                    String sendJsonData=jsonMain.toString();


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, strFcmUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //
                                    Log.e("TAG","RES:>> "+response);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //
                        }
                    }) {
                        @Override
                        public byte[] getBody() {
                            return sendJsonData.getBytes();
                        }

                        @Override
                        public Map<String, String> getHeaders() {
                            Map<String, String> params = new HashMap<>();
                            params.put("Content-Type", "application/json; charset=UTF-8");
                            params.put("Authorization", strAuthorization);
                            return params;
                        }
                    };
                    Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
                    //.addToRequestQueue(stringRequest);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }


}
   //



// https://documentation.onesignal.com/docs/android-firebase-credentials
/* Node.js Example
const admin = require('firebase-admin');

admin.initializeApp({
    credential: admin.credential.cert('path/to/your/serviceAccountKey.json'),
});


        const message = {
token: 'FCM_DEVICE_TOKEN',
notification: {
title: 'Test Title',
body: 'This is a test message',
        },
        };

        admin.messaging().send(message)
  .then(response => {
    console.log('Successfully sent message:', response);
})
        .catch(error => {
        console.log('Error sending message:', error);
  });
*/
