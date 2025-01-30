package com.phycaresolutions.mymap.asyntaskreplace;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.CalendarContract;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.phycaresolutions.mymap.R;
import com.phycaresolutions.mymap.changelanguage.LocaleHelper;

import java.util.Calendar;

public class AsynReplaceMainActivity extends AppCompatActivity {
   TextView tv,tv2;
   HandlerThread handlerThread;
   Handler handler;
    String url ="https://reqres.in/api/users?page=1";
    Context context;
    Resources resources;
    boolean bb;
    RadioButton rb1,rb2;
    RadioGroup radioGroup;
    String lg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_asyn_replace_main);
        radioGroup = findViewById(R.id.radioGroup2);
        rb1 = findViewById(R.id.radioButton3);
        rb2 = findViewById(R.id.radioButton4);
        tv = findViewById(R.id.textView15);
        tv2 = findViewById(R.id.textView16);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (rb1.isChecked()){
            context = LocaleHelper.setLocale(AsynReplaceMainActivity.this, "te");
            tv.setText(context.getResources().getString(R.string.select_language));
            rb1.setText(context.getResources().getString(R.string.rb1));
            rb2.setText(context.getResources().getString(R.string.rb2));
            tv2.setText("ప్రాజెక్ట్ కోసం స్ట్రింగ్ వనరులు strings.xml ఫైల్\u200Cలలో ఉంటాయి. మీ ప్రాజెక్ట్ మీ యాప్ కోసం డిఫాల్ట్ భాషలో స్ట్రింగ్ వనరులను కలిగి ఉన్న డిఫాల్ట్ strings.xml ఫైల్\u200Cను కలిగి ఉంది, ఇది మీ యాప్ వినియోగదారులు ఎక్కువగా మాట్లాడాలని మీరు ఆశించే భాష. మీరు మీ యాప్\u200Cను ఉంచాలనుకునే ఇతర భాషల కోసం స్ట్రింగ్ వనరులను కలిగి ఉన్న strings.xml ఫైల్\u200Cలను కూడా అనువదించవచ్చు");

        }else {
            context = LocaleHelper.setLocale(AsynReplaceMainActivity.this, "en");
            tv.setText(context.getResources().getString(R.string.select_language));
            rb1.setText(context.getResources().getString(R.string.rb1));
            rb2.setText(context.getResources().getString(R.string.rb2));
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (R.id.radioButton3 == checkedId){
                    context = LocaleHelper.setLocale(AsynReplaceMainActivity.this, "te");
                    resources = context.getResources();
                    tv.setText(context.getResources().getString(R.string.select_language));
                    rb1.setText(context.getResources().getString(R.string.rb1));
                    rb2.setText(context.getResources().getString(R.string.rb2));
                    getSupportActionBar().setTitle(resources.getString(R.string.app_name));
                    lg = LocaleHelper.getLanguage(getApplicationContext());
                    Log.e("Language","LAN: "+lg);
                    tv2.setText("ప్రాజెక్ట్ కోసం స్ట్రింగ్ వనరులు strings.xml ఫైల్\u200Cలలో ఉంటాయి. మీ ప్రాజెక్ట్ మీ యాప్ కోసం డిఫాల్ట్ భాషలో స్ట్రింగ్ వనరులను కలిగి ఉన్న డిఫాల్ట్ strings.xml ఫైల్\u200Cను కలిగి ఉంది, ఇది మీ యాప్ వినియోగదారులు ఎక్కువగా మాట్లాడాలని మీరు ఆశించే భాష. మీరు మీ యాప్\u200Cను ఉంచాలనుకునే ఇతర భాషల కోసం స్ట్రింగ్ వనరులను కలిగి ఉన్న strings.xml ఫైల్\u200Cలను కూడా అనువదించవచ్చు");
                    ObjectAnimator moveRight = ObjectAnimator.ofFloat(tv, "translationX", 0f, 300f);
                    ObjectAnimator moveDown = ObjectAnimator.ofFloat(tv, "translationY", 0f, 300f);
                    AnimatorSet set = new AnimatorSet();
                    set.playSequentially(moveRight, moveDown);
                    set.setDuration(2000);
                    set.start();

                }else {
                    context = LocaleHelper.setLocale(AsynReplaceMainActivity.this, "en");
                    resources = context.getResources();
                    tv.setText(context.getResources().getString(R.string.select_language));
                    rb1.setText(context.getResources().getString(R.string.rb1));
                    rb2.setText(context.getResources().getString(R.string.rb2));
                    getSupportActionBar().setTitle(resources.getString(R.string.app_name));
                    lg = LocaleHelper.getLanguage(getApplicationContext());
                    Log.e("Language","LAN: "+lg);
                    tv2.setText("The string resources for a project are contained in strings.xml files. Your project has a default strings.xml file that contains string resources in the default language for your app, which is the language you expect most of your app users to speak. You can also have translated strings.xml files that contain string resources for other languages that you want your app to accommodate");



                }
            }
        });

        /*tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bb) {
                    context = LocaleHelper.setLocale(AsynReplaceMainActivity.this, "te");
                    resources = context.getResources();
                    tv.setText(resources.getString(R.string.language));
                    getSupportActionBar().setTitle(resources.getString(R.string.app_name));
                    bb=true;
                }else {
                    bb=false;
                    context = LocaleHelper.setLocale(AsynReplaceMainActivity.this, "en");
                    resources = context.getResources();
                    tv.setText(resources.getString(R.string.language));
                    getSupportActionBar().setTitle(resources.getString(R.string.app_name));
                }
            }
        });*/

    }
    private void initiateUPIPayment() {
        // Replace these values with your own details
        String upiId = "merchant@upi"; // Your PhonePe UPI ID (or any other UPI ID)
        String name = "Merchant Name"; // Merchant name
        String note = "Payment for order #12345"; // Transaction note
        String amount = "100.00"; // Transaction amount

        Uri uri = new Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("mc", "")
                .appendQueryParameter("tid", "1234567890")
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();

        Intent upiPaymentIntent = new Intent(Intent.ACTION_VIEW);
        upiPaymentIntent.setData(uri);
        upiPaymentIntent.putExtra(Intent.EXTRA_REFERRER, Uri.parse("android-app://com.android.chrome"));

        // Check if PhonePe or any other UPI-supported apps are installed
        if (upiPaymentIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(upiPaymentIntent, 1);
        } else {
            // Inform user to install a UPI payment app (PhonePe, Google Pay, Paytm, etc.)
            // You could redirect them to the Play Store or show a message
            Toast.makeText(this, "Please install a UPI-based app like PhonePe", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // Get response from UPI payment
                String transactionResponse = data.getStringExtra("response");
                handleUPIPaymentResponse(transactionResponse);
            } else {
                // Handle payment failure or cancellation
                Toast.makeText(this, "Payment failed or cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void handleUPIPaymentResponse(String response) {
        if (response == null || response.isEmpty()) {
            // Handle error response
            Toast.makeText(this, "Transaction failed", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the payment was successful or failed
        String[] responseParts = response.split("&");
        String status = null;
        for (String part : responseParts) {
            String[] keyValue = part.split("=");
            if (keyValue[0].equals("status")) {
                status = keyValue[1];
            }
        }

        if ("SUCCESS".equalsIgnoreCase(status)) {
            // Payment successful, process the response here
            Toast.makeText(this, "Payment successful", Toast.LENGTH_SHORT).show();
        } else {
            // Payment failed
            Toast.makeText(this, "Payment failed", Toast.LENGTH_SHORT).show();
        }
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