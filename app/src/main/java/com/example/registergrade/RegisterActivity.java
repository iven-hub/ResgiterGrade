package com.example.registergrade;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtName,editnota1,editnota2,editid;
    private Button btn_Add,btn_Cancel;
    private SQLiteHelper dbHelper;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = findViewById(R.id.edit_name);
        editnota1 = findViewById(R.id.edt_grade1);
        editnota2 = findViewById(R.id.edt_grade2);
        btn_Add = findViewById(R.id.btn_add);
        editid = findViewById(R.id.edit_id);
        btn_Cancel = findViewById(R.id.btn_cancel);
        //cria um objeto SQLiteHelper e inicializa db
        dbHelper = new  SQLiteHelper(this);

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //id do usuário e converte-o em um inteiro O método get é usado para obter o texto do EditText
                // como uma string e, em seguida, é convertido em um inteiro.
                int id = Integer.parseInt(editid.getText().toString());
                String nome = edtName.getText().toString();
                float grade1 = Float.parseFloat(editnota1.getText().toString());//converte para valor flutuante
                float grade2 = Float.parseFloat(editnota2.getText().toString());
                float media = (grade1 + grade2 ) / 2;//media

                ////Chama o método para adicionar o aluno na base de dados
                Student student = new Student(id,nome, grade1, grade2, media);
                dbHelper.insertData(student);
                Toast.makeText(RegisterActivity.this, nome + " successfully added with average grade of " + media, Toast.LENGTH_SHORT).show();
            }
        });

        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder cance = new AlertDialog.Builder(RegisterActivity.this);
                cance.setTitle("Deseja mesmo cancelar");
                cance.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Foi cancelado com sucesso", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    }
                });
                cance.setPositiveButton("Continuar", null);
                cance.setCancelable(false);//click fora
                cance.create().show(); //mostrar o alert
            }
        });
    }
}