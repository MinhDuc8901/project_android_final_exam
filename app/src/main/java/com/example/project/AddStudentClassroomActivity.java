package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.project.adapter.studentclassroom.SpinnerClassroom;
import com.example.project.adapter.studentclassroom.SpinnerStudent;
import com.example.project.database.DatabaseClassroom;
import com.example.project.database.DatabaseClassroomStudent;
import com.example.project.database.DatabaseStudent;
import com.example.project.model.Classroom;
import com.example.project.model.Student;
import com.example.project.model.StudentClassroom;

import java.util.List;

public class AddStudentClassroomActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spinnerStu,spinnerClass;
    private EditText edtSemeter,edtCredits;
    private DatabaseClassroom dbClass;
    private DatabaseStudent dbStu;
    private DatabaseClassroomStudent dbClassStu;
    private List<Classroom> listClass;
    private List<Student> listStu;
    private Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_classroom);
        initView();
        setupSpinner();
        btnAdd.setOnClickListener(this);
    }

    void initView(){
        spinnerClass = findViewById(R.id.spinnerClass);
        spinnerStu = findViewById(R.id.spinnerStu);
        edtCredits = findViewById(R.id.edtCredits);
        edtSemeter = findViewById(R.id.edtSemeter);
        btnAdd = findViewById(R.id.btnAddData);

        dbClass = new DatabaseClassroom(this);
        dbStu = new DatabaseStudent(this);
        dbClassStu = new DatabaseClassroomStudent(this);
        listClass = dbClass.getListClassroom();
        listStu = dbStu.getListStudent();
    }

    void setupSpinner(){
        SpinnerStudent adapterStu = new SpinnerStudent(listStu,this);
        SpinnerClassroom adapterClass = new SpinnerClassroom(listClass,this);
        spinnerStu.setAdapter(adapterStu);
        spinnerClass.setAdapter(adapterClass);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddData:
                Classroom classroom = (Classroom) spinnerClass.getSelectedItem();
                Student student = (Student) spinnerStu.getSelectedItem();
                StudentClassroom studentClassroom = new StudentClassroom(student.getId(),classroom.getId(),edtSemeter.getText().toString().trim(),Integer.parseInt(edtCredits.getText().toString().trim()));
                dbClassStu.addClassroomStudent(studentClassroom);
                setResult(RESULT_OK);
                finish();
                break;
        }
    }
}