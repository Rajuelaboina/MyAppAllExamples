package com.phycaresolutions.mymap;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.UnderlineSpan;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.phycaresolutions.mymap.userjetpack.Detail;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etAssessment;
    EditText length1;
    EditText length2;
    TextView tv1;
    String strAssessmentValue = "";
    ListView lv;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //computePakageHash();



        // lv = findViewById(R.id.listView);
        rv = findViewById(R.id.recyclerView);

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
       // SpannableString spannableString = new SpannableString("Welcome Back");
        //spannableString.setSpan(new ForegroundColorSpan(Color.RED),7,12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
       // spannableString.setSpan(new UnderlineSpan(),7,12,Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
       /* spannableString.setSpan(new RelativeSizeSpan(2f),7,12,Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tv1.setText(spannableString, TextView.BufferType.SPANNABLE);
        User user =new User("Android","101","AND");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new User("Android","101","AND"));
        arrayList.add(new User("Java","102","JAA"));
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("MY",arrayList);*/
       // startActivity(intent);

         // static json----------------------------------------------
        /*String strType = getStringjsonData(getApplicationContext(),"data.json");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Detail>>(){}.getType();
        List<Detail> list = gson.fromJson(strType,type);
        for (int i = 0; i < list.size(); i++) {
            Detail detail = list.get(i);
           Log.e("AAAAAA", detail.getProviderName());
        }*/


        /*UserAdapter adapter = new UserAdapter(getApplicationContext(),R.layout.row_item,list);
       lv.setAdapter(adapter);
       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Toast.makeText(getApplicationContext(),list.get(position).getProviderName(),Toast.LENGTH_LONG).show();
           }
       });*/

        /*List<ItemClass> itemClasses = new ArrayList<>();

        // Pass the arguments
        itemClasses.add(new ItemClass(R.layout.layout_one, "Item Type 1"));
        itemClasses.add(new ItemClass(R.layout.layout_two, "Item Type 1"));
        itemClasses.add(new ItemClass(R.layout.layout_two, R.drawable.doctor, "Item Type 2","sub_Android"));
        itemClasses.add(new ItemClass(R.layout.layout_one, "Item Type 1"));
        itemClasses.add(new ItemClass(R.layout.layout_two, R.drawable.img, "Item Type 2","sub_Android"));
        itemClasses.add(new ItemClass(R.layout.layout_two, R.drawable.baseline_visibility_off_24, "Item Type 2","sub_Android"));
        itemClasses.add(new ItemClass(R.layout.layout_one, "Item Type 1"));
        itemClasses.add(new ItemClass(R.layout.layout_two, R.drawable.baseline_account_circle_24, "Item Type 2","sub_Android"));

       // UserAdapter adapter = new UserAdapter(getApplicationContext(),R.layout.row_item,itemClasses);
       // lv.setAdapter(adapter);

        ArrayList<Model> list2= new ArrayList();
        list2.add(new Model(Model.TEXT_TYPE,"Hello. This is the Text-only View Type. Nice to meet you",0));
        list2.add(new Model(Model.IMAGE_TYPE,"Hi. I display a cool image too besides the omnipresent TextView.",R.drawable.baseline_cloudy_snowing_24));
        list2.add(new Model(Model.AUDIO_TYPE,"Hey. Pressing the FAB button will playback an audio file on loop.",R.drawable.baseline_volume_up_24));
        list2.add(new Model(Model.IMAGE_TYPE,"Hi again. Another cool image here. Which one is better?",R.drawable.doctor));

        CustomRecyclerView adapter = new CustomRecyclerView(list2);
        rv.setAdapter(adapter);*/



       /* if (strAssessmentValue.contains("\n")) {
            strAssessmentValue = strAssessmentValue.replace("\n", "\\r\\n");
        }
        if (strAssessmentValue.contains(" ")) {
            strAssessmentValue = strAssessmentValue.replace(" ", "_");
        }*/
       // Log.e("DAAA",": " +strAssessmentValue );


       /* setTheme(R.style.splashScreenTheme);


        final TextView length1View = (TextView) findViewById(R.id.length1);
        final TextView length2View = (TextView) findViewById(R.id.length2);

        length1 = (EditText) findViewById(R.id.length1Value);
        length2 = (EditText) findViewById(R.id.length2value);
        result = (EditText) findViewById(R.id.resultValue);

        ImageView square = (ImageView) findViewById(R.id.square);
        ImageView triangle = (ImageView) findViewById(R.id.triangle);
        ImageView circle = (ImageView)findViewById(R.id.circle);

        shape = (TextView) findViewById(R.id.shapeselect);

        Button calculate = (Button) findViewById(R.id.calculatebutton);
        Button clear = (Button) findViewById(R.id.clearbutton);

        result.setEnabled(false);
        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                length2.setVisibility(View.INVISIBLE);
                length2View.setVisibility(View.INVISIBLE);
                length1View.setText("Side Length:");
                shape.setText("Square");
                shape.setTextColor(getResources().getColor(R.color.colorPrimary));
                result.setEnabled(false);
                length1.setText("");
                length2.setText("");
                result.setText("");
            }
        });

        triangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                length2.setVisibility(View.VISIBLE);
                length2View.setVisibility(View.VISIBLE);
                length1View.setText("Breadth:");
                length2View.setText("Height:");
                shape.setText("Triangle");
                shape.setTextColor(getResources().getColor(R.color.colorPrimary));
                result.setEnabled(false);
                length1.setText("");
                length2.setText("");
                result.setText("");
            }
        });

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                length2.setVisibility(View.INVISIBLE);
                length2View.setVisibility(View.INVISIBLE);
                length1View.setText("Radius:");
                shape.setText("Circle");
                shape.setTextColor(getResources().getColor(R.color.colorPrimary));
                result.setEnabled(false);
                length1.setText("");
                length2.setText("");
                result.setText("");
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double side1;
                double side2;
                double area;
                DecimalFormat dFormat = new DecimalFormat("#.##");
                result.setEnabled(true);
                switch(shape.getText().toString())
                {
                    case "Square":

                        if(length1.getText().toString() != null && !length1.getText().toString().isEmpty()) {
                            side1 = Double.parseDouble(length1.getText().toString());
                            result = (EditText) findViewById(R.id.resultValue);
                            area = Double.valueOf(dFormat.format(side1 * side1));
                            result.setText(Double.toString(area));
                        }

                        else {

                            Toast.makeText( MainActivity.this,"Enter Length of Square to Proceed",Toast.LENGTH_LONG).show();
                        }
                        break;

                    case "Triangle":

                        if(length1.getText().toString() != null && length2.getText().toString() != null && !length1.getText().toString().isEmpty() && !length2.getText().toString().isEmpty()) {
                            side1 = Double.parseDouble(length1.getText().toString());
                            side2 = Double.parseDouble(length2.getText().toString());
                            area = Double.valueOf(dFormat.format(side1 * side2 * 0.5));
                            result.setText(Double.toString(area));
                        }

                        else {

                            Toast.makeText( MainActivity.this,"Enter Breadth and Height of Triangle to Proceed",Toast.LENGTH_LONG).show();
                        }
                        break;

                    case "Circle":
                        if(length1.getText().toString() != null && !length1.getText().toString().isEmpty()) {
                            side1 = Double.parseDouble(length1.getText().toString());
                            result = (EditText) findViewById(R.id.resultValue);
                            area = Double.valueOf(dFormat.format((side1 * side1 * 22) / 7));
                            result.setText(Double.toString(area));
                        }
                        else {

                            Toast.makeText( MainActivity.this,"Enter Radius of Circle to Proceed",Toast.LENGTH_LONG).show();
                        }
                        break;

                    default:
                        Toast.makeText(MainActivity.this,"Select a Shape to Proceed",Toast.LENGTH_LONG).show();

                }

                result.setEnabled(false);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                length1.setText("");
                length2.setText("");
                length2View.setVisibility(View.VISIBLE);
                length2.setVisibility(View.VISIBLE);
                length1View.setText("Length 1:");
                length2View.setText("Length 2:");
                result.setText("");
                result.setEnabled(false);
                shape.setText(getResources().getString(R.string.select));
            }
        });*/
    }

    private String getStringjsonData(Context context,String filename) {
       String str;
        try {
            InputStream is= context.getAssets().open(filename);
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            is.close();
            str = new String(bytes,"UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return str;
    }

    private void computePakageHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.phycaresolutions.mymap",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));


            }
        } catch (Exception e) {
            Log.e("TAG",e.getMessage());
        }
        }
}