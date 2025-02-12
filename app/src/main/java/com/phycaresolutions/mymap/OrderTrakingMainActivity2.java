package com.phycaresolutions.mymap;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderTrakingMainActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TrackingAdapter adapter;
    //private List<TrackingStep> trackingSteps;

    private List<OrderStatus> statusList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_traking_main2);
       /* recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        trackingSteps = new ArrayList<>();
        trackingSteps.add(new TrackingStep("Ordered and Approved", true));
        trackingSteps.add(new TrackingStep("Packed", true));
        trackingSteps.add(new TrackingStep("Shipped", false));
        trackingSteps.add(new TrackingStep("Delivered", false));

        adapter = new TrackingAdapter(trackingSteps);
        recyclerView.setAdapter(adapter);*/
        /*VerticalSeekBar verticalSeekBar = findViewById(R.id.verticalSeekBar2);
        verticalSeekBar.setMax(100);
        verticalSeekBar.setProgress(90);*/
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));

        // Add tracking steps
        statusList = new ArrayList<>();
        statusList.add(new OrderStatus("Awaiting confirmation", false));
        statusList.add(new OrderStatus("Order confirmed", false));
        statusList.add(new OrderStatus("Assigning a driver", false));
        statusList.add(new OrderStatus("Driver assigned", false));
        statusList.add(new OrderStatus("Driver at restaurant", false));
        statusList.add(new OrderStatus("Driver nearby", false));
        statusList.add(new OrderStatus("On its way", false));
        statusList.add(new OrderStatus("Enjoy your food", false));

        adapter = new TrackingAdapter(statusList);
        recyclerView.setAdapter(adapter);

        // Simulate status update
        updateStatus();
    }

    private void updateStatus() {
        new Thread(() -> {
            try {
                for (int i = 0; i < statusList.size(); i++) {
                    Thread.sleep(2000); // Simulate delay
                    final int index = i;
                    runOnUiThread(() -> {
                        if (index<=2){
                            statusList.get(index).isCompleted = true;
                            adapter.notifyDataSetChanged();
                        }else {
                            statusList.get(index).isCompleted = false;
                            adapter.notifyDataSetChanged();
                        }


                    });
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


}