package com.phycaresolutions.mymap.multiviewrecycler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phycaresolutions.mymap.R;

import java.util.List;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter {
    List<Map.Entry<String, List>> map;

    public MyAdapter(List<Map.Entry<String, List>> mapEntries) {
        this.map = mapEntries;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1){
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false));
        } else if (viewType == 2) {

            return new MyViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row2,parent,false));
        }else {
            return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Map.Entry<String, List> entry = map.get(position);
        List list = entry.getValue();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Student){
                Student student = (Student) list.get(i);
                Log.e("MAP","MAP VALUES: " + student.name);
                /*((MyViewHolder) holder).tv5.setText("Id: " +student.id +"\n"+ student.name);
                ((MyViewHolder) holder).tv6.setText(student.course);*/
                TextView valueTextView = new TextView(holder.itemView.getContext());
                valueTextView.setText("Id: " +student.id +"\n"+ "Name: " + student.name + "\n" + "course: "+student.course);
                ((MyViewHolder) holder).layout.addView(valueTextView);
            } else if (list.get(i) instanceof Employee2) {
                Employee2 employee = (Employee2) list.get(i);
                TextView valueTextView = new TextView(holder.itemView.getContext());
                valueTextView.setText("Emp name: " +employee.empName +"\n"+ "Name: " + "empDepatment: " +employee.empDepatment );
                ((MyViewHolder2) holder).linearLayout2.addView(valueTextView);
            }
        }
        //Student student = (Student) list;
        //Log.e("MAP","MAP VALUES: " + student.name);

        /* if (holder instanceof MyViewHolder){
             Student student = (Student) list;
             ((MyViewHolder) holder).tv5.setText("Id: " +student.id +"\n"+ student.name);
             ((MyViewHolder) holder).tv6.setText(student.course);
         }else if (holder instanceof MyViewHolder2){
             Employee employee = (Employee) list;
             ((MyViewHolder2) holder).tv3.setText(employee.empName);
             ((MyViewHolder2) holder).tv4.setText(employee.empDepatment);
         }*/
    }

    @Override
    public int getItemViewType(int position) {
        if (map.get(position).getKey() == "A"){
            return 1;
        } else if (map.get(position).getKey() == "B") {
            return 2;
        }else {
            return -1;
        }
    }

    @Override
    public int getItemCount() {
        return map.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv5,tv6;
        LinearLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            /*tv5 = itemView.findViewById(R.id.textView5);
            tv6 = itemView.findViewById(R.id.textView6);*/
            layout = itemView.findViewById(R.id.linearLayout1);
        }
    }
    class MyViewHolder2 extends RecyclerView.ViewHolder{
       TextView tv3,tv4;
       LinearLayout linearLayout2;
        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
         /*   tv3 = itemView.findViewById(R.id.textView3);
            tv4 = itemView.findViewById(R.id.textView4);*/
            linearLayout2 = itemView.findViewById(R.id.linearLayout2);
        }
    }
}
