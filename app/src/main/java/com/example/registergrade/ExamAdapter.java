package com.example.registergrade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//extender recycle adpater defenir um classe interna para no adapter do nosso recycle
public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {
    private Context context;
    private ArrayList<Student> studentList;

    public ExamAdapter(Context context, ArrayList<Student> studentList) {//apanhar os valores das nossas variaveis
        this.context = context;//inflar layout
        this.studentList = studentList;
    }

    @NonNull
    @Override
    //vamos inflar nosso layout e dar aparencia a cada um das nossas coluna
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.aluno, parent, false);
        return new ExamAdapter.ExamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        //ter acesso aos dados
        Student student = studentList.get(position);
        holder.student_id.setText(student.getNome());
        holder.grade_id.setText(String.valueOf(student.getMedia()));
    }

    @Override
    public int getItemCount() {
        if (studentList != null) {
            return studentList.size();
        }
        return 0;
    }

    //ela é responsável por armazenar as referências das visões da linha de item da lista de estudantes no exame.
    class ExamViewHolder extends RecyclerView.ViewHolder {
        //esqueleto no nosso recycle vai apanhar as views do nosso recycle-view layout tipo um one create metodo
        TextView student_id, grade_id;
        SQLiteHelper db;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            student_id = itemView.findViewById(R.id.student_id);
            grade_id = itemView.findViewById(R.id.grade_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(context, itemView);
                    popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.delete_menu:
                                    db = new SQLiteHelper(context);
                                    Integer id = studentList.get(getLayoutPosition()).getId();
                                    long res = db.deletedata(id);
                                    notifyDataSetChanged();

                                    if (res > 0) {
                                        studentList.remove(getLayoutPosition());
                                        notifyDataSetChanged();
                                    }
                                    Toast.makeText(context.getApplicationContext(), "Succeded Remove", Toast.LENGTH_SHORT).show();
                                    return true;
                                    default:
                                        return false;
                            }
                        }
                    });
                    popup.show();
                }
            });
        }
    }
}
