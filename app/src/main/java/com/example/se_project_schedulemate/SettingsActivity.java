package com.example.se_project_schedulemate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.se_project_schedulemate.Login.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    CardView logoutBtn = findViewById(R.id.logoutBtn);

    // Font yg di input
//    Typeface boldFont = Typeface.createFromAsset(getAssets(), "JosefinSans-Bold.ttf");
//    Typeface regularFont = Typeface.createFromAsset(getAssets(), "JosefinSans-Regular.ttf");

    //Class yg fontnya ingin di ganti
    TextView username = findViewById(R.id.username);
    TextView nim = findViewById(R.id.nim);
    TextView header = findViewById(R.id.header);
    TextView subHeader1 = findViewById(R.id.subHeader1);
    TextView descClass = findViewById(R.id.descClass);
    TextView subHeader2 = findViewById(R.id.subHeader2);
    TextView descAsg1 = findViewById(R.id.descAsg1);
    TextView descAsg2 = findViewById(R.id.descAsg2);
    TextView subHeader3 = findViewById(R.id.subHeader3);
    TextView descForum1 = findViewById(R.id.descForum1);
    TextView descForum2 = findViewById(R.id.descForum2);
    TextView applyText = findViewById(R.id.applyText);

//    TextView logoutText = findViewById(R.id.logoutText);
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
        }else{
            username.setText(user.getEmail());
        }

//        // Apply fontnya
//        header.setTypeface(boldFont);
//        subHeader1.setTypeface(boldFont);
//        subHeader2.setTypeface(boldFont);
//        subHeader3.setTypeface(boldFont);
//
//        username.setTypeface(regularFont);
//        nim.setTypeface(regularFont);
//        descClass.setTypeface(regularFont);
//        descAsg2.setTypeface(regularFont);
//        descAsg1.setTypeface(regularFont);
//        descForum1.setTypeface(regularFont);
//        descForum2.setTypeface(regularFont);
//
//        applyText.setTypeface(boldFont);
//        logoutText.setTypeface(boldFont);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
    }


}