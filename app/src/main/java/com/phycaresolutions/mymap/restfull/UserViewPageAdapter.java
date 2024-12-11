package com.phycaresolutions.mymap.restfull;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.phycaresolutions.mymap.R;

import java.util.List;
import java.util.Objects;

public class UserViewPageAdapter extends RecyclerView.Adapter<UserViewPageAdapter.MyViewHolder>{
    Context context;
    //List<Datum> data;
    int[] images;
  /*  public UserViewPageAdapter(Context context, List<Datum> data) {
        this.context = context;
        this.data = data;
    }*/

    public UserViewPageAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        /*  Glide.with(context).load(data.get(position).getAvatar())
                  .into(holder.img);*/
        holder.img.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageId);
        }
    }
}