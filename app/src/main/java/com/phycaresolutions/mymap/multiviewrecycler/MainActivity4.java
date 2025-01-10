package com.phycaresolutions.mymap.multiviewrecycler;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phycaresolutions.mymap.R;
import com.task.task.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity4 extends AppCompatActivity {
   RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Map<String, List> map = new HashMap();
        List list1 = new ArrayList();
        list1.add(new Student("Ram","100","Android"));
        list1.add(new Student("kiran","101","Java"));
        list1.add(new Student("name2","102","kotlin"));
       // list1.add("Angular");
        List list2= new ArrayList();
        list2.add(new Employee2("Name1","IT"));
        list2.add(new Employee2("Name2","IT"));
        list2.add(new Employee2("Name3","IT"));
       // list2.add(40);

        map.put("A",list1);
        map.put("B",list2);

        List<Map.Entry<String, List>> mapEntries = new ArrayList<>(map.entrySet());

        MyAdapter adapter = new MyAdapter(mapEntries);
        recyclerView.setAdapter(adapter);
    }
}