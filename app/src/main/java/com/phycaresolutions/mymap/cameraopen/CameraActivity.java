package com.phycaresolutions.mymap.cameraopen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.phycaresolutions.mymap.R;

public class CameraActivity extends AppCompatActivity {
    Button btn,btn2;
    Uri uri;
    static final int CAMERA_PERMISSION_CODE = 101;
    Intent intent1 = new Intent(Intent.ACTION_SEND);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_camera);
        btn = findViewById(R.id.button4);
        btn2 = findViewById(R.id.button5);
       // intent1 = new Intent(Intent.ACTION_SEND);
        btn.setOnClickListener(v -> {
          // checkCameraPermission();
            /*Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
            someActivityResultLauncher.launch(intent);*/

           /* intent1.putExtra(Intent.EXTRA_SUBJECT,"this is demo");
            intent1.putExtra(Intent.EXTRA_TEXT,"hello");

            intent1.setType("text/plain");
            startActivity(Intent.createChooser(intent1,"Raju"));*/

        });
       btn2.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setType("*/*");
            //intent.setType("text/plain");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.putExtra("return-data", true);
            someActivityResultLauncher.launch(intent);
        });

    }
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    /*if (result.getData()!=null) {
                        uri = result.getData().getData();


                    }*/
                    /*if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        intent1.putExtra(Intent.EXTRA_STREAM,data.getData());
                    }*/
                }
    });

    private void checkCameraPermission() {
       /* if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(CameraActivity.this,new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
        }else {
            Toast.makeText(getApplicationContext(),"Permission granted",Toast.LENGTH_LONG).show();
            //opencamera();
        }*/
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(CameraActivity.this,new String[]{Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_CALL_LOG,Manifest.permission.READ_PHONE_NUMBERS},CAMERA_PERMISSION_CODE);
        }else {
            //Toast.makeText(getApplicationContext(),"Permission granted",Toast.LENGTH_LONG).show();
            //opencamera();
            getPhoneState();
            /*TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            String str1 = telephonyManager.getLine1Number();
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                String string = telephonyManager.getNetworkCountryIso();
                Toast.makeText(getApplicationContext(),str1 + "\n" + string,Toast.LENGTH_LONG).show();
            }*/

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        /*if (requestCode == CAMERA_PERMISSION_CODE){
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(CameraActivity.this, "Camera Permission Granted", Toast.LENGTH_SHORT) .show();
                opencamera();
            }else {
                Toast.makeText(CameraActivity.this, "Camera Permission Denied", Toast.LENGTH_SHORT) .show();

            }
        }*/
        if (requestCode == CAMERA_PERMISSION_CODE){
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED ){
              //  Toast.makeText(CameraActivity.this, "Camera Permission Granted", Toast.LENGTH_SHORT) .show();
               // opencamera();
                getPhoneState();
            }else {
                Toast.makeText(CameraActivity.this, "Camera Permission Denied", Toast.LENGTH_SHORT) .show();

            }
        }
    }

    private void getPhoneState() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String str1 = telephonyManager.getSimOperator();
            //String string = telephonyManager.getImei();
            @SuppressLint("MissingPermission") String Line1Number = telephonyManager.getLine1Number();
            Toast.makeText(getApplicationContext(),str1 + "\n" + Line1Number,Toast.LENGTH_LONG).show();
        }

    }

    private void opencamera() {
        Intent intent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        someActivityResultLauncher.launch(intent);
    }
}