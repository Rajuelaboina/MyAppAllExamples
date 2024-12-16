package com.phycaresolutions.mymap;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.phycaresolutions.mymap.adapter.UserAdapter2;
import com.phycaresolutions.mymap.databinding.ActivityDropDownBinding;
import com.phycaresolutions.mymap.db.UserDataBase;

import java.util.ArrayList;
import java.util.List;

public class DropDownActivity extends AppCompatActivity {
   ActivityDropDownBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDropDownBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList list = new ArrayList();
        list.add("A");list.add("B");
        list.add("C");list.add("D"); list.add("E");list.add("F");
        List<ItemClass> itemClasses = new ArrayList<>();

        itemClasses.add(new ItemClass(R.layout.layout_one, "Item Type 1"));
        itemClasses.add(new ItemClass(R.layout.layout_two, "Item Type 2"));
        itemClasses.add(new ItemClass(R.layout.layout_two, R.drawable.doctor, "Item Type 2","sub_Android"));
        itemClasses.add(new ItemClass(R.layout.layout_one, "Item Type 1"));
        itemClasses.add(new ItemClass(R.layout.layout_two, R.drawable.img, "Item Type 2","sub_Android"));
        itemClasses.add(new ItemClass(R.layout.layout_two, R.drawable.baseline_visibility_off_24, "Item Type 2","sub_Android"));
        itemClasses.add(new ItemClass(R.layout.layout_one, "Item Type 1"));
        itemClasses.add(new ItemClass(R.layout.layout_two, R.drawable.baseline_account_circle_24, "Item Type 2","sub_Android"));

        UserAdapter2 adapter = new UserAdapter2(getApplicationContext(),R.layout.row_item,itemClasses);
       // binding.autoCompleteTextView.setAdapter(new ArrayAdapter<>(getApplication(), android.R.layout.simple_dropdown_item_1line,list));
        binding.autoCompleteTextView.setAdapter(adapter);
        binding.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemClass string =(ItemClass) parent.getItemAtPosition(position);
                Log.e("DAAAA",string.getText());
                binding.textInputLayout.setStartIconDrawable(string.getImageResource());
            }
        });
      /* binding.radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @SuppressLint("NonConstantResourceId")
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {

           }
       });*/
        UserDataBase dataBase = new UserDataBase(getApplicationContext());
        if (!dataBase.getIsUserExist("raju","12345")) {
            long ll = dataBase.insertData(new ItemClass(R.layout.layout_one, "Item Type 1"));
            Log.e("DBDBBDB","DB>>> "+ll);
        }

        Log.e("DBDBBDB","Login details >>>>  :  "+ dataBase.getIsUserExist("raju","123"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}