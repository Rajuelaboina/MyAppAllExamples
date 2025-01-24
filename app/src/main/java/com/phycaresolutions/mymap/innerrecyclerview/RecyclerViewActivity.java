package com.phycaresolutions.mymap.innerrecyclerview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.phycaresolutions.mymap.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerViewActivity extends AppCompatActivity {
     RecyclerView recyclerView;
    HashMap<String, Map<String,List<String >>> hashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.recyclerView4);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getDataList();
        StatesAdapter adapter = new StatesAdapter(hashMap);
        recyclerView.setAdapter(adapter);
    }

    private void getDataList() {
        hashMap = new HashMap<>();
        Map<String,List<String>> map = new HashMap<>();
        List<String> stList = new ArrayList<>();
        stList.add("Tgmdl1");
        stList.add("Tgmdl2");
        stList.add("Tgmdl3");
        stList.add("Tgmdl4");

        List<String> stListhyd = new ArrayList<>();
        stListhyd.add("hydmdl1");
        stListhyd.add("hydmdl2");

        map.put("Wgl",stList);
        map.put("Hyd",stListhyd);


        Map<String,List<String>> map2 = new HashMap<>();
        List<String> stList3 = new ArrayList<>();
        stList3.add("ApMDL");
        stList3.add("APMDL2");
        map2.put("apDist",stList3);

        Map<String,List<String>> map3 = new HashMap<>();
        List<String> stList4 = new ArrayList<>();
        stList4.add("TnMDL");
        List<String> stList5 = new ArrayList<>();
        stList4.add("TnMDL");
        map3.put("tnDist",stList4);
        map3.put("tnDist2",stList5);


       /* List list = new ArrayList();
        list.add("Tg1");
        list.add("Tg2");
        list.add("Tg3");
        list.add("Tg4");

        List list1 = new ArrayList();
        list1.add("Ap1");
        list1.add("Ap2");
        list1.add("Ap3");


        List list2 = new ArrayList();
        list2.add("Tn1");
        list2.add("Tn2");*/

        hashMap.put("AP",map2);
        hashMap.put("Tg",map);
        hashMap.put("Tn",map3);
    }

    private class StatesAdapter  extends RecyclerView.Adapter<StatesAdapter.MyViewholder> {
        HashMap<String, Map<String, List<String>>> hashMap;
        int selectPosition = 0;
        public StatesAdapter(HashMap<String, Map<String, List<String>>> hashMap) {
           this.hashMap = hashMap;
        }

        @NonNull
        @Override
        public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parent,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewholder holder, @SuppressLint("RecyclerView") int position) {
            List<String> keysList = new ArrayList<>(hashMap.keySet());
            holder.tv.setText(keysList.get(position));
            Map<String,List<String >> list =  hashMap.get(keysList.get(position));


            ChildAdapter adapter = new ChildAdapter(list);
            holder.recyclerView.setAdapter(adapter);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPosition = position;
                    notifyDataSetChanged();

                }
            });
            if (selectPosition != position){
                holder.recyclerView.setVisibility(View.GONE);
                holder.imageView2.setVisibility(View.INVISIBLE);
                holder.imageView.setVisibility(View.VISIBLE);
            }else {
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.imageView.setVisibility(View.INVISIBLE);
                holder.imageView2.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public int getItemCount() {
            return hashMap.size();
        }

        class MyViewholder extends RecyclerView.ViewHolder{
            TextView tv;
            RecyclerView recyclerView;
            ImageView imageView,imageView2;
            public MyViewholder(@NonNull View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.textView8);
                imageView = itemView.findViewById(R.id.imageView4);
                imageView2 = itemView.findViewById(R.id.imageView5);
                recyclerView= itemView.findViewById(R.id.recyclerViewChild1);
                recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));

            }
        }
    }
    public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.MyChildViewHolder>{
        Map<String,List<String >> list;
        int selectPosition = 0;
        public ChildAdapter( Map<String,List<String >> list) {
         this.list = list;
        }

        @NonNull
        @Override
        public MyChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyChildViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_child,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyChildViewHolder holder, @SuppressLint("RecyclerView") int position) {
            List<String> keysList = new ArrayList<>(list.keySet());
            holder.tv.setText(keysList.get(position));
            List list2 = list.get(keysList.get(position));

            SubChildAdapter adapter = new SubChildAdapter(list2);
            holder.rv.setAdapter(adapter);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPosition =position;
                    notifyDataSetChanged();
                }
            });
            if (selectPosition!=position){
                holder.rv.setVisibility(View.GONE);
                holder.imageView2.setVisibility(View.INVISIBLE);
                holder.imageView.setVisibility(View.VISIBLE);
            }
            else {
                holder.rv.setVisibility(View.VISIBLE);
                holder.imageView.setVisibility(View.INVISIBLE);
                holder.imageView2.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyChildViewHolder extends RecyclerView.ViewHolder{
             TextView tv;
             RecyclerView rv;
            ImageView imageView,imageView2;
             public MyChildViewHolder(@NonNull View itemView) {
                 super(itemView);
                 tv = itemView.findViewById(R.id.textView9);
                 imageView = itemView.findViewById(R.id.imageViewchild1);
                 imageView2 = itemView.findViewById(R.id.imageViewchild2);
                 rv =itemView.findViewById(R.id.recyclerViewchild2);
                 rv.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
             }
         }
    }

    public class SubChildAdapter extends RecyclerView.Adapter<SubChildAdapter.MySubChildViewHolder>{
        List list2;
        public SubChildAdapter(List list2) {
            this.list2 = list2;
        }

        @NonNull
        @Override
        public MySubChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MySubChildViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_child,parent,false));

        }

        @Override
        public void onBindViewHolder(@NonNull MySubChildViewHolder holder, int position) {
               holder.tv.setText(list2.get(position).toString());
        }

        @Override
        public int getItemCount() {
            return list2.size();
        }

        class MySubChildViewHolder extends RecyclerView.ViewHolder{
           TextView tv;
            public MySubChildViewHolder(@NonNull View itemView) {
                super(itemView);
                tv= itemView.findViewById(R.id.textView10);
            }
        }
    }
}