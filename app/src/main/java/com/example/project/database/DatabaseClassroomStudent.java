package com.example.project.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.project.model.Student;
import com.example.project.model.StudentClassroom;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DatabaseClassroomStudent extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ManagerStudents";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NAME = "classroom_student";

    private static final String KEY_ID = "id";
    private static final String KEY_STUDENT_ID = "student_id";
    private static final String KEY_CLASSROOM_ID = "classroom_id";
    private static final String KEY_SEMETER = "semeter" ;
    private static final String KEY_CREDITS = "credits";


    public DatabaseClassroomStudent(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "Create table "  + TABLE_NAME + " ( " + KEY_ID + " integer primary key autoincrement,"+ KEY_STUDENT_ID + " integer,"+
                KEY_CLASSROOM_ID + " integer, " + KEY_SEMETER + " text, " + KEY_CREDITS + " integer)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "Drop table if exists " + TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public void addClassroomStudent(StudentClassroom studentClassroom){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_ID,studentClassroom.getStudentId());
        values.put(KEY_CLASSROOM_ID,studentClassroom.getClassroomId());
        values.put(KEY_SEMETER,studentClassroom.getSemeter());
        values.put(KEY_CREDITS,studentClassroom.getCredits());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public List<StudentClassroom> getListStudentClassroom(){
        List<StudentClassroom> list = new ArrayList<>();
        String query = "Select * from "+ TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            StudentClassroom studentClassroom = new StudentClassroom(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getInt(4));
            list.add(studentClassroom);
            cursor.moveToNext();
        }
        Collections.sort(list, new Comparator<StudentClassroom>() {
            @Override
            public int compare(StudentClassroom studentClassroom, StudentClassroom t1) {
                if(studentClassroom.getStudentId()>t1.getStudentId()){
                    return -1;
                }else{
                    return 1;
                }
            }
        });
        return list;
    }
}
