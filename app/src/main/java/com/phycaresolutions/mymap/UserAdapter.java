package com.phycaresolutions.mymap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserAdapter extends ArrayAdapter<ItemClass>  {
    List<ItemClass> arrayList;
    public UserAdapter(@NonNull Context context, int resource, List<ItemClass> arrayList) {
        super(context, resource,arrayList);
        this.arrayList = arrayList;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (arrayList.get(position).getViewType() == R.layout.layout_one ) {
            return arrayList.get(position).getViewType();
        }else {
            return arrayList.get(position).getViewType();
        }

    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }
    // 72 /2 = 31

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (arrayList.get(position).getViewType() == R.layout.layout_one ) {
            View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
            TextView tv = view.findViewById(R.id.name);
            ImageView img = view.findViewById(R.id.item_info);
            tv.setText(arrayList.get(position).getText());
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),arrayList.get(position).getText(),Toast.LENGTH_LONG).show();
                }
            });
            return view;
        }else {
            View  view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_two,parent,false);
            TextView tv = view1.findViewById(R.id.text_view_two);
            ImageView img = view1.findViewById(R.id.image_view);
            tv.setText(arrayList.get(position).getText());
            img.setImageResource(arrayList.get(position).getImageResource());
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),arrayList.get(position).getText(),Toast.LENGTH_LONG).show();
                }
            });
            return view1;
        }

    }


}
