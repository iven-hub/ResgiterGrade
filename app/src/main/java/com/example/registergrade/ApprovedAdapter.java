package com.example.registergrade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
//extender recycle adpater defenir um classe interna para no adapter do nosso recycle
public class ApprovedAdapter extends RecyclerView.Adapter< ApprovedAdapter.ApprovedViewHolder>{
    private Context context;
    private ArrayList<Student> studentList;
    private SQLiteHelper dbHelper;
    private View view;

    public ApprovedAdapter( Context context,ArrayList<Student> studentList) {//apanhar os valores das nossas variaveis
        this.context = context;//inflar layout
        this.studentList = studentList;
    }

    @NonNull
    @Override
    //vamos inflar nosso layout e dar aparencia a cada um das nossas coluna
    public ApprovedAdapter.ApprovedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.aluno, parent, false);
        return new ApprovedViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ApprovedAdapter.ApprovedViewHolder holder, int position) {
        //ter acesso aos dados
        Student student = studentList.get(position);
        holder.student_id.setText(student.getNome());
        holder.grade_id.setText(String.valueOf(student.getMedia()));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu menu = new PopupMenu(context,v);
                menu.getMenu().add("DELETE");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().equals("DELETE")){
                            dbHelper.deletedata(student.getId());
                            studentList.remove(student);
                            notifyDataSetChanged();
                        }
                        return true;
                    }
                });
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if(studentList != null ){
            return studentList.size();
        }
        return 0;
    }
    //ela é responsável por armazenar as referências das visões da linha de item da lista de estudantes no exame.
    class ApprovedViewHolder extends RecyclerView.ViewHolder {
        //esqueleto no nosso recycle vai apanhar as views do nosso recycle-view layout tipo um one create metodo
        //evitando o custo de criar uma nova visualização para cada item.
        TextView student_id, grade_id;

        public ApprovedViewHolder(@NonNull View itemView) {
            super(itemView);
            student_id = itemView.findViewById(R.id.student_id);
            grade_id = itemView.findViewById(R.id.grade_id);

        }
    }
}

