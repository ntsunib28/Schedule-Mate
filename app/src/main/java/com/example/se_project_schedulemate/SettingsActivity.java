package com.example.se_project_schedulemate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.se_project_schedulemate.Login.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authListener;


    FirebaseUser user;
    ImageView logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        logoutBtn = findViewById(R.id.logoutBtn);

        if (user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
//        else{
//            username.setText(user.getEmail());
//        }

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