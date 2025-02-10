package com.phycaresolutions.mymap.roomdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phycaresolutions.mymap.databinding.PersonItemRowBinding;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {
     List<PersonDetails> list;
    static OnItemClickListener onitemClickListener;
    public PersonAdapter(List<PersonDetails> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item_row,parent,false));
        return new MyViewHolder(PersonItemRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     //  holder.tv.setText("Name: "+list.get(position).getUsername());
      //  holder.tv2.setText("Password: "+list.get(position).getPassword());
        holder.bind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
       // TextView tv,tv2;
        PersonItemRowBinding binding2;
        public MyViewHolder(@NonNull  PersonItemRowBinding binding) {
            super(binding.getRoot());
           // tv = itemView.findViewById(R.id.textView19);
           // tv2 = itemView.findViewById(R.id.textView20);
            binding2 = binding;
        }
        public void bind(PersonDetails details){
            binding2.textView19.setText("Name: "+details.getUsername());
            binding2.textView20.setText("Password: "+details.getPassword());
            binding2.imageView7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onitemClickListener.onItemClick(details,"delete");
                }
            });
            binding2.imageView8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onitemClickListener.onItemClick(details,"update");
                }
            });
        }

    }
    public static void setOnitemClickListener(OnItemClickListener onitemClickListener2){
         onitemClickListener = onitemClickListener2;
    }


}
