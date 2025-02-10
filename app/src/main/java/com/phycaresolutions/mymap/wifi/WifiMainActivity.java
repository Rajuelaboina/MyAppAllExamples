package com.phycaresolutions.mymap.wifi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.phycaresolutions.mymap.R;

import java.util.ArrayList;
import java.util.List;

public class WifiMainActivity extends AppCompatActivity {
    boolean connected = false;
    String desiredMacAddress = "router mac address";
    ListView tv22,tv23;
    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wifi_main);
        aSwitch = findViewById(R.id.switch1);
        tv22 = findViewById(R.id.textView22);
        tv23 = findViewById(R.id.textView23);
        String ssid = null;
        ConnectivityManager connManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                      if (Build.VERSION.SDK_INT <Build.VERSION_CODES.Q){

                          if (!wifiManager.isWifiEnabled()) {
                              wifiManager.setWifiEnabled(true);
                              aSwitch.setText("Wifi ONN");
                          } else {
                              wifiManager.setWifiEnabled(false);
                              aSwitch.setText("Wifi OFF");
                          }
                      }else {// if it is Android Q and above go for the newer way    NOTE: You can also use this code for less than android Q also
                          Intent panelIntent = new Intent(Settings.Panel.ACTION_WIFI);
                          //startActivityForResult(panelIntent, 1);
                          launcher.launch(panelIntent);
                      }


            }
        });


        if (networkInfo.isConnected()) {

            final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null && !(connectionInfo.getSSID().equals(""))) {
                //if (connectionInfo != null && !StringUtil.isBlank(connectionInfo.getSSID())) {
                ssid = connectionInfo.getSSID();
                Log.e("BSSID", "ssid>>>>>> : " + ssid);
            }
            // Get WiFi status MARAKANA
            WifiInfo info = wifiManager.getConnectionInfo();
            String textStatus = "";
            textStatus += "\n\nWiFi Status: " + info.toString();
            String BSSID = info.getBSSID();
            Log.e("BSSID", "bssid>>>>>> : " + BSSID);
            String MAC = info.getMacAddress();
            Log.e("BSSID", "MAC>>>>>> : " + MAC);

            @SuppressLint("MissingPermission") List<ScanResult> results = wifiManager.getScanResults();
            ScanResult bestSignal = null;
            int count = 1;
            String etWifiList = "";
            List<String> list = new ArrayList<>();
            for (ScanResult result : results) {
                etWifiList += count++ + ". " + result.SSID + " : " + result.level + "\n" +
                        result.BSSID + "\n" + result.capabilities + "\n" + result.level+"\n"+
                        "\n=======================\n";
                Log.v("TAG", "from SO: \n" + etWifiList);
               list.add(count++ + ". " + result.SSID + " : " + result.level + "\n" +
                       result.BSSID + "\n" + result.capabilities + "\n" + result.level+"\n"+
                       "\n=======================\n");
            }

            tv22.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,list));

            // List stored networks
            @SuppressLint("MissingPermission") List<WifiConfiguration> configs = wifiManager.getConfiguredNetworks();
            List<String> list2 = new ArrayList<>();
            for (WifiConfiguration config : configs) {
                textStatus += "\n\n" + config.toString();
                Log.v("TAG", "from marakana: \n" + textStatus);
                list2.add(config.toString());
            }
            tv22.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,list2));
        }
    }
    ActivityResultLauncher<Intent> launcher =registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
                  if (result.getData()!=null) {
                       Uri uri = result.getData().getData();

                  }
                   /* if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        intent1.putExtra(Intent.EXTRA_STREAM,data.getData());
                    }*/
        }
    });
}