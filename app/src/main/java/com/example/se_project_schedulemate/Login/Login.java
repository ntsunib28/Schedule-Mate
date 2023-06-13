package com.example.se_project_schedulemate.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.se_project_schedulemate.Alarm.AlarmsPageActivity;
import com.example.se_project_schedulemate.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonLogin = findViewById(R.id.llogin);
        EditText ltfname = findViewById(R.id.ltfname);
        EditText ltfpw = findViewById(R.id.ltfpw);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ltfname.getText().toString().trim();
                String password = ltfpw.getText().toString().trim();

                if (username.isEmpty()) {
                    ltfname.setError("Username cannot be empty");
                } else if (password.isEmpty()) {
                    ltfpw.setError("Password cannot be empty");
                } else if (password.length() < 8) {
                    ltfpw.setError("Password must have at least 8 characters");
                } else {
                    Intent intent = new Intent(Login.this, AlarmsPageActivity.class);
                    intent.putExtra("Username", username);
                    startActivity(intent);
                }
            }
        });
    }
}