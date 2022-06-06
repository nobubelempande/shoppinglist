package com.viiishoppinglistapp.doit.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.viiishoppinglistapp.doit.R;

public class SettingsActivity extends AppCompatActivity {

    Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setupSwitch();
        ImageView icon = findViewById(R.id.imgIcon);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
            }

        });

    }

    private void setupSwitch() {
        aSwitch = findViewById(R.id.switchThemeMode);
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            aSwitch.setChecked(true);
        }
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
    }

    public void goToHome(View view){
        //goto Home page
        Bundle bundle = new Bundle();
        bundle.putString("list_name", "Default");
        bundle.putInt("ID", 0);
        Intent I = new Intent(this, TabbedHomeActivity.class);
        I.putExtras(bundle);
        this.startActivity(I);
    }

    public void goToHome(){
        //goto Home page
        Bundle bundle = new Bundle();
        bundle.putString("list_name", "Default");
        bundle.putInt("ID", 0);
        Intent I = new Intent(this, TabbedHomeActivity.class);
        I.putExtras(bundle);
        this.startActivity(I);
    }

}