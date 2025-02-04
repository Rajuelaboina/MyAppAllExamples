package com.phycaresolutions.mymap.asyntaskreplace;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.phycaresolutions.mymap.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        // 1 St way
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

       //onPreExecute() before to get into executor, as below
      //  progressBar_main_activity.setVisibility(View.VISIBLE);


        executor.execute(new Runnable() {
            @Override
            public void run() {

                //Background work here
               // runbackground();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //UI Thread work here
                       // update_UI();
                    }
                });
            }
        });

        // 2 nd way
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

    private String loadDataFromBackground() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "this is loadFrom background";
    }
}