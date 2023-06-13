package com.example.se_project_schedulemate.Assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.se_project_schedulemate.Alarm.AlarmsPageActivity;
import com.example.se_project_schedulemate.Forum.ForumsActivity;
import com.example.se_project_schedulemate.MyInterface;
import com.example.se_project_schedulemate.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Timestamp;
import java.util.Vector;

public class AssignmentActivity extends AppCompatActivity implements MyInterface {
    RecyclerView rv_Assignment;
    Vector<Assignment> assignmentVector;

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

    public void viewAsgDetails(int position) {
        String assignmentTitle = assignmentVector.get(position).getAssignmentName();
        Integer deadlineYear = assignmentVector.get(position).getDeadline().getYear();
        Integer deadlineMonth = assignmentVector.get(position).getDeadline().getMonth();
        Integer deadlineDay = assignmentVector.get(position).getDeadline().getDate();
        Integer deadlineHour = assignmentVector.get(position).getDeadline().getHours();
        Integer deadlineMinute = assignmentVector.get(position).getDeadline().getMinutes();


        Intent intentAsgDetail = new Intent(this, AssignmentDetail.class);
        intentAsgDetail.putExtra("Title", assignmentTitle);
        intentAsgDetail.putExtra("Deadline Year", deadlineYear);
        intentAsgDetail.putExtra("Deadline Month", deadlineMonth);
        intentAsgDetail.putExtra("Deadline Day", deadlineDay);
        intentAsgDetail.putExtra("Deadline Hour", deadlineHour);
        intentAsgDetail.putExtra("Deadline Minute", deadlineMinute);
        startActivity(intentAsgDetail);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        init();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if(item.getItemId() == R.id.alarms_menu){
                        Intent intent = new Intent(AssignmentActivity.this, AlarmsPageActivity.class);
                        startActivity(intent);
                    }
                    else if (item.getItemId() == R.id.forums_menu) {
                        Intent intent = new Intent(AssignmentActivity.this, ForumsActivity.class);
                        startActivity(intent);
                    }

                    return true;
                }
            };

    @Override
    public void onClick(int position) {
        Toast.makeText(this, assignmentVector.get(position).getAssignmentName(), Toast.LENGTH_SHORT).show();
    }

}