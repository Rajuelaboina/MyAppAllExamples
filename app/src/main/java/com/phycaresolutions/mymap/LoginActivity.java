package com.phycaresolutions.mymap;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.Semaphore;

public class LoginActivity extends AppCompatActivity {
    private static final int MAX_THREADS = 5;
    Object lock = new Object();
    private int counter = 0;
    TextView textView;
    Semaphore semaphore = new Semaphore(MAX_THREADS);
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.listView);
       String[] str = {"a","b","d","e","f","g","h","i"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,str);
        listView.setAdapter(adapter);
    }
    public void counterIncrement(View view){
       /* new Thread(()->{
            try {
               semaphore.acquire();
               synchronized (lock){
                   counter++;
               }
            }catch (InterruptedException e){

            }finally {
                 semaphore.release();
            }
            updateTextView();

        }).start();*/
        updateTextView();
    }

    private void updateTextView() {
        new Handler(getMainLooper()).post(()-> textView.setText(String.valueOf(counter)));
    }
}