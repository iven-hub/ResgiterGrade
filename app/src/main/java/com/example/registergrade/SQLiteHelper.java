package com.example.registergrade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Alunos.db";
    private static final String TABLE_NAME = "Alunos";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_GRADE1 = "grade1";
    private static final String COLUMN_GRADE2 = "grade2";
    private static final String COLUMN_MEDIA = "MEDIA";
//constr. context recursos do aplicativo, como o banco de dados. Se a base de dados será criada uma nova base de dados.
    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    //Chamado quando o banco de dados é criado pela primeira vez. É aqui que deve acontecer a criação das tabelas
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//e o preenchimento inicial das tabelas
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_GRADE1 + " REAL," + ""
                + COLUMN_GRADE2 + " REAL," + ""
                + COLUMN_MEDIA + " REAL" + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    //atualizar tabela se v+
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
//responsável por inserir dados de um objeto Student na base /escrever/armazena valores/put
    public void insertData (Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();//ContentValue utiliza os dados do objeto Student
        values.put(COLUMN_ID, student.getId());
        values.put(COLUMN_NAME, student.getNome());
        values.put(COLUMN_GRADE1, student.getNota1());
        values.put(COLUMN_GRADE2, student.getNota2());
        values.put(COLUMN_MEDIA, student.getMedia());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Cursor getAllData() {//get all data passa condition
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public ArrayList<Student> getApprovedStudents() {//lista dos alunos aprovados media>12
        ArrayList<Student> studentsAprov = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();//abrir conecao com db modo leitura
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE media >= 12.0", null);//consulta sql das media guardar result no cursor


        if (cursor.moveToFirst()) {//para verificar se há pelo menos um resultado na consulta... primeiro dado
            do {//extraimos resultado do cursor
                int id = cursor.getInt(0);//ref colun
                String name = cursor.getString(1);
                float grade1 = cursor.getFloat(2);
                float grade2 = cursor.getFloat(3);
                float media = cursor.getFloat(4);
                //Criamos um objeto Student com esses dados e o adicionamos à lista studentsaprov
                studentsAprov.add(new Student(id, name, grade1, grade2, media));

            } while (cursor.moveToNext());
        }
        cursor.close();
        return studentsAprov;//retorna a lista
    }

    public ArrayList<Student> getExamStudents() {
        ArrayList<Student> studentsExam = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE media  < 12.0", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                float grade1 = cursor.getFloat(2);
                float grade2 = cursor.getFloat(3);
                float media = cursor.getFloat(4);
                studentsExam.add(new Student(id, name, grade1, grade2, media));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return studentsExam;
    }
    //Este código é um método que deleta um registro de estudante da base
    public void deletedata(int id) {
        //Ele usa o ID do estudante como chave para localizar e excluir o registro correto.
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
//        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(student.getId())});
//        db.close();
    }
}

