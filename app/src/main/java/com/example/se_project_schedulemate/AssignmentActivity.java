package com.example.se_project_schedulemate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.Vector;

public class AssignmentActivity extends AppCompatActivity implements MyInterface{
    RecyclerView rv_Assignment;
    Vector<Assignment> assignmentVector;

    public void init(){
        rv_Assignment = findViewById(R.id.rv_Assignment);
        assignmentVector = new Vector<>();
        assignmentVector.add(new Assignment("Asg",
                new Timestamp(22,12,22,7,0,0,0), 7,
                "Dosen",
                new Timestamp(21,12,22,7,0,0,0)));
        assignmentVector.add(new Assignment("Asg",
                new Timestamp(20,12,22,7,0,0,0), 7,
                "Dosen",
                new Timestamp(24,12,22,7,0,0,0)));
        assignmentVector.add(new Assignment("Asg",
                new Timestamp(20,12,22,7,0,0,0), 7,
                "Dosen",
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
        init();
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(this, assignmentVector.get(position).getAssignmentName(), Toast.LENGTH_SHORT).show();
    }

}