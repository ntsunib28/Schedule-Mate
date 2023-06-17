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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.example.se_project_schedulemate.Models.User;

public class AlarmsPageActivity extends AppCompatActivity implements MyInterface {

    RecyclerView rv_AlarmRecycler;
    Vector<Alarm> alarmList;
    ImageView settingsBtn;
    TextView tvDisplayName, tvNim;


    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authListener;
    FirebaseUser user;
    // Ini tes langsung pake url
    final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://schedule-mate-70c31-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference mReference = mDatabase.getReference();

    // value buat init.
    int class_list_length = 0;
    boolean addTo = false;

    public void viewAlarmDetails(int position) {
        String alarmTitle = alarmList.get(position).getAlarmTitle();
        String alarmDesc = alarmList.get(position).getAlarmDescription();
        //        Integer deadlineYear = assignmentVector.get(position).getDeadline().getYear();
        //        Integer deadlineMonth = assignmentVector.get(position).getDeadline().getMonth();
        //        Integer deadlineDay = assignmentVector.get(position).getDeadline().getDate();
        //        Integer deadlineHour = assignmentVector.get(position).getDeadline().getHours();
        //        Integer deadlineMinute = assignmentVector.get(position).getDeadline().getMinutes();


        Intent intentAsgDetail = new Intent(this, SetAlarm.class);
        intentAsgDetail.putExtra("Title", alarmTitle);
        intentAsgDetail.putExtra("Description", alarmDesc);
        //        intentAsgDetail.putExtra("Deadline Year", deadlineYear);
        //        intentAsgDetail.putExtra("Deadline Month", deadlineMonth);
        //        intentAsgDetail.putExtra("Deadline Day", deadlineDay);
        //        intentAsgDetail.putExtra("Deadline Hour", deadlineHour);
        //        intentAsgDetail.putExtra("Deadline Minute", deadlineMinute);
        startActivity(intentAsgDetail);
    }

    private void init(){
        rv_AlarmRecycler = (RecyclerView) findViewById(R.id.rv_alarms);
        alarmList = new Vector<>();


        AlarmAdapter adapter = new AlarmAdapter(this, this);
        adapter.setAlarmAdapterItem(alarmList);

        rv_AlarmRecycler.setAdapter(adapter);
        rv_AlarmRecycler.setLayoutManager(new LinearLayoutManager(this));

        // ini method2 biar ngebaca semua kelar si orangnya.
        DatabaseReference tempReference = mDatabase.getReference("class_groups/");
        List<String> classes_list = new ArrayList<String>();
        List<String> user_class = new ArrayList<String>();

        // Start read id siswa per kelas
        tempReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot _snapshot: snapshot.getChildren()) {
                    String tempAdd = _snapshot.getKey().toString();
                    classes_list.add(tempAdd);
                    Log.d("Kelas Read wait", _snapshot.getKey());
                }

                for(int ii = 0; ii < classes_list.size(); ii++){
                    addTo = false;
                    String kelas = classes_list.get(ii);
                    DatabaseReference tempReference2 = mDatabase.getReference("class_groups/" + kelas + "/students/");
                    tempReference2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String userID = user.getUid();
                            for(DataSnapshot _snapshot: snapshot.getChildren()) {
                                // if id user muncul disini,
                                // THEN
                                // add Kelas.
                                if(userID.compareTo(_snapshot.getKey()) == 0){
                                    addTo = true;
//                                    user_class.add(kelas);
                                    Log.d("snapshot gc found", _snapshot.getKey());
                                    Log.d("snapshot gc found", kelas + "added");
                                    break;
                                } else {
                                    addTo = false;
                                    Log.d("snapshot gc", "ganemu in " + kelas );
                                    Log.d("snapshot gc", _snapshot.getKey());
                                    Log.d("snapshot gc", userID);
                                }

                            }

                            if(addTo == true){
                                mReference = mDatabase.getReference("class_schedule/" + kelas + "/");
                                mReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        for(DataSnapshot _snapshot: snapshot.getChildren()) {

                                            for(DataSnapshot _snapshot2 : _snapshot.getChildren()){
                                                AlarmObject TempAlarm = _snapshot2.getValue(AlarmObject.class);

                                                alarmList.add(new Alarm(
                                                        TempAlarm.getTitle(),
                                                        TempAlarm.createAlarmActivation(),
                                                        TempAlarm.getDescription(),
                                                        TempAlarm.createScheduleStartTime(),
                                                        TempAlarm.createScheduleEndTime()
                                                ));
                                            }
                                        }
                                        adapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // End read id siswa per kelas

//        mReference = mDatabase.getReference("class_schedule/LA01/la01_software_engineering/");

//        mReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for(DataSnapshot _snapshot: snapshot.getChildren()) {
//                    AlarmObject TempAlarm = _snapshot.getValue(AlarmObject.class);
//
//                    alarmList.add(new Alarm(
//                            TempAlarm.getTitle().toString(),
//                            TempAlarm.createAlarmActivation(),
//                            TempAlarm.getDescription(),
//                            TempAlarm.createScheduleStartTime(),
//                            TempAlarm.createScheduleEndTime()
//                    ));
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });

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

        init();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if(item.getItemId() == R.id.assignments_menu){
                        Intent intent = new Intent(AlarmsPageActivity.this, AssignmentActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else if (item.getItemId() == R.id.forums_menu) {
                        Intent intent = new Intent(AlarmsPageActivity.this, ForumsActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    return true;
                }
            };

    @Override
    public void onClick(int position) {
        Toast.makeText(this, alarmList.get(position).getAlarmTitle(), Toast.LENGTH_SHORT).show();
        viewAlarmDetails(position);
    }
}