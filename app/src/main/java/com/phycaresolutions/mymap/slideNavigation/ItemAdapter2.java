package com.phycaresolutions.mymap.slideNavigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phycaresolutions.mymap.R;

import java.util.List;

public class ItemAdapter2 extends RecyclerView.Adapter<ItemAdapter2.MyViewHolder>{
    List<UserDetails> listData;
   static ItemClickListener itemClickListener;
    public ItemAdapter2(List<UserDetails> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemAdapter2.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_expand,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter2.MyViewHolder holder, int position) {
        UserDetails details = listData.get(position);
        holder.tv1.setText(details.name);
        holder.tv2.setText(details.mobile);
        holder.tv3.setText(details.description);
        holder.itemView.setOnClickListener(v -> {
            itemClickListener.onItemClick(listData.get(position),position);
            boolean expanded = details.isExpanded();
            details.setExpanded(!expanded);
            notifyItemChanged(position);
        });
        // holder.linearLayout.setVisibility(details ? View.VISIBLE : View.GONE);
        if (details.isExpanded){
            holder.linearLayout.setVisibility(View.GONE);
        }else {
            holder.linearLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv1,tv2,tv3;
        LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.textView11);
            tv2 = itemView.findViewById(R.id.textView12);
            tv3 = itemView.findViewById(R.id.textView13);
            linearLayout = itemView.findViewById(R.id.linearLayoutItem);
        }
    }
    public static void setOnItemClickListener(ItemClickListener onItemClickListener){
        itemClickListener = onItemClickListener;
    }
}