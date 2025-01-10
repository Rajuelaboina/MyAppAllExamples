package com.phycaresolutions.mymap.addorremove;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.phycaresolutions.mymap.R;
import com.phycaresolutions.mymap.databinding.ActivityAddorRemoveEdittextMainBinding;
import com.phycaresolutions.mymap.databinding.ActivityMain3Binding;
import com.phycaresolutions.mymap.multiviewrecycler.MainActivity3;

import java.util.StringTokenizer;

public class AddorRemoveEdittextMainActivity extends AppCompatActivity {
    ActivityAddorRemoveEdittextMainBinding binding;
    int editCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_addor_remove_edittext_main);

        binding = ActivityAddorRemoveEdittextMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //recyclerView = findViewById(R.id.recyclerView);
        // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEdit();

            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder values = new StringBuilder();
                for (int i = 0; i < binding.linearlayout.getChildCount(); i++) {
                    LinearLayout editTextContainer = (LinearLayout) binding.linearlayout.getChildAt(i);
                    EditText editText = (EditText)editTextContainer.getChildAt(0);
                    String string = editText.getText().toString();
                    values.append(string).append(",");
                }
                Log.e("Values for String>>>>>: " , "Edit values : " +values);
               /* String str = values.toString();
                String[] parts = str.split(",");
                for (String part : parts) {
                    System.out.println(part);
                    Log.e("Values for String>>>>>: " , "String token : " +part);
                }*/

                StringTokenizer tokenizer = new StringTokenizer(values.toString(), ",");
                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    System.out.println(token);
                    Log.e("Values for String>>>>>: " , "String token : " +token);
                }

            }
        });
    }
    private void addEdit() {
        EditText editText = new EditText(AddorRemoveEdittextMainActivity.this);
        editText.setHint("Enter value" + (editCount+1));
        editText.setId(View.generateViewId());
        LinearLayout editTextContainer = new LinearLayout(AddorRemoveEdittextMainActivity.this);
        editTextContainer.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f
        );
        editText.setLayoutParams(params);
        Button removeButton = new Button(AddorRemoveEdittextMainActivity.this);
        removeButton.setText("Remove");
        removeButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        editCount++;
        editTextContainer.addView(editText);
        editTextContainer.addView(removeButton);
        binding.linearlayout.addView(editTextContainer);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.linearlayout.removeView(editTextContainer);  // Remove this EditText and Remove button container
                editCount--;  // Decrease the counter
            }
        });
    }
}