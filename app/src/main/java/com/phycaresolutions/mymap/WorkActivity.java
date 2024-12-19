package com.phycaresolutions.mymap;

import android.health.connect.datatypes.units.Length;
import android.net.Network;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class WorkActivity extends AppCompatActivity {
     RecyclerView rc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_work);
        rc = findViewById(R.id.recyclerView2);
        List<ItemDetails> list = new ArrayList<>();
        list.add(new ItemDetails("itemName1",500.20));
        list.add(new ItemDetails("itemName2",450.20));
        list.add(new ItemDetails("itemName3",7500.20));
        list.add(new ItemDetails("itemName4",800.20));
        list.add(new ItemDetails("itemName5",370.20));
        WorkAdapter adapter = new WorkAdapter(getApplicationContext(),list);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(adapter);
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
