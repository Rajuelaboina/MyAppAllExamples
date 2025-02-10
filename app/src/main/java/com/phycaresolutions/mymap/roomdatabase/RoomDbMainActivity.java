package com.phycaresolutions.mymap.roomdatabase;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.phycaresolutions.mymap.R;
import com.phycaresolutions.mymap.databinding.ActivityRoomDbMainBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoomDbMainActivity extends AppCompatActivity implements OnItemClickListener {
    ActivityRoomDbMainBinding binding;
    List<PersonDetails> list;
    RoomViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRoomDbMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(RoomViewModel.class);
        binding.RecyclerRoom.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayData();
            }
        });
       // viewModel = new RoomViewModel(getApplication());
        viewModel.getAllPersonDetails().observe(RoomDbMainActivity.this, new Observer<List<PersonDetails>>() {
            @Override
            public void onChanged(List<PersonDetails> personDetails) {
                if(personDetails!=null)
                    binding.RecyclerRoom.setAdapter(new PersonAdapter(personDetails));
            }
        });

        PersonAdapter.setOnitemClickListener(this);





    }

    private void displayData() {

        PersonDetails details = new PersonDetails(0, binding.editTextText2.getText().toString().trim(),
                binding.editTextTextPassword2.getText().toString().trim());
        viewModel.insert(details);

        ExecutorService executor = Executors.newSingleThreadExecutor();
       // Handler handler = new Handler(Looper.getMainLooper());

        //onPreExecute() before to get into executor, as below
       // progressBar_main_activity.setVisibility(View.VISIBLE);
       /* executor.execute(new Runnable() {
            @Override
            public void run() {
                //Background work here
                if (!binding.editTextText2.getText().toString().trim().isEmpty()) {
                    //UserDataBase.getInstance(getApplicationContext()).personDao().insertData(details);
                    viewModel.insert(details);
                }

              //  list = UserDataBase.getInstance(getApplicationContext()).personDao().getDetails();
               *//* handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //UI Thread work here
                        binding.RecyclerRoom.setAdapter(new UserAdapter(list));
                        binding.RecyclerRoom.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                });*//*
            }
        });*/

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

    @Override
    public void onItemClick(PersonDetails details, String str) {
          if (str.equals("delete")){
              int b = viewModel.delete(String.valueOf(details.getId()));
              if (b!=-1){
                  Toast.makeText(getApplicationContext(), "Delete item successfully", Toast.LENGTH_SHORT).show();
              }
          }else {
              AlertDialog.Builder alert = new AlertDialog.Builder(RoomDbMainActivity.this);
              View vv = getLayoutInflater().inflate(R.layout.dialog_item, null); // custom Dialog
              EditText editTextName = vv.findViewById(R.id.editTextText3);
              editTextName.setText(details.getUsername());
              EditText passwordEditText = vv.findViewById(R.id.editTextText4);
              passwordEditText.setText(details.getPassword());
              alert.setMessage("Update Data").setCancelable(false).setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {

                      viewModel.update(
                              new PersonDetails( details.getId(),
                                      editTextName.getText().toString().trim(),
                              passwordEditText.getText().toString().trim())
                      );
                      Toast.makeText(getApplicationContext(), "Update data successfully", Toast.LENGTH_SHORT).show();
                  }
              });
              alert.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      dialog.dismiss();
                  }
              });

              alert.setView(vv);
              alert.show();

          }
    }
}