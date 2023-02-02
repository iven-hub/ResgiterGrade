package com.example.registergrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ApprovedActivity extends AppCompatActivity {
    //variaveis
    private RecyclerView recyclerView;
    private StateAdapter adapter;
    private ArrayList<Student> studentList;
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approved);
        //recycle
        recyclerView = findViewById(R.id.approved_id);
        dbHelper = new SQLiteHelper(this);// cria um objeto SQLiteHelper e inicializa db
        studentList = dbHelper.getApprovedStudents();//e pega a lista de estudantes aprovados usando o método getApprovedStudents.
        adapter = new StateAdapter(this, studentList);//passar lista de estudantes
        recyclerView.setAdapter(adapter);//apanhar informacoes e layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}