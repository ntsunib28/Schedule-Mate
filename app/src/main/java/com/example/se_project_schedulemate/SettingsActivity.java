package com.example.se_project_schedulemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.se_project_schedulemate.Login.Login;
import com.example.se_project_schedulemate.Models.Settings;
import com.example.se_project_schedulemate.Models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingsActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    private DatabaseReference mReference;
    final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://schedule-mate-70c31-default-rtdb.asia-southeast1.firebasedatabase.app/");

    CardView logoutBtn, applySettings;
    ImageView backBtn;
    NumberPicker npClassSettings, npARSettings, npForumSettings;
    TextView descClass, descAsg2, descForum2;
    int asg, classM, forum;
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

        mReference = mDatabase.getReference().child("user_settings").child(auth.getUid());
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Settings settingTemp = snapshot.getValue(Settings.class);
                npClassSettings.setValue(settingTemp.getClass_alarm_minutes());
                npARSettings.setValue(settingTemp.getAssignment_reminder_days());
                npForumSettings.setValue(settingTemp.getForum_reminder_days());

                descClass.setText(settingTemp.getClass_alarm_minutes() + " Minutes Before");
                descAsg2.setText(settingTemp.getAssignment_reminder_days() + " Days");
                descForum2.setText(settingTemp.getForum_reminder_days() + " Days");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



//        NUMBER PICKER
        npClassSettings = findViewById(R.id.npClassSettings);
        descClass = findViewById(R.id.descClass);
        npClassSettings.setMinValue(1);
        npClassSettings.setMaxValue(60);
      npClassSettings.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                descClass.setText(newVal + " Minutes Before");
                classM = newVal;
            }
        });

        npARSettings = findViewById(R.id.npARSettings);
        descAsg2 = findViewById(R.id.descAsg2);
        npARSettings.setMinValue(1);
        npARSettings.setMaxValue(30);
      npARSettings.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                descAsg2.setText(newVal + " Days");
                asg = newVal;
            }
        });

        npForumSettings = findViewById(R.id.npForumSettings);
        descForum2 = findViewById(R.id.descForum2);
        npForumSettings.setMinValue(1);
        npForumSettings.setMaxValue(30);
        npForumSettings.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                descForum2.setText(newVal + " Days");
                forum = newVal;
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
                if(asg != 0){
                    mReference.child("assignment_reminder_days").setValue(asg);
                }
                if(classM != 0){
                    mReference.child("class_alarm_minutes").setValue(classM);
                }
                if(forum != 0){
                    mReference.child("forum_reminder_days").setValue(forum);
                }
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