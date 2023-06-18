package com.example.se_project_schedulemate.Assignment;

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

import com.example.se_project_schedulemate.Alarm.Alarm;
import com.example.se_project_schedulemate.Alarm.AlarmObject;
import com.example.se_project_schedulemate.Alarm.AlarmsPageActivity;
import com.example.se_project_schedulemate.Forum.ForumsActivity;
import com.example.se_project_schedulemate.Login.Login;
import com.example.se_project_schedulemate.Models.User;
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

public class AssignmentActivity extends AppCompatActivity implements MyInterface {
    RecyclerView rv_Assignment;
    Vector<Assignment> assignmentVector;
    ImageView settingsBtn;
    TextView tvDisplayName, tvNim;
    private FirebaseAuth auth;
    FirebaseUser user;
    private DatabaseReference mReference, mReference2, mReference3;
    final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://schedule-mate-70c31-default-rtdb.asia-southeast1.firebasedatabase.app/");
    boolean addTo;
    String lecturer;

    public void init(){
        rv_Assignment = findViewById(R.id.rv_Assignment);
        assignmentVector = new Vector<>();


        mReference = mDatabase.getReference().child("user_data").child(auth.getUid());



        AssignmentAdapter adapter = new AssignmentAdapter(this, this);
        adapter.setAssignments(assignmentVector);

        rv_Assignment.setAdapter(adapter);
        rv_Assignment.setLayoutManager(new LinearLayoutManager(this));

//        FIREBASE DISPLAY PROFILE

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

//        END


        mReference2 = mDatabase.getReference("class_groups/");

        List<String> classes_list = new ArrayList<>();
        mReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot _snapshot: snapshot.getChildren()) {
                    String tempAdd = _snapshot.getKey();
                    classes_list.add(tempAdd);
                    Log.d("Kelas Read ASG wait", _snapshot.getKey());
                }

                addTo = false;
                for (int i = 0; i < classes_list.size(); i++){
                    String kelas = classes_list.get(i);
                    Log.d("TCEYB", "Iterasi kelas " + kelas );
                    DatabaseReference temp = mDatabase.getReference("class_groups/" + kelas + "/students/");
                    String userID = user.getUid();
                    temp.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot _snapshot: snapshot.getChildren()) {
                                // if id user muncul disini,
                                // THEN
                                // add Kelas.
                                if (userID.equals(_snapshot.getKey())) {
                                    addTo = true;
//                                    user_class.add(kelas);
                                    Log.d("snapshot gc found", _snapshot.getKey());
                                    Log.d("snapshot asg found", kelas + " added");
                                    break;
                                } else {
                                    addTo = false;
                                    Log.d("snapshot gc", "ganemu in " + kelas);
                                    Log.d("snapshot asg", _snapshot.getKey());
                                }
                            }

                            if(addTo){
                                mReference3 = mDatabase.getReference("class_groups/" + kelas + "/lecturer");
                                Log.d("IF", "onDataChange: START OF ADD TO IF");
                                mReference3.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        lecturer = snapshot.getKey();
//                                        Log.d("Lecturer Name", snapshot.getValue().toString());
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    });

                                mReference3 = mDatabase.getReference("class_assignment/" + kelas + "/");
                                mReference3.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        AssignmentObject tempAsg;

                                        for(DataSnapshot _snapshot: snapshot.getChildren()) {
                                            Log.d("FIRST KEY", _snapshot.getKey());
                                                for(DataSnapshot _snapshot2 : _snapshot.getChildren()){
                                                    Log.d("ASG", _snapshot2.getKey());
                                                    Log.d("ASG", _snapshot2.getValue().toString());
                                                     tempAsg = _snapshot2.getValue(AssignmentObject.class);
                                                    Log.d("Title", tempAsg.getTitle());

                                                    assignmentVector.add(new Assignment(
                                                            tempAsg.getTitle(),
                                                            tempAsg.createDeadlineDate(),
                                                            tempAsg.getSession()
                                                    , lecturer
                                                    , tempAsg.createDeadlineDate()));


//                                                    assignmentVector.add(new Assignment(tempAsg.getTitle(), new Timestamp(0), 7, lecturer, new Timestamp(0)));
                                                    Log.d("MASUK OR NOT", assignmentVector.get(0).getAssignmentName());
                                                    Log.d("MASUK OR NOT", assignmentVector.get(0).getLecturer());
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.assignments_menu);

        init();


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