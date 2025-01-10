package com.phycaresolutions.mymap.multiviewrecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phycaresolutions.mymap.R;

import java.util.List;

public class MultiAdapter extends RecyclerView.Adapter {
    List list;
    public MultiAdapter(List list) {
      this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
           return new TextItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.text_item,parent,false));
        } else if (viewType==1) {
            return new ImageItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent,false));
        }else {
            return new AddItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_item,parent,false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       // RecyclerView.ViewHolder TextItemViewHolder = holder;
        Object object = list.get(position);
        if (holder instanceof TextItemViewHolder){
                TextItem2 textItem2 = (TextItem2) object;
                ((TextItemViewHolder) holder).tvType1.setText(textItem2.getType1());
        }else if (holder instanceof  ImageItemViewHolder){
                ImageItem2 imageItem2 = (ImageItem2) object;
                ((ImageItemViewHolder) holder).img.setImageResource(imageItem2.getImageId());
        }else if (holder instanceof AddItemViewHolder){
                AddItem addItem = (AddItem) object;
                ((AddItemViewHolder) holder).tvType2.setText(addItem.getName2());
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object object =list.get(position);
        if (object instanceof TextItem2) {
           return 0;
        } else if (object instanceof ImageItem2) {
            return 1;
        }else if (object instanceof AddItem){
            return 2;
        }else {
            return -1;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
   public class TextItemViewHolder extends RecyclerView.ViewHolder{
        TextView tvType1 ;
        public TextItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvType1 = itemView.findViewById(R.id.textviewone);
        }
        public void bind(){

        }
    }
    public class ImageItemViewHolder extends RecyclerView.ViewHolder{
        ImageView img ;
        public ImageItemViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageone);
        }
    }
    public class AddItemViewHolder extends RecyclerView.ViewHolder{
        TextView tvType2 ;
        public AddItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvType2 = itemView.findViewById(R.id.textviewtwo);
        }
    }
}
