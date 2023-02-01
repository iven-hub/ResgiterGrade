package com.example.registergrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ExamActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ApprovedAdapter adapter;
    private ArrayList<Student> studentList;
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        recyclerView = findViewById(R.id.exam_id);

        dbHelper = new SQLiteHelper(this);
        studentList = dbHelper.getExamStudents();

        adapter = new ApprovedAdapter(this,studentList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}