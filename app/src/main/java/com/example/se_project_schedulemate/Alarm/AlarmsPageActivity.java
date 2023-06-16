package com.example.se_project_schedulemate.Alarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.se_project_schedulemate.Assignment.AssignmentActivity;
import com.example.se_project_schedulemate.Forum.ForumsActivity;
import com.example.se_project_schedulemate.Login.Login;
import com.example.se_project_schedulemate.MyInterface;
import com.example.se_project_schedulemate.R;
import com.example.se_project_schedulemate.SettingsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.util.Vector;

import com.example.se_project_schedulemate.Models.User;

public class AlarmsPageActivity extends AppCompatActivity implements MyInterface {

    RecyclerView rv_AlarmRecycler;
    Vector<Alarm> alarmList;
    ImageView settingsBtn;
    TextView username, nim;


    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authListener;
    FirebaseUser user;
    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
    // Ini tes langsung pake url
    final FirebaseDatabase mDatabase2 = FirebaseDatabase.getInstance("https://schedule-mate-70c31-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference mReference2 = mDatabase2.getReference();
    String userId, usernameString;

    private void init(){


        rv_AlarmRecycler = (RecyclerView) findViewById(R.id.rv_alarms);
        alarmList = new Vector<>();

        mReference2 = mDatabase2.getReference("class_schedule/LA01/la01_software_engineering/Session 4");

        alarmList.add(new Alarm(
                "Bukan dari Firebase",
                new Timestamp(2023, 11, 28, 14, 20, 0, 0),
                "LEC - Session 7",
                new Timestamp(0, 0, 0, 9, 20, 0, 0),
                new Timestamp(0, 0, 0, 11, 0 ,0, 0)
        ));

        AlarmAdapter adapter = new AlarmAdapter(this, this);
        adapter.setAlarmAdapterItem(alarmList);

        rv_AlarmRecycler.setAdapter(adapter);
        rv_AlarmRecycler.setLayoutManager(new LinearLayoutManager(this));


        mReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                AlarmObject TempAlarm = snapshot.getValue(AlarmObject.class);

                alarmList.add(new Alarm(
                        TempAlarm.getTitle().toString(),
                        TempAlarm.createAlarmActivation(),
                        TempAlarm.getDescription(),
                        TempAlarm.createScheduleStartTime(),
                        TempAlarm.createScheduleEndTime()
                ));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarms_page);

        //        Bottom NavBar
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.alarms_menu);


        //Setting dipencet
        settingsBtn = findViewById(R.id.settingsBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmsPageActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });


        //FIREBASE
        //        Usernya logged in apa engga
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if (user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        // Ambil data user

        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("UserData", "USER ID " + userId);




        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user1 = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //INIT
        username = findViewById(R.id.username);
        nim = findViewById(R.id.nim);
        init();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if(item.getItemId() == R.id.assignments_menu){
                        Intent intent = new Intent(AlarmsPageActivity.this, AssignmentActivity.class);
                        startActivity(intent);
                    }
                    else if (item.getItemId() == R.id.forums_menu) {
                        Intent intent = new Intent(AlarmsPageActivity.this, ForumsActivity.class);
                        startActivity(intent);
                    }

                    return true;
                }
            };

    @Override
    public void onClick(int position) {
        Toast.makeText(this, alarmList.get(position).getAlarmTitle(), Toast.LENGTH_SHORT).show();
//        viewAlarmDetails(position);
    }
}