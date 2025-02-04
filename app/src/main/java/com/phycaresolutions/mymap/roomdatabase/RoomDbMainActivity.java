package com.phycaresolutions.mymap.roomdatabase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.phycaresolutions.mymap.databinding.ActivityRoomDbMainBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoomDbMainActivity extends AppCompatActivity {
    ActivityRoomDbMainBinding binding;
    List<PersonDetails> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoomDbMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayData();
            }
        });

        displayData();

    }

    private void displayData() {

        PersonDetails details = new PersonDetails(binding.editTextText2.getText().toString().trim(),
                binding.editTextTextPassword2.getText().toString().trim());
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        //onPreExecute() before to get into executor, as below
       // progressBar_main_activity.setVisibility(View.VISIBLE);


        executor.execute(new Runnable() {
            @Override
            public void run() {

                //Background work here
                if (!binding.editTextText2.getText().toString().trim().isEmpty()) {
                    UserDataBase.getInstance(getApplicationContext()).personDao().insertData(details);
                }
                list = UserDataBase.getInstance(getApplicationContext()).personDao().getDetails();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //UI Thread work here
                        binding.RecyclerRoom.setAdapter(new UserAdapter(list));
                        binding.RecyclerRoom.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                });
            }
        });
        /*ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            // background
            if (!binding.editTextText2.getText().toString().trim().isEmpty()) {
                UserDataBase.getInstance(getApplicationContext()).personDao().insertData(details);
            }
            list = UserDataBase.getInstance(getApplicationContext()).personDao().getDetails();

            // Update the UI
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    binding.RecyclerRoom.setAdapter(new UserAdapter(list));
                    binding.RecyclerRoom.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }
            });

        });*/
    }
}