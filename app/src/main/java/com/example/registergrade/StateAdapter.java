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
public class StateAdapter extends RecyclerView.Adapter< StateAdapter.ApprovedViewHolder>{
    private Context context;
    private ArrayList<Student> studentList;
    private SQLiteHelper dbHelper;


    public StateAdapter(Context context, ArrayList<Student> studentList) {//apanhar os valores das nossas variaveis
        this.context = context;//inflar layout
        this.studentList = studentList;
    }

    @NonNull
    @Override
    //vamos inflar nosso layout e dar aparencia a cada um das nossas coluna
    public StateAdapter.ApprovedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.aluno, parent, false);
        return new ApprovedViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StateAdapter.ApprovedViewHolder holder, int position) {
        //ter acesso aos dados
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
    //ela é responsável por armazenar as referências das visões da linha de item da lista de estudantes no exame.
    class ApprovedViewHolder extends RecyclerView.ViewHolder {
        //esqueleto no nosso recycle vai apanhar as views do nosso recycle-view layout tipo um one create metodo
        //evitando o custo de criar uma nova visualização para cada item.
        TextView student_id, grade_id;
        SQLiteHelper db;

        public ApprovedViewHolder(@NonNull View itemView) {
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
                        public boolean onMenuItemClick(MenuItem item) {//ao realizarmos um click aparece o menu que contem o menu
                            switch (item.getItemId()) {
                                case R.id.delete_menu:
                                    db = new SQLiteHelper(context);//conexao com db
                                    Integer id = studentList.get(getLayoutPosition()).getId();//busca id na lista
                                    long res = db.deletedata(id);//usa o método deletedata para deletar o item da base de dados,
                                    notifyDataSetChanged();
                                    //se o delete do db for bem sucedido
                                    if (res > 0) {
                                        studentList.remove(getLayoutPosition());//e em seguida, remove o item da lista studentList
                                        notifyDataSetChanged();
                                    }
                                    Toast.makeText(context.getApplicationContext(), "Succeeded Remove", Toast.LENGTH_SHORT).show();
                                    return true;
                                default:
                                    return false;
                            }
                        }
                    }
                    );
                    popup.show();
                }
            });
            }
        }
    }