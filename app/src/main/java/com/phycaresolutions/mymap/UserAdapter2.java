package com.phycaresolutions.mymap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter2 extends ArrayAdapter<ItemClass>  {
    List<ItemClass> arrayList;
    List<ItemClass> suggestions;
    List<ItemClass> tempItems;
    public UserAdapter2(@NonNull Context context, int resource, List<ItemClass> arrayList) {
        super(context, resource,arrayList);
        this.arrayList = arrayList;
        suggestions = new ArrayList<>();
        tempItems = new ArrayList<>();
        tempItems.addAll(arrayList);
    }

    @Nullable
    @Override
    public ItemClass getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return UserFilter;
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
            TextView tv2 = view1.findViewById(R.id.text_view_three);
            ImageView img = view1.findViewById(R.id.image_view);
            tv.setText(arrayList.get(position).getText());
            tv2.setText(arrayList.get(position).getText2());
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
    private Filter UserFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint!=null){
                for (ItemClass itemClass: tempItems) {
                       suggestions.clear();
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            }else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
             ArrayList<ItemClass> list =( ArrayList<ItemClass>)results.values;
            if (results != null && results.count > 0) {
                clear();
                for (ItemClass itemClass : list) {
                    add(itemClass);
                    notifyDataSetChanged();

                }
            } else {
                clear();
                notifyDataSetChanged();
            }
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            ItemClass itemClass = (ItemClass) resultValue;
            return itemClass.getText();
        }
    };


}

