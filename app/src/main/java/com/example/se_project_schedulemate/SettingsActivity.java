package com.example.se_project_schedulemate;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {
    // Font yg di input
    Typeface boldFont = Typeface.createFromAsset(getAssets(), "JosefinSans-Bold.ttf");
    Typeface regularFont = Typeface.createFromAsset(getAssets(), "JosefinSans-Regular.ttf");

    //Class yg fontnya ingin di ganti
    TextView profile = findViewById(R.id.profile);
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
    TextView logoutText = findViewById(R.id.logoutText);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Apply fontnya
        header.setTypeface(boldFont);
        subHeader1.setTypeface(boldFont);
        subHeader2.setTypeface(boldFont);
        subHeader3.setTypeface(boldFont);

        profile.setTypeface(regularFont);
        descClass.setTypeface(regularFont);
        descAsg2.setTypeface(regularFont);
        descAsg1.setTypeface(regularFont);
        descForum1.setTypeface(regularFont);
        descForum2.setTypeface(regularFont);

        applyText.setTypeface(boldFont);
        logoutText.setTypeface(boldFont);
    }


}