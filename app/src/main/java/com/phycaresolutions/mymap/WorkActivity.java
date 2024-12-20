package com.phycaresolutions.mymap;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkActivity extends AppCompatActivity {
     RecyclerView rc;
    Address address;
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        rc = findViewById(R.id.recyclerView2);
        rc.setVisibility(View.VISIBLE);
        List<ItemDetails> list = new ArrayList<>();

        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        client.getLastLocation().addOnSuccessListener(WorkActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location!=null){
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> listAddress = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                        address =  listAddress.get(0);
                        Log.e("Location Data",address.getLocality() + "\n" +
                                address.getAdminArea() + "\n" +
                                address.getAddressLine(0) + "\n" +
                                address.getSubAdminArea()+ "\n" +
                                address.getSubLocality()+ "\n" +
                                address.getPhone()+ "\n" +
                                address.getPostalCode()+ "\n" +
                                address.getUrl()+ "\n" );
                        address.getLocality();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        list.add(new ItemDetails(address.getLocality() + "\n" +
                address.getAdminArea() + "\n" +
                address.getAddressLine(0) + "\n" +
                address.getSubAdminArea()+ "\n" +
                address.getSubLocality(),500.20));
        list.add(new ItemDetails("itemName1",500.20));
        list.add(new ItemDetails("itemName2",450.20));
        list.add(new ItemDetails("itemName3",7500.20));
        list.add(new ItemDetails("itemName4",800.20));
        list.add(new ItemDetails("itemName5",370.20));
        WorkAdapter adapter = new WorkAdapter(getApplicationContext(),list);
        rc.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rc.setAdapter(adapter);

        /*ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @SuppressLint("MissingPermission")
            @Override
            public void run() {

               *//* try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }*//*


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });*/



    }

    public class ItemDetails{
        String itemName;
        double itemCost;

        public ItemDetails(String itemName, double itemCost) {
           this.itemCost = itemCost;
            this.itemName = itemName;
        }
    }


}
// Data storage , Network secyrity,Code , Input validation, Permissions, othe
// app security Level
/*
1. Data Storage
Internal Storage: Store sensitive data, such as user credentials or API keys, in internal storage using the getFilesDir() or getCacheDir() methods. Internal storage is private to your app and is sandboxed from other apps.
Encrypted Shared Preferences: Use the EncryptedSharedPreferences class to store sensitive data in Shared Preferences with encryption. This provides an extra layer of security for data that needs to be persisted across app sessions.
Android Keystore System: Utilize the Android Keystore System to store cryptographic keys securely. This system provides hardware-backed protection for keys, making them more resistant to extraction
2. Network Security
HTTPS: Always use HTTPS for network communication to encrypt data in transit and protect it from eavesdropping.
Certificate Pinning: Implement certificate pinning to verify the server's certificate against a known good certificate, preventing man-in-the-middle attacks.
Network Security Configuration: Define a network security configuration file to enforce HTTPS and other security policies for your app's network connections.
3. Code Security
ProGuard: Enable ProGuard or R8 to obfuscate your code, making it more difficult for reverse engineers to understand your app's logic.
Root Detection: Implement root detection mechanisms to identify if the device is rooted and take appropriate actions, such as disabling sensitive features or displaying a warning.
Tamper Detection: Use techniques like checksum verification or code integrity checks to detect if your app's code has been modified.
4. Input Validation
Sanitize User Input: Always sanitize user input before using it in your app to prevent injection attacks, such as SQL injection or cross-site scripting (XSS).
Validate Data Formats: Validate data formats, such as email addresses or phone numbers, to ensure they are in the expected format.
Limit Input Length: Limit the length of user input to prevent buffer overflow attacks
 5. Permissions
Request Only Necessary Permissions: Request only the permissions that your app actually needs to function. Avoid requesting unnecessary permissions that could potentially expose sensitive data.
Use Intents: Use intents to defer permissions to other apps, reducing the attack surface of your app.
6.Other Security Measures
Secure Random Number Generation: Use a secure random number generator, such as SecureRandom, for generating cryptographic keys or other sensitive data.
Regular Security Audits: Conduct regular security audits of your app to identify and address potential vulnerabilities.
Stay Updated: Keep your app's dependencies and Android SDK tools updated to benefit from the latest security patches and improvements. Example (Encrypted Shared Preferences)*/

//  differences between Executors and Coroutines in Android development
// What is a Thread Pool?
// What is Multithreading?
// Product flavours
// mvvm  model -- data and business logic
 //      view -- Ui handle the user interactions
 //      viewModel -- Expose the data and commands to the view and viewmodel user interactions
