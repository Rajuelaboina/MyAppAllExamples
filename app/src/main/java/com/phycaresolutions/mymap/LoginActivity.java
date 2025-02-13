package com.phycaresolutions.mymap;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.phycaresolutions.mymap.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
     ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidationUserName(binding.nameEditText.getText().toString().trim())){
                      binding.textInputLayout2.setError("Enter valid name !");
                      binding.textInputLayout2.setBoxStrokeErrorColor(ColorStateList.valueOf(Color.RED));
                }else if (!isValidationPassword(binding.passwordEditText.getText().toString().trim())){
                    binding.textInputLayout3.setError("Enter valid password! OR password must be greater than 3");
                    binding.textInputLayout3.setBoxStrokeErrorColor(ColorStateList.valueOf(Color.RED));
                }else {
                    Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    // call to Intent
                }
            }
        });
        binding.nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length()>0){
                    binding.textInputLayout2.setErrorEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        binding.passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length()>0){
                    binding.textInputLayout3.setErrorEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private boolean isValidationPassword(String password) {
        if (password==null || password.isEmpty()){
            return false;
        }else if (password.length()<3){
            return false;
        }
        return true;
    }

    private boolean isValidationUserName(String name) {
        if (name==null || name.isEmpty()){
            return false;
        }
        return true;
    }


}