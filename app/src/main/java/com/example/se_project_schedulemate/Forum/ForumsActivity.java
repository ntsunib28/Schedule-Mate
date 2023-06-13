package com.example.se_project_schedulemate.Forum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.se_project_schedulemate.Alarm.AlarmsPageActivity;
import com.example.se_project_schedulemate.Assignment.AssignmentActivity;
import com.example.se_project_schedulemate.MyInterface;
import com.example.se_project_schedulemate.R;
import com.example.se_project_schedulemate.SettingsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Timestamp;
import java.util.Vector;

public class ForumsActivity extends AppCompatActivity implements MyInterface {

    RecyclerView rv_forum;
    Vector<Forum> forumList;

    ImageView settingBtn;

    private void init(){
        rv_forum = findViewById(R.id.rv_forums);
        forumList = new Vector<>();

        forumList.add(new Forum(
                "LEC - Software Engineering",
                "Session 7",
                "Lecturer 101",
                new Timestamp(2023, 1, 12, 9, 0, 0, 0)
        ));

        forumList.add(new Forum(
                "LEC - Software Engineering",
                "Session 7",
                "Lecturer 101",
                new Timestamp(2023, 2, 12, 9, 0, 0, 0)
        ));

        forumList.add(new Forum(
                "LEC - Software Engineering",
                "Session 7",
                "Lecturer 101",
                new Timestamp(2023, 3, 12, 9, 0, 0, 0)
        ));

        forumList.add(new Forum(
                "LEC - Software Engineering",
                "Session 7",
                "Lecturer 101",
                new Timestamp(2023, 4, 12, 9, 0, 0, 0)
        ));

        forumList.add(new Forum(
                "LEC - Software Engineering",
                "Session 7",
                "Lecturer 101",
                new Timestamp(2023, 3, 12, 9, 0, 0, 0)
        ));

        ForumAdapter adapter = new ForumAdapter(this, this);
        adapter.setForumAdapterItems(forumList);
        rv_forum.setAdapter(adapter);
        rv_forum.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forums);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        init();

        //Setting btn kanan atas
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForumsActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(int position) {

    }


    BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if(item.getItemId() == R.id.alarms_menu){
                        Intent intent = new Intent(ForumsActivity.this, AlarmsPageActivity.class);
                        startActivity(intent);
                    }
                    else if (item.getItemId() == R.id.assignments_menu) {
                        Intent intent = new Intent(ForumsActivity.this, AssignmentActivity.class);
                        startActivity(intent);
                    }

                    return true;
                }
            };

}