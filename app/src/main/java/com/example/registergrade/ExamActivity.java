package com.example.registergrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ExamActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExamAdapter adapter;
    private ArrayList<Student> studentList;
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        recyclerView = findViewById(R.id.exam_id);
        dbHelper = new SQLiteHelper(this);// cria um objeto SQLiteHelper e inicializa
        studentList = dbHelper.getExamStudents();//e pega a lista de estudantes aprovados usando o método getApprovedStudents.
        adapter = new ExamAdapter(this,studentList);//passar lista de estudantes
        recyclerView.setAdapter(adapter);//apanhar informacoes e layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}