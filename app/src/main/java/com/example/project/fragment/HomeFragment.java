package com.example.project.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.project.AddStudentClassroomActivity;
import com.example.project.R;
import com.example.project.adapter.student.AdapterSpinner;
import com.example.project.adapter.studentclassroom.AdapterRecycle;
import com.example.project.adapter.studentclassroom.SpinnerClassroom;
import com.example.project.database.DatabaseClassroom;
import com.example.project.database.DatabaseClassroomStudent;
import com.example.project.model.Classroom;
import com.example.project.model.StudentClassroom;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private View view;
    private Spinner spinner;
    private Button btnAdd;
    private RecyclerView recyclerView;
    private List<StudentClassroom> list;
    private DatabaseClassroomStudent dbClassroomStudent;
    private DatabaseClassroom dbClassroom;
    private List<Classroom> listClass;
    private AdapterRecycle adapterRecycle;
    private SpinnerClassroom spinnerClassroom;
    private static final int REQUEST_CODE_STUDENT_CLASSROOM = 110110;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_home, container, false);
         initView();
        setupRecycle();
        setupSpinner();
        btnAdd.setOnClickListener(this);
         return view;
    }

    void setupRecycle(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        adapterRecycle = new AdapterRecycle(list,this.getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterRecycle);
    }

    void setupSpinner(){
        List<Classroom> listTemp = new ArrayList<>();
        listTemp.add(new Classroom(0,"Tất cả",""));
        for(Classroom item : listClass){
            listTemp.add(item);
        }
        Log.i("test",listTemp.get(1).getName());
        spinnerClassroom = new SpinnerClassroom(listTemp,this.getContext());
        spinner.setAdapter(spinnerClassroom);
        spinner.setOnItemSelectedListener(this);
    }

    void initView(){
        spinner = view.findViewById(R.id.spinner);
        recyclerView = view.findViewById(R.id.recycle);
        btnAdd = view.findViewById(R.id.btnAddStudentClassroom);

        dbClassroomStudent = new DatabaseClassroomStudent(this.getContext());
        list = dbClassroomStudent.getListStudentClassroom();
        dbClassroom = new DatabaseClassroom(this.getContext());
        listClass = dbClassroom.getListClassroom();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddStudentClassroom:
                Intent intent = new Intent(this.getContext(), AddStudentClassroomActivity.class);
                startActivityForResult(intent,REQUEST_CODE_STUDENT_CLASSROOM);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_STUDENT_CLASSROOM){
            list = dbClassroomStudent.getListStudentClassroom();
            adapterRecycle.setList(list);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Classroom classroom = (Classroom) spinnerClassroom.getItem(i);
        List<StudentClassroom> listTemp = new ArrayList<>();
        for(StudentClassroom item : list){
            if(item.getClassroomId() == classroom.getId()){
                listTemp.add(item);
            }
        }
        if(classroom.getId() == 0){
            adapterRecycle.setList(list);
        }else{
            adapterRecycle.setList(listTemp);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}