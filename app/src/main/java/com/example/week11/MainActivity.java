package com.example.week11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.drawerlayout.widget.DrawerLayout.LayoutParams;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    TextView textView;
    TextView textView2;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        editText = findViewById((R.id.editText));



        textView.setText("Moi Kalle, miten menee. Huriseeko hyvin? Täällä hurisee hyvin!");
        dl = (DrawerLayout)findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();


                if(id == R.id.settings){
                    Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivityForResult(intent, 1);
                }

                return true;
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                String koko = data.getStringExtra("key1");
                Boolean bold = data.getBooleanExtra("key2", false);
                Boolean italic = data.getBooleanExtra("key3", false);
                Boolean edit = data.getBooleanExtra("key5", false);
                String rivi = data.getStringExtra("key4");
                String tekstia = data.getStringExtra("key6");

                System.out.println(tekstia);

                textView2.setText(tekstia);

                if (koko.length() != 0){
                    float k = Float.parseFloat(koko);
                    textView.setTextSize(k);
                }
                if (bold == true && italic == false) {
                    textView.setTypeface(null, Typeface.BOLD);
                }
                if (italic == true && bold == false) {
                    textView.setTypeface(null, Typeface.ITALIC);
                }

                if (italic == true && bold == true) {
                    textView.setTypeface(null, Typeface.BOLD_ITALIC);
                }

                if (italic == false && bold == false) {
                    textView.setTypeface(null, Typeface.NORMAL);
                }

                if (rivi.length() != 0) {
                    int r = Integer.parseInt(rivi);
                    textView.setLines(r);
                }

                if(edit == true) {
                    editText.setEnabled(true);
                }else{
                    editText.setEnabled(false);
                }
            }
        }

    }

}
