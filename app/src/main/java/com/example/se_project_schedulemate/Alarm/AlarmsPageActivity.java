package com.example.se_project_schedulemate.Alarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.se_project_schedulemate.Assignment.AssignmentActivity;
import com.example.se_project_schedulemate.Forum.ForumsActivity;
import com.example.se_project_schedulemate.MyInterface;
import com.example.se_project_schedulemate.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Timestamp;
import java.util.Vector;

public class AlarmsPageActivity extends AppCompatActivity implements MyInterface {

    RecyclerView rv_AlarmRecycler;
    Vector<Alarm> alarmList;

    private void init(){
        rv_AlarmRecycler = (RecyclerView) findViewById(R.id.rv_alarms);
        alarmList = new Vector<>();

        alarmList.add(new Alarm(
                "Software Engineering Class",
                new Timestamp(2023, 11, 12, 9, 0, 0, 0),
                "LEC - Session 7",
                new Timestamp(0, 0, 0, 9, 20, 0, 0),
                new Timestamp(0, 0, 0, 11, 0 ,0, 0)
        ));

        alarmList.add(new Alarm(
                "User Experience Class",
                new Timestamp(2023, 4, 12, 9, 0, 0, 0),
                "LEC - Session 11",
                new Timestamp(0, 0, 0, 9, 20, 0, 0),
                new Timestamp(0, 0, 0, 11, 0 ,0, 0)
        ));

        alarmList.add(new Alarm(
                "Software Engineering Class",
                new Timestamp(2023, 0, 12, 9, 0, 0, 0),
                "LEC - Session 7",
                new Timestamp(0, 0, 0, 9, 20, 0, 0),
                new Timestamp(0, 0, 0, 11, 0 ,0, 0)
        ));

        alarmList.add(new Alarm(
                "Software Engineering Class",
                new Timestamp(2023, 1, 12, 9, 0, 0, 0),
                "LEC - Session 7",
                new Timestamp(0, 0, 0, 9, 20, 0, 0),
                new Timestamp(0, 0, 0, 11, 0 ,0, 0)
        ));

        alarmList.add(new Alarm(
                "Software Engineering Class",
                new Timestamp(2023, 11, 12, 9, 0, 0, 0),
                "LEC - Session 7",
                new Timestamp(0, 0, 0, 9, 20, 0, 0),
                new Timestamp(0, 0, 0, 11, 0 ,0, 0)
        ));

        alarmList.add(new Alarm(
                "Software Engineering Class",
                new Timestamp(2023, 11, 12, 9, 0, 0, 0),
                "LEC - Session 7",
                new Timestamp(0, 0, 0, 9, 20, 0, 0),
                new Timestamp(0, 0, 0, 11, 0 ,0, 0)
        ));

        alarmList.add(new Alarm(
                "Software Engineering Class",
                new Timestamp(2023, 11, 28, 14, 20, 0, 0),
                "LEC - Session 7",
                new Timestamp(0, 0, 0, 9, 20, 0, 0),
                new Timestamp(0, 0, 0, 11, 0 ,0, 0)
        ));


        AlarmAdapter adapter = new AlarmAdapter(this, this);
        adapter.setAlarmAdapterItem(alarmList);

        rv_AlarmRecycler.setAdapter(adapter);
        rv_AlarmRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarms_page);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

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

    }
}