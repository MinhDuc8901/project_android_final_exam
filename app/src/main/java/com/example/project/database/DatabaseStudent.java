package com.example.project.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.project.model.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DatabaseStudent extends SQLiteOpenHelper {
    public static SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
    private static final String DATABASE_NAME = "ManagerStudents";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NAME = "Students";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_BIRTH = "birthday";
    private static final String KEY_PLACE = "place" ;
    private static final String KEY_YEAR_STUDENT = "year_student";


    public DatabaseStudent(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "Create table " + TABLE_NAME + " (" + KEY_ID + " integer primary key autoincrement, " + KEY_NAME + " Text,"+
                KEY_BIRTH+" Text,"+ KEY_PLACE + " Text, " + KEY_YEAR_STUDENT + " integer)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "Drop table if exists " + TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public void addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,student.getName());
        values.put(KEY_BIRTH,sf.format(student.getBirth()));
        values.put(KEY_PLACE,student.getPlace());
        values.put(KEY_YEAR_STUDENT,student.getStudentYear());
        db.insert(TABLE_NAME,null,values);
        db.close();

    }

    public List<Student> getListStudent(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Student> listStudent = new ArrayList<>();
        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            try {
                Student student = new Student(cursor.getInt(0),cursor.getString(1),sf.parse(cursor.getString(2)),cursor.getString(3),cursor.getInt(4));
                listStudent.add(student);
                cursor.moveToNext();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return listStudent;
    }

    public Student getStudent(int id) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();
        Student student = null;
        String query = "Select * from "+ TABLE_NAME + " where id = " + id;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        student = new Student(cursor.getInt(0),cursor.getString(1),sf.parse(cursor.getString(2)),cursor.getString(3),cursor.getInt(4));
        return student;
    }
}
