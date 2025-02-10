package com.phycaresolutions.mymap.bluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.phycaresolutions.mymap.R;

import java.util.Set;

public class BlueToothMainActivity extends AppCompatActivity {
   Button btnGet;
    TextView tvName, tvMac;
    BluetoothAdapter bAdapter;
    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth_main);
        aSwitch = findViewById(R.id.button6);
        btnGet = findViewById(R.id.btnGet);
        tvName = findViewById(R.id.nameTv);
        tvMac = findViewById(R.id.macAddressTv);
        // Initializing the Bluetooth Adapter
        bAdapter = BluetoothAdapter.getDefaultAdapter();

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Checks if Bluetooth Adapter is present
                if (bAdapter == null) {
                    Toast.makeText(getApplicationContext(), "Bluetooth Not Supported", Toast.LENGTH_SHORT).show();
                } else {
                    // List all the bonded devices(paired)
                    @SuppressLint("MissingPermission") Set<BluetoothDevice> pairedDevices = bAdapter.getBondedDevices();
                    if (pairedDevices.size() > 0) {
                        for (BluetoothDevice device : pairedDevices) {

                            // get the device name
                            @SuppressLint("MissingPermission") String deviceName = device.getName();

                            // get the mac address
                            String macAddress = device.getAddress();

                            // append in the two separate views
                            tvName.append(deviceName + "\n");
                            tvMac.append(macAddress + "\n");
                        }
                    }
                }
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               // bAdapter.enable();
                if (isChecked) {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    //startActivityForResult(enableBtIntent, 10);
                    launcher.launch(enableBtIntent);
                }else {
                    bAdapter.disable();
                }
               /* if (bAdapter.isEnabled()) {
                    bAdapter.disable();
                    aSwitch.setText("Bluetooth is OFF");
                } else {
                    bAdapter.enable();
                    aSwitch.setText("Bluetooth is ON");
                }*/
            }
        });

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