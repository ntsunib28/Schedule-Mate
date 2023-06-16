package com.example.se_project_schedulemate.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.se_project_schedulemate.Alarm.Alarm;
import com.example.se_project_schedulemate.Alarm.AlarmObject;
import com.example.se_project_schedulemate.Alarm.AlarmsPageActivity;
import com.example.se_project_schedulemate.Models.User;
import com.example.se_project_schedulemate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private FirebaseAuth auth;
    private DatabaseReference mReference;
    final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://schedule-mate-70c31-default-rtdb.asia-southeast1.firebasedatabase.app/");

    ProgressBar progressBar;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(Login.this, AlarmsPageActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();


        Button buttonLogin = findViewById(R.id.llogin);
        EditText ltfemail = findViewById(R.id.ltfemail);
        EditText ltfpw = findViewById(R.id.ltfpw);

        progressBar = findViewById(R.id.progressBar);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ltfemail.getText().toString().trim();
                String password = ltfpw.getText().toString().trim();

                progressBar.setVisibility(View.VISIBLE);

                if (email.isEmpty()) {
                    progressBar.setVisibility(View.GONE);
                    ltfemail.setError("Email cannot be empty");
                    return;
                }

                if (password.isEmpty()) {
                    progressBar.setVisibility(View.GONE);
                    ltfpw.setError("Password cannot be empty");
                    return;
                }

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.VISIBLE);

//                                    FIREBASE
                                    mReference = mDatabase.getReference().child("user_data").child(auth.getUid());
//                                    END
                                    mReference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            User userTemp = snapshot.getValue(User.class);
                                            Toast.makeText(getApplicationContext(), "Welcome " + userTemp.getName(), Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    });


                                    Intent intent = new Intent(Login.this, AlarmsPageActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText( Login.this,
                                            "Make sure your email and password are correct", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

}