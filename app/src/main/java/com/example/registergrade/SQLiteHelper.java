package com.example.registergrade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Alunos.db";
    private static final String TABLE_NAME = "Alunos";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_GRADE1 = "grade1";
    private static final String COLUMN_GRADE2 = "grade2";
    private static final String COLUMN_MEDIA = "MEDIA";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_GRADE1 + " REAL," + ""
                + COLUMN_GRADE2 + " REAL," + ""
                + COLUMN_MEDIA + " REAL" + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertData (Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getNome());
        values.put(COLUMN_GRADE1, student.getNota1());
        values.put(COLUMN_GRADE2, student.getNota2());
        values.put(COLUMN_MEDIA, student.getMedia());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public ArrayList<Student> getApprovedStudents() {
        ArrayList<Student> students = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE media >= 12.0", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                float grade1 = cursor.getFloat(2);
                float grade2 = cursor.getFloat(3);
                float media = cursor.getFloat(4);
                students.add(new Student(id, name, grade1, grade2, media));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return students;
    }

    public ArrayList<Student> getExamStudents() {
        ArrayList<Student> students = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE media  < 12.0", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                float grade1 = cursor.getFloat(2);
                float grade2 = cursor.getFloat(3);
                float media = cursor.getFloat(4);
                students.add(new Student(id, name, grade1, grade2, media));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return students;
    }

    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(student.getId())});
        db.close();
    }
}

