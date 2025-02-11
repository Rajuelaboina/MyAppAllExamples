package com.phycaresolutions.mymap.buttonsegmentandchip;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.phycaresolutions.mymap.R;

import java.util.List;

public class ButtonSegmentAndChipMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_ex_main);
        MaterialButtonToggleGroup toggleGroup = findViewById(R.id.toggleButtonGroup);
        MaterialButton button1 = findViewById(R.id.button1);
        button1.setChecked(true);

        toggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                  if (checkedId == R.id.button1){
                       if (isChecked){

                       }
                   } else if (checkedId == R.id.button2) {
                      
                  }else {

                  }
            }
        });
        ChipGroup chipGroup = findViewById(R.id.chipGroup);
        Chip chip = findViewById(R.id.chip1);
        ChipDrawable chipDrawable = (ChipDrawable)chip.getChipDrawable();
        chipDrawable.setChipBackgroundColorResource(R.color.colorPrimary);
        chip.setTextColor(getResources().getColor(R.color.white,null));
        //chip.setChecked(true);
        chipGroup.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                // Loop through all chips in the group
                for (int i = 0; i < group.getChildCount(); i++) {
                    Chip chip = (Chip) group.getChildAt(i);
                    if (chip.isChecked()) {
                        // Change the selected chip background color
                        chip.setChipBackgroundColorResource(R.color.bg);
                        chip.setTextColor(getResources().getColor(R.color.white,null));

                    } else {
                        // Reset the unselected chip background color
                        chip.setChipBackgroundColorResource(R.color.one);
                    }
                }
            }
        });

    }
}