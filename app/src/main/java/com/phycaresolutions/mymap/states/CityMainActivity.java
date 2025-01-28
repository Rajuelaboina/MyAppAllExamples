package com.phycaresolutions.mymap.states;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;
import com.phycaresolutions.mymap.R;

import java.util.ArrayList;
import java.util.List;

public class CityMainActivity extends AppCompatActivity {
    AutoCompleteTextView act;
    TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_city_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        act = findViewById(R.id.autoCompleteTextView2);
        textInputLayout = findViewById(R.id.textInputLayout);
        List<Item> list = new ArrayList<>();
        list.add(new Item("Item1"));
        list. add(new Item("Item2"));
        list. add(new Item("Item3"));
        list. add(new Item("Item4"));
        MyDropdownAdapter adapter = new MyDropdownAdapter(getApplicationContext(),R.layout.item_dropwon,list);
        act.setAdapter(adapter);

    }

    class MyDropdownAdapter extends ArrayAdapter<Item>{
        List<Item> list;
        Context context;
        List<Item> suggestions;
        List<Item> tempItems;
        public MyDropdownAdapter(@NonNull Context context, int resource, List<Item> list) {
            super(context, resource,list);
            this.list = list;
            this.context = context;
            suggestions = new ArrayList<>();
            tempItems = new ArrayList<>();
            tempItems.addAll(list);
        }

        @Nullable
        @Override
        public Item getItem(int position) {
            return super.getItem(position);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dropwon,parent,false);
            TextView tv = view.findViewById(R.id.textView14);
            tv.setText(list.get(position).name);
            Spinner spinner = view.findViewById(R.id.sp);
            if (position==0) {
                ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }
            return view;
        }

        @NonNull
        @Override
        public Filter getFilter() {
            return filter;
        }
        private Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint!=null){
                    //for (Item item: tempItems) {
                        suggestions.clear();
                   // }

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
                ArrayList<Item> arrayList = (ArrayList<Item>) results.values;
                if (results!=null && results.count>0){
                     clear();
                    for (Item item : arrayList) {
                        add(item);
                        notifyDataSetChanged();

                    }
                } else {
                    clear();
                    notifyDataSetChanged();
                }
            }
            @Override
            public CharSequence convertResultToString(Object resultValue) {
                Item item = (Item) resultValue;

                return item.name;
            }
        };
    }

    private class Item{
        String name;

        public Item(String name) {
            this.name = name;
        }
    }
}
       // static json data Reading in JSON Object and Array
     /*  String jsonType = getStringJson(getApplicationContext(),"Cites.json");

        Gson gson = new Gson();
        Type type = new TypeToken<Cites>(){}.getType();
        Cites cites = gson.fromJson(jsonType,type);
        List<Up>  uplist =cites.getUp();
        List<Agra> aList = uplist.get(0).getAgra();

        List<Mp>  list =cites.getMp();
        List<Indore> ll =list.get(0).getIndore();
        List<Bhopal> bll =list.get(1).getBhopal();
        for (int i = 0; i < ll.size(); i++) {
            Log.e("Data","Indore>>: "+ll.get(i).getBrName());
            Log.e("Data","Bhopal>>: "+bll.get(i).getBrName());
            Log.e("Data","Agra>>: "+aList.get(i).getBrName());
        }

    }

    private String getStringJson(Context context, String s) {
       String str = "";
        try {
            InputStream stream = context.getAssets().open(s);
           byte[] bytes = new byte[stream.available()];
           stream.read(bytes);
           stream.close();
           str = new String(bytes,"UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return str;
    }
}*/