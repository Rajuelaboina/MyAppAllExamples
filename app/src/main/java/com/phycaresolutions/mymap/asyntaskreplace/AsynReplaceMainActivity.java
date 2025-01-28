package com.phycaresolutions.mymap.asyntaskreplace;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.CalendarContract;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.phycaresolutions.mymap.R;

import java.util.Calendar;

public class AsynReplaceMainActivity extends AppCompatActivity {
   TextView tv;
   HandlerThread handlerThread;
   Handler handler;
    String url ="https://reqres.in/api/users?page=1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_asyn_replace_main);
        tv = findViewById(R.id.textView15);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addEventToCalendar();
       /* // Event start and end time with date
        String mStartTime = "2025-01-28T09:00:00";
        String mEndTime = "2025-01-28T12:00:00";
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2025, 0, 28, 9, 30);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2025, 0, 28, 10, 30);
        Intent mIntent = new Intent(Intent.ACTION_EDIT);
        mIntent.setType("vnd.android.cursor.item/event");
        mIntent.putExtra("beginTime", beginTime.getTime());
        mIntent.putExtra("time", true);
        mIntent.putExtra("rule", "FREQ=YEARLY");
        mIntent.putExtra("endTime", endTime.getTime());
        mIntent.putExtra("title", "Raju Android Test work");
        mIntent.putExtra(Intent.EXTRA_EMAIL, "raju.elaboina@gmail.com");
        startActivity(mIntent);*/
        /*StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("RESPONSE", "DATA: "+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("RESPONSE", "DATA: "+error.getLocalizedMessage());
            }
        });
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);*/
        // 1 St way
       /* ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                 // do work on background
              //  String str = loadDataFromBackground();

                // update on UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       //tv.append(str);
                    }
                });
            }
        });*/
        // 2 way
       /* handlerThread = new HandlerThread("BackGround Thread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                // do work on background
                String str = loadDataFromBackground();
                // update on UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.append(str);
                    }
                });
            }
        });*/
    }
    // Method to add event to the calendar
    private void addEventToCalendar() {
        // Get content resolver
        ContentResolver contentResolver = getContentResolver();

        try {
            // Create ContentValues to insert the event details
            ContentValues values = new ContentValues();

            // Use Calendar object to set event time (10:00 AM on January 28, 2025)
            Calendar beginTime = Calendar.getInstance();
            beginTime.set(2025, Calendar.JANUARY, 28, 10, 0);  // Start at 10:00 AM
            long startMillis = beginTime.getTimeInMillis();

            Calendar endTime = Calendar.getInstance();
            endTime.set(2025, Calendar.JANUARY, 28, 11, 0);  // End at 11:00 AM
            long endMillis = endTime.getTimeInMillis();

            // Fill the event details into ContentValues
            values.put(CalendarContract.Events.DTSTART, startMillis);
            values.put(CalendarContract.Events.DTEND, endMillis);
            values.put(CalendarContract.Events.TITLE, "Test Event");
            values.put(CalendarContract.Events.DESCRIPTION, "This is a test event added programmatically.");
            values.put(CalendarContract.Events.CALENDAR_ID, 1); // Default calendar ID
            values.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance().getTimeZone().getID());


            // Insert the event into the calendar
            contentResolver.insert(CalendarContract.Events.CONTENT_URI, values);

            // Show a toast message to confirm the event was added
            Toast.makeText(this, "Event added to calendar!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to add event", Toast.LENGTH_SHORT).show();
        }
    }
    // Method to add event to calendar
  /*  private void addEventToCalendar() {
        try {
            ContentValues values = new ContentValues();

            // Set the event details (start time, end time, title, etc.)
            long startMillis = System.currentTimeMillis();  // Set current time as the start time
            long endMillis = startMillis + 60 * 60 * 1000;  // 1 hour event

            values.put(CalendarContract.Events.DTSTART, startMillis);
            values.put(CalendarContract.Events.DTEND, endMillis);
            values.put(CalendarContract.Events.TITLE, "Sample Event");
            values.put(CalendarContract.Events.DESCRIPTION, "This is a test event.");
            values.put(CalendarContract.Events.CALENDAR_ID, 1);  // Default calendar ID (you may need to check the correct one)
            values.put(CalendarContract.Events.EVENT_TIMEZONE, java.util.TimeZone.getDefault().getID());

            // Insert the event into the calendar
            getContentResolver().insert(CalendarContract.Events.CONTENT_URI, values);

            Toast.makeText(this, "Event added to calendar!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to add event", Toast.LENGTH_SHORT).show();
        }
    }*/
    private String loadDataFromBackground() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "this is loadFrom background";
    }
}