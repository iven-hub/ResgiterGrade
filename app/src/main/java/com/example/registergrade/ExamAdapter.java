package com.example.registergrade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder>{
    private Context context;
    private ArrayList<Student> studentList;

    public ExamAdapter( Context context,ArrayList<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.aluno, parent, false);
        return new ExamAdapter.ExamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.student_id.setText(student.getNome());
        holder.grade_id.setText(String.valueOf(student.getMedia()));
    }

    @Override
    public int getItemCount() {
        if(studentList != null ){
            return studentList.size();
        }
        return 0;
    }


    class ExamViewHolder extends RecyclerView.ViewHolder {
        TextView student_id, grade_id;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            student_id = itemView.findViewById(R.id.student_id);
            grade_id = itemView.findViewById(R.id.grade_id);

        }
    }
}
