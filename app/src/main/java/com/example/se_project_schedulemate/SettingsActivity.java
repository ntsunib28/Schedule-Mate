package com.example.se_project_schedulemate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.se_project_schedulemate.Login.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsActivity extends AppCompatActivity {

    FirebaseAuth auth;


    FirebaseUser user;
    CardView logoutBtn, applySettings;
    ImageView backBtn;
    NumberPicker npClassSettings, npARSettings, npForumSettings;
    TextView descClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if (user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }


//        NUMBER PICKER
        npClassSettings = findViewById(R.id.npClassSettings);
        descClass = findViewById(R.id.descClass);
        npClassSettings.setMinValue(5);
        npClassSettings.setMinValue(60);
        npClassSettings.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.d("TES NP", "onValueChange: ");
                descClass.setText(newVal + "Minutes Before");
            }
        });

        npARSettings = findViewById(R.id.npARSettings);
        npARSettings.setMinValue(1);
        npARSettings.setMinValue(7);
        npARSettings.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });

        npForumSettings = findViewById(R.id.npForumSettings);
        npForumSettings.setMinValue(1);
        npForumSettings.setMinValue(7);
        npForumSettings.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });




//        BUTTON
        logoutBtn = findViewById(R.id.logoutBtn);
        applySettings = findViewById(R.id.applySettings);
        backBtn = findViewById(R.id.backBtn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        applySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}