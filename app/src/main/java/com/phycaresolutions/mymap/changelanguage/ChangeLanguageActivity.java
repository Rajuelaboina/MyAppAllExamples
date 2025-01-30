package com.phycaresolutions.mymap.changelanguage;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.phycaresolutions.mymap.R;

public class ChangeLanguageActivity extends AppCompatActivity {
   TextView tv,tv2;
   Context context;
    Resources resources;
    RadioButton rb1,rb2;
    RadioGroup radioGroup;
    String lg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_asyn_replace_main);
        radioGroup = findViewById(R.id.radioGroup2);
        rb1 = findViewById(R.id.radioButton3);
        rb2 = findViewById(R.id.radioButton4);
        tv = findViewById(R.id.textView15);
        tv2 = findViewById(R.id.textView16);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (rb1.isChecked()){
            context = LocaleHelper.setLocale(ChangeLanguageActivity.this, "te");
            tv.setText(context.getResources().getString(R.string.select_language));
            rb1.setText(context.getResources().getString(R.string.rb1));
            rb2.setText(context.getResources().getString(R.string.rb2));
            tv2.setText("ప్రాజెక్ట్ కోసం స్ట్రింగ్ వనరులు strings.xml ఫైల్\u200Cలలో ఉంటాయి. మీ ప్రాజెక్ట్ మీ యాప్ కోసం డిఫాల్ట్ భాషలో స్ట్రింగ్ వనరులను కలిగి ఉన్న డిఫాల్ట్ strings.xml ఫైల్\u200Cను కలిగి ఉంది, ఇది మీ యాప్ వినియోగదారులు ఎక్కువగా మాట్లాడాలని మీరు ఆశించే భాష. మీరు మీ యాప్\u200Cను ఉంచాలనుకునే ఇతర భాషల కోసం స్ట్రింగ్ వనరులను కలిగి ఉన్న strings.xml ఫైల్\u200Cలను కూడా అనువదించవచ్చు");

        }/*else {
            context = LocaleHelper.setLocale(ChangeLanguageActivity.this, "en");
            tv.setText(context.getResources().getString(R.string.select_language));
            rb1.setText(context.getResources().getString(R.string.rb1));
            rb2.setText(context.getResources().getString(R.string.rb2));
        }*/

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (R.id.radioButton3 == checkedId){
                    context = LocaleHelper.setLocale(ChangeLanguageActivity.this, "te");
                    resources = context.getResources();
                    tv.setText(context.getResources().getString(R.string.select_language));
                    rb1.setText(context.getResources().getString(R.string.rb1));
                    rb2.setText(context.getResources().getString(R.string.rb2));
                    getSupportActionBar().setTitle(resources.getString(R.string.app_name));
                    lg = LocaleHelper.getLanguage(getApplicationContext());
                    Log.e("Language","LAN: "+lg);
                    tv2.setText("ప్రాజెక్ట్ కోసం స్ట్రింగ్ వనరులు strings.xml ఫైల్\u200Cలలో ఉంటాయి. మీ ప్రాజెక్ట్ మీ యాప్ కోసం డిఫాల్ట్ భాషలో స్ట్రింగ్ వనరులను కలిగి ఉన్న డిఫాల్ట్ strings.xml ఫైల్\u200Cను కలిగి ఉంది, ఇది మీ యాప్ వినియోగదారులు ఎక్కువగా మాట్లాడాలని మీరు ఆశించే భాష. మీరు మీ యాప్\u200Cను ఉంచాలనుకునే ఇతర భాషల కోసం స్ట్రింగ్ వనరులను కలిగి ఉన్న strings.xml ఫైల్\u200Cలను కూడా అనువదించవచ్చు");

                }else {
                    context = LocaleHelper.setLocale(ChangeLanguageActivity.this, "en");
                    resources = context.getResources();
                    tv.setText(context.getResources().getString(R.string.select_language));
                    rb1.setText(context.getResources().getString(R.string.rb1));
                    rb2.setText(context.getResources().getString(R.string.rb2));
                    getSupportActionBar().setTitle(resources.getString(R.string.app_name));
                    lg = LocaleHelper.getLanguage(getApplicationContext());
                    Log.e("Language","LAN: "+lg);
                    tv2.setText("The string resources for a project are contained in strings.xml files. Your project has a default strings.xml file that contains string resources in the default language for your app, which is the language you expect most of your app users to speak. You can also have translated strings.xml files that contain string resources for other languages that you want your app to accommodate");


                }
            }
        });

        /*tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bb) {
                    context = LocaleHelper.setLocale(AsynReplaceMainActivity.this, "te");
                    resources = context.getResources();
                    tv.setText(resources.getString(R.string.language));
                    getSupportActionBar().setTitle(resources.getString(R.string.app_name));
                    bb=true;
                }else {
                    bb=false;
                    context = LocaleHelper.setLocale(AsynReplaceMainActivity.this, "en");
                    resources = context.getResources();
                    tv.setText(resources.getString(R.string.language));
                    getSupportActionBar().setTitle(resources.getString(R.string.app_name));
                }
            }
        });*/

    }

}