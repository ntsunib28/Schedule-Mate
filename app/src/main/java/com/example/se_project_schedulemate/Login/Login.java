package com.example.se_project_schedulemate.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.se_project_schedulemate.Alarm.AlarmsPageActivity;
import com.example.se_project_schedulemate.R;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonLogin = findViewById(R.id.llogin);
        EditText ltfname = findViewById(R.id.ltfname);
        EditText ltfpw = findViewById(R.id.ltfpw);

        progressBar = findViewById(R.id.progressBar);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ltfname.getText().toString().trim();
                String password = ltfpw.getText().toString().trim();

                progressBar.setVisibility(View.VISIBLE);

                if (username.isEmpty()) {
                    progressBar.setVisibility(View.GONE);
                    ltfname.setError("Username cannot be empty");
                } else if (password.isEmpty()) {
                    progressBar.setVisibility(View.GONE);
                    ltfpw.setError("Password cannot be empty");
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(Login.this, "Welcome " + username, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Login.this, AlarmsPageActivity.class);
                    intent.putExtra("Username", username);
                    startActivity(intent);
                }
            }
        });
    }

    private void setupFirebaseAuth(){
        mAuth = FirebaseAuth.getInstance();

    }

}