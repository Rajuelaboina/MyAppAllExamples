package com.phycaresolutions.mymap.backgroundservices

import android.content.Intent
import android.content.IntentFilter
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.phycaresolutions.mymap.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.Locale

class LocationBackGroundActivity : AppCompatActivity(), MyCallBack {
    lateinit var myReceiver: MyReceiver;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_location_back_ground)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        myReceiver = MyReceiver()
        val filter = IntentFilter("YourAction")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(myReceiver, filter, RECEIVER_EXPORTED)
        } else {
            registerReceiver(myReceiver, filter)
        }
        val serviceIntent = Intent(this,MyService::class.java)
        startService(serviceIntent)

    }

    override fun getResult(result: Int, latitude: Double, longitude: Double) {
        if (latitude != 0.0) {
            //tv.append("\nlatitude= $latitude\nlongitude= $longitude\n")
            Log.e("MyReceiver", "COUNT: {$latitude , $longitude}")
             GlobalScope.async {
                 val geocoder = Geocoder(applicationContext, Locale.getDefault())

                 val addresses = geocoder.getFromLocation(latitude, longitude, 1);
                 val address = addresses?.get(0)
                 val add = address?.locality
                 Log.e("MyReceiver", "COUNT: $add")

             }

        }
    }
}