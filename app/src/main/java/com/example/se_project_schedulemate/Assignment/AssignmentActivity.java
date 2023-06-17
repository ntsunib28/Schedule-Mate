package com.example.se_project_schedulemate.Assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.se_project_schedulemate.Alarm.AlarmsPageActivity;
import com.example.se_project_schedulemate.Forum.ForumsActivity;
import com.example.se_project_schedulemate.Models.User;
import com.example.se_project_schedulemate.MyInterface;
import com.example.se_project_schedulemate.R;
import com.example.se_project_schedulemate.SettingsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.util.Vector;

public class AssignmentActivity extends AppCompatActivity implements MyInterface {
    RecyclerView rv_Assignment;
    Vector<Assignment> assignmentVector;
    ImageView settingsBtn;
    TextView tvDisplayName, tvNim;
    private FirebaseAuth auth;
    private DatabaseReference mReference;
    final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://schedule-mate-70c31-default-rtdb.asia-southeast1.firebasedatabase.app/");


    public void init(){
        rv_Assignment = findViewById(R.id.rv_Assignment);
        assignmentVector = new Vector<>();
        assignmentVector.add(new Assignment("Asg1",
                new Timestamp(22,12,22,7,0,0,0), 7,
                "Dosen1",
                new Timestamp(21,12,22,7,0,0,0)));
        assignmentVector.add(new Assignment("Asg2",
                new Timestamp(20,12,22,7,0,0,0), 7,
                "Dosen 2",
                new Timestamp(24,12,22,7,0,0,0)));
        assignmentVector.add(new Assignment("Asg3",
                new Timestamp(20,12,22,7,0,0,0), 7,
                "Dosen 3",
                new Timestamp(24,12,22,7,0,0,0)));

        AssignmentAdapter adapter = new AssignmentAdapter(this, this);
        adapter.setAssignments(assignmentVector);

        rv_Assignment.setAdapter(adapter);
        rv_Assignment.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.assignments_menu);

        init();

//        FIREBASE
        auth = FirebaseAuth.getInstance();
        mReference = mDatabase.getReference().child("user_data").child(auth.getUid());
//                                    END

        tvDisplayName = findViewById(R.id.tvDisplayName);
        tvNim = findViewById(R.id.tvNim);
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userTemp = snapshot.getValue(User.class);
                tvDisplayName.setText(userTemp.getName());
                tvNim.setText(userTemp.getNim());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        //Setting dipencet
        settingsBtn = findViewById(R.id.settingsBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssignmentActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if(item.getItemId() == R.id.alarms_menu){
                        Intent intent = new Intent(AssignmentActivity.this, AlarmsPageActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else if (item.getItemId() == R.id.forums_menu) {
                        Intent intent = new Intent(AssignmentActivity.this, ForumsActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    return true;
                }
            };

    @Override
    public void onClick(int position) {
    }

}