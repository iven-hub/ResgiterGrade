package com.example.registergrade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ApprovedAdapter extends RecyclerView.Adapter< ApprovedAdapter.ApprovedViewHolder>{
    private Context context;
    private ArrayList<Student> studentList;

    public ApprovedAdapter( Context context,ArrayList<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public ApprovedAdapter.ApprovedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.aluno, parent, false);
        return new ApprovedViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ApprovedAdapter.ApprovedViewHolder holder, int position) {
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

    class ApprovedViewHolder extends RecyclerView.ViewHolder {
        TextView student_id, grade_id;

        public ApprovedViewHolder(@NonNull View itemView) {
            super(itemView);
            student_id = itemView.findViewById(R.id.student_id);
            grade_id = itemView.findViewById(R.id.grade_id);

        }
    }
}

