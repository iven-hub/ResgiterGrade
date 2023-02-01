package com.example.registergrade;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtnota1;
    private EditText edtnota2,edtid;
    private Button btnAdd;
    private Button btnCancel;
    private SQLiteHelper dbHelper;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = findViewById(R.id.edit_name);
        edtnota1 = findViewById(R.id.edt_grade1);
        edtnota2 = findViewById(R.id.edt_grade2);
        btnAdd = findViewById(R.id.btn_add);
        edtid = findViewById(R.id.edit_id);
        btnCancel = findViewById(R.id.btn_cancel);
        //cria um objeto SQLiteHelper e inicializa db
        dbHelper = new  SQLiteHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //id do usuário e converte-o em um inteiro O método get é usado para obter o texto do EditText
                // como uma string e, em seguida, é convertido em um inteiro.
                int id = Integer.parseInt(edtid.getText().toString());
                String nome = edtName.getText().toString();
                float grade1 = Float.parseFloat(edtnota1.getText().toString());//converte para valor flutuante
                float grade2 = Float.parseFloat(edtnota2.getText().toString());
                float media = (grade1 + grade2 ) / 2;//media

                ////Chama o método para adicionar o aluno na base de dados
                Student student = new Student(id,nome, grade1, grade2, media);
                dbHelper.insertData(student);
                Toast.makeText(RegisterActivity.this, nome + " successfully added with average grade of " + media, Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}