package com.example.se_project_schedulemate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Vector;

public class AssignmentActivity extends AppCompatActivity implements MyInterface{
    RecyclerView rv_Asignment;
    Vector<Assignment> assignmentVector;

    public void init(){
        rv_Asignment = findViewById(R.id.rv_Building);
        assignmentVector = new Vector<>();
        assignmentVector.add(new Building("Building A", 5000, R.drawable.anggrek));
        buildingVector.add(new Building("Building A", 5000, R.drawable.anggrek));
        buildingVector.add(new Building("Building A", 5000, R.drawable.bekasi));
        buildingVector.add(new Building("Building A", 5000, R.drawable.anggrek));
        buildingVector.add(new Building("Building A", 5000, R.drawable.anggrek));

        BuildingAdapter adapter = new BuildingAdapter(this, this);
        adapter.setBuildings(buildingVector);

        rv_Building.setAdapter(adapter);
        rv_Building.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
    }

    @Override
    public void onClick(int position) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}