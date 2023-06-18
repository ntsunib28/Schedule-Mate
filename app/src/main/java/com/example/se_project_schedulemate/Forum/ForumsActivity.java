package com.example.se_project_schedulemate.Forum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.se_project_schedulemate.Alarm.Alarm;
import com.example.se_project_schedulemate.Alarm.AlarmObject;
import com.example.se_project_schedulemate.Alarm.AlarmsPageActivity;
import com.example.se_project_schedulemate.Assignment.AssignmentActivity;
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

public class ForumsActivity extends AppCompatActivity implements MyInterface {

    RecyclerView rv_forum;
    Vector<Forum> forumList;
    ImageView settingBtn;
    TextView tvDisplayName, tvNim;
    private FirebaseAuth auth;
    private DatabaseReference mReference;
    final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://schedule-mate-70c31-default-rtdb.asia-southeast1.firebasedatabase.app/");

    boolean addTo = false;
    private void init(){
        rv_forum = findViewById(R.id.rv_forums);
        forumList = new Vector<>();

        ForumAdapter adapter = new ForumAdapter(this, this);
        adapter.setForumAdapterItems(forumList);
        rv_forum.setAdapter(adapter);
        rv_forum.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference tempReference = mDatabase.getReference("class_groups");
        List<String> classes_list = new ArrayList<String>();
        List<String> user_class = new ArrayList<String>();

        // START ADD ISI FORUM LEWAT DATABASE
        // START ADD ISI FORUM LEWAT DATABASE
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

                            String userID = auth.getUid();
                            for(DataSnapshot _snapshot: snapshot.getChildren()) {
                                // if id user muncul disini,
                                // THEN
                                // add Kelas.
                                if(userID.compareTo(_snapshot.getKey()) == 0){
                                    addTo = true;
//                                   user_class.add(kelas);
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
                                mReference = mDatabase.getReference("forums/" + kelas + "/");
                                mReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        for(DataSnapshot _snapshot: snapshot.getChildren()) {

                                            for(DataSnapshot _snapshot2 : _snapshot.getChildren()){
                                                ForumObject TempForum = _snapshot2.getValue(ForumObject.class);

                                                forumList.add(new Forum(
                                                        TempForum.getTitle(),
                                                        TempForum.getDescription(),
                                                        TempForum.getLecturer_id(),
                                                        TempForum.createDeadline()
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
        // END ISI FORUM LEWAT DATABASE
        // END ISI FORUM LEWAT DATABASE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forums);

        settingBtn = findViewById(R.id.settingsBtn);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.forums_menu);

        //Setting btn kanan atas
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForumsActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });




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

        init();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if(item.getItemId() == R.id.alarms_menu){
                        Intent intent = new Intent(ForumsActivity.this, AlarmsPageActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else if (item.getItemId() == R.id.assignments_menu) {
                        Intent intent = new Intent(ForumsActivity.this, AssignmentActivity.class);
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