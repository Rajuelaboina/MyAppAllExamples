package com.phycaresolutions.mymap.multiviewrecycler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phycaresolutions.mymap.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
       recyclerView = findViewById(R.id.recyclerView3);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List list = new ArrayList();
        list.add(new TextItem2("Android","type1"));
        list.add(new ImageItem2(R.drawable.dog,"type2"));
        list.add(new AddItem("Kotlin","type3"));
        list.add(new TextItem2("Android","type1"));
        list.add(new ImageItem2(R.drawable.cat,"type2"));
        list.add(new AddItem("Kotlin","type3"));
        list.add(new TextItem2("Android","type1"));
        list.add(new ImageItem2(R.drawable.dog,"type2"));
        list.add(new ImageItem2(R.drawable.cat,"type2"));
        list.add(new ImageItem2(R.drawable.doctor,"type2"));
        list.add(new AddItem("Kotlin","type3"));
        list.add(new AddItem("Kotlin","type3"));
        list.add(new AddItem("Kotlin","type3"));
       MultiAdapter adapter = new MultiAdapter(list);
       recyclerView.setAdapter(adapter);
    }
}