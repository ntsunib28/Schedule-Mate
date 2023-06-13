package com.example.se_project_schedulemate.Forum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.se_project_schedulemate.MyInterface;
import com.example.se_project_schedulemate.R;

import java.sql.Timestamp;
import java.util.Vector;

public class ForumsActivity extends AppCompatActivity implements MyInterface {

    RecyclerView rv_forum;
    Vector<Forum> forumList;

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
        init();
    }

    @Override
    public void onClick(int position) {

    }

}