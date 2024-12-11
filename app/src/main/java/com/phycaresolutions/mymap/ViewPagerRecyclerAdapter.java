package com.phycaresolutions.mymap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.compose.foundation.layout.ContextualFlowRowOverflow;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ViewPagerRecyclerAdapter extends RecyclerView.Adapter<ViewPagerRecyclerAdapter.MyViewHolder> {
    ArrayList arrayList;
    Context context;
    public ViewPagerRecyclerAdapter(Context context, ArrayList arrayList) {
        this.arrayList = arrayList;
       this. context= context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //holder.img.setImageResource((Integer) arrayList.get(position));
        Glide.with(context).load(arrayList.get(position)).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
      ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageId);
        }
    }
}
