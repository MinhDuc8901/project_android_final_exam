package com.example.project.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.project.model.Classroom;
import com.example.project.model.Student;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseClassroom extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ManagerStudents";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NAME = "Classrooms";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";

    public DatabaseClassroom(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "Create table " + TABLE_NAME + " (" + KEY_ID + " integer primary key autoincrement, " + KEY_NAME + " Text,"+
                KEY_DESCRIPTION+" Text)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "Drop table if exists " + TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public void addClassroom(Classroom classroom){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,classroom.getName());
        values.put(KEY_DESCRIPTION,classroom.getDescription());
        db.insert(TABLE_NAME,null,values);
        db.close();

    }

    public List<Classroom> getListClassroom(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Classroom> listClassroom = new ArrayList<>();
        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Classroom classroom = new Classroom(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
            listClassroom.add(classroom);
            cursor.moveToNext();
        }
        return listClassroom;
    }

    public Classroom getClassroom(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Classroom classroom = null;
        String query = "Select * from " + TABLE_NAME + " where id = "+ id;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        classroom = new Classroom(cursor.getInt(0), cursor.getString(1),cursor.getString(2));
        return classroom;
    }
}
