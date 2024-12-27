package com.phycaresolutions.mymap;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.phycaresolutions.mymap.adapter.UserAdapter2;
import com.phycaresolutions.mymap.databinding.ActivityDropDownBinding;
import com.phycaresolutions.mymap.db.UserDataBase;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DropDownActivity extends AppCompatActivity {
   ActivityDropDownBinding binding;
    private static String key = "riakjluVs/@qpUh#";

    @RequiresApi(api = Build.VERSION_CODES.O)
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
          //  long ll = dataBase.insertData(new ItemClass(R.layout.layout_one, "Item Type 1"));
          //  Log.e("DBDBBDB","DB>>> "+ll);
        }

        Log.e("DBDBBDB","Login details >>>>  :  "+ dataBase.getIsUserExist("raju","123"));

        for (String str: dataBase.getUserData()) {
            Log.e("DBDBBDB","get user Details >>>>  :  "+ str);
        }
        try {

          String by = Encrypt("hello world");
          Log.e("BYTE[]" ,"Byte array : " + by);
            //Advanced Encryption Standard
           // Decrypt the ciphertext
           String dc = Decrypt(by);
            Log.e("BYTE[]" ,"Byte array : " + dc);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String Decrypt(String text) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes= new byte[16];
        byte[] b= key.getBytes("UTF-8");
        int len= b.length;
        if (len > keyBytes.length) len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.DECRYPT_MODE,keySpec,ivSpec);
        byte[] raw = Base64.getDecoder().decode(text);
        byte [] results = cipher.doFinal(raw);
        return new String(results,"UTF-8");
    }
    @SuppressLint("NewApi")
    public static String Encrypt(String text)throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes= new byte[16];
        byte[] b= key.getBytes("UTF-8");
        int len= b.length;
        if (len > keyBytes.length) len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.ENCRYPT_MODE,keySpec,ivSpec);
        byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(results);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}