package com.phycaresolutions.mymap.odertracking;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phycaresolutions.mymap.R;

import java.util.ArrayList;
import java.util.List;

public class OrderTrackActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private OrderTrackingAdapter adapter;
    private List<OrderStep> orderSteps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_track);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add order tracking steps
        orderSteps = new ArrayList<>();
        orderSteps.add(new OrderStep("Order Placed", "10:00 AM, 12 Jan", true));
        orderSteps.add(new OrderStep("Confirmed by Seller", "10:30 AM, 12 Jan", true));
        orderSteps.add(new OrderStep("Shipped", "3:00 PM, 13 Jan", true));
        orderSteps.add(new OrderStep("Out for Delivery", "8:00 AM, 14 Jan", false));
        orderSteps.add(new OrderStep("Delivered", "Expected 14 Jan", false));

        adapter = new OrderTrackingAdapter(this, orderSteps);
        recyclerView.setAdapter(adapter);
    }
}