package com.phycaresolutions.mymap;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CustomRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Model> dataSet;

    public CustomRecyclerView(List<Model> dataSet) {
        this.dataSet =dataSet;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case Model.TEXT_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_type,parent,false);
                return new TextTypeViewHolder(view);

            case Model.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_type,parent,false);
                return new ImageTypeViewHolder(view);
            case Model.AUDIO_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_type,parent,false);
                return new AudioTypeViewHolder(view);

        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
           Model model = dataSet.get(position);
           if (model!=null){
               switch (model.type){
                   case Model.TEXT_TYPE:
                       ((TextTypeViewHolder) holder).tv.setText(model.text);
                       break;
                   case Model.IMAGE_TYPE:
                       ((ImageTypeViewHolder) holder).txtType.setText(model.text);
                       ((ImageTypeViewHolder) holder).image.setImageResource(model.id);
                       break;
                   case Model.AUDIO_TYPE:
                   ((AudioTypeViewHolder) holder).tv.setText(model.text);
                       break;
               }
           }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public int getItemViewType(int position) {
         switch (dataSet.get(position).type){
             case 1 :
                 return Model.TEXT_TYPE;
             case 2:
                 return Model.IMAGE_TYPE;
             case 3:
                 return Model.AUDIO_TYPE;
             default:
                 return -1;
         }

    }
   public  class TextTypeViewHolder extends RecyclerView.ViewHolder{
         TextView tv;
         CardView cardView;
       public TextTypeViewHolder(@NonNull View itemView) {
           super(itemView);
           tv =itemView.findViewById(R.id.type);
           cardView = itemView.findViewById(R.id.card_view);
       }
   }
    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder{
        TextView txtType;
        ImageView image;
        public ImageTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtType = itemView.findViewById(R.id.img_type);
            image = itemView.findViewById(R.id.background);
        }
    }
    public static class AudioTypeViewHolder extends RecyclerView.ViewHolder{
         TextView tv;
         FloatingActionButton fb;
        public AudioTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.type);
            fb = itemView.findViewById(R.id.fab);
        }
    }
}

