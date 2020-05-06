package com.example.week11;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {
    EditText editText2;
    EditText editText3;
    EditText editText5;

    String koko = "";
    String rivi = "";
    String tekstia = "";

    Boolean B;
    Boolean I;
    Boolean E;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadLocale();
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(getResources().getString(R.string.app_name));

        setContentView(R.layout.activity_settings);
        editText2 = findViewById(R.id.editText2);
        editText5 = findViewById(R.id.editText5);
        editText3 = findViewById(R.id.editText3);

    }

    public void kieli(View view){
        final String[] list = {"Suomi", "English", "Svenska"};
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
        builder.setTitle("Valitse kieli");
        builder.setSingleChoiceItems(list, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(i == 0){
                    setLocale("fi");
                    recreate();
                }
                else if(i == 1){
                    setLocale("en");
                    recreate();
                }
                if(i == 2){
                    setLocale("sv");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My lang", lang);
        editor.apply();
    }
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My lang", "");
        setLocale(language);
    }

    public void tallennus(View view){

        tekstia = editText3.getText().toString();
        koko = editText2.getText().toString();
        rivi = editText5.getText().toString();
        Switch switch1 = (Switch) findViewById(R.id.switch1);
        Switch switch2 = (Switch) findViewById(R.id.switch2);
        Switch switch3 = (Switch) findViewById(R.id.switch3);

        if (switch1.isChecked()){
            B  = true;
        }else{
            B = false;
        }
        if (switch2.isChecked()){
            I  = true;
        }else{
            I = false;
        }

        if (switch3.isChecked()){
            E  = true;
        }else{
            E = false;
        }


    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        intent.putExtra("key1", koko);
        intent.putExtra("key2", B);
        intent.putExtra("key3", I);
        intent.putExtra("key4", rivi);
        intent.putExtra("key5", E);
        intent.putExtra("key6", tekstia);

        setResult(RESULT_OK, intent);

        finish();
    }

}
