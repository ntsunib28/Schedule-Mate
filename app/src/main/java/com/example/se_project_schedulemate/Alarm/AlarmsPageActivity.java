package com.example.se_project_schedulemate.Alarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.se_project_schedulemate.MyInterface;
import com.example.se_project_schedulemate.R;

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
        init();
    }

    @Override
    public void onClick(int position) {

    }
}