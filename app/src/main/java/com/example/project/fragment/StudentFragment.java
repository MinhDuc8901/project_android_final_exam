package com.example.project.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.project.AddStudentActivity;
import com.example.project.R;
import com.example.project.adapter.student.AdapterRecycle;
import com.example.project.adapter.student.AdapterSpinner;
import com.example.project.database.DatabaseStudent;
import com.example.project.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentFragment extends Fragment implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    private View view;
    private Button btnSearch, btnAdd;
    private EditText edtName;
    private RecyclerView recyclerView;
    private Spinner spinner;
    private AdapterRecycle adapter;
    private AdapterSpinner adapterSpinner;
    private List<Student> list;
    private static final int REQUEST_CODE_STUDENT = 10002;
    private DatabaseStudent db;
    public StudentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_student, container, false);
        initView();
        setupRecycle();
        setupSpinner();
        btnAdd.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        return view;
    }

    void initView(){
        btnAdd = view.findViewById(R.id.btnAddStudent);
        btnSearch = view.findViewById(R.id.btnSearchStudent);
        edtName = view.findViewById(R.id.edtSearchStudent);
        spinner = view.findViewById(R.id.spinnerStudent);
        recyclerView = view.findViewById(R.id.recycleStudent);

        db = new DatabaseStudent(this.getContext());
        list = db.getListStudent();
    }

    void setupRecycle(){
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
        adapter = new AdapterRecycle(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }

    void setupSpinner(){
        List<String> listStudentYear = new ArrayList<>();
        listStudentYear.add("Tất cả");
        listStudentYear.add("Năm 1");
        listStudentYear.add("Năm 2");
        listStudentYear.add("Năm 3");
        listStudentYear.add("Năm 4");

        adapterSpinner = new AdapterSpinner(listStudentYear,this.getContext());
        spinner.setAdapter(adapterSpinner);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        List<Student> listStudent = new ArrayList<>();
        if(i != 0 ){
            for (Student item : list){
                if(item.getStudentYear() == i){
                    listStudent.add(item);
                }
            }
            adapter.setList(listStudent);
        }else{
            adapter.setList(list);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSearchStudent:
                List<Student> listStudent = new ArrayList<>();
                int positionspinner = spinner.getSelectedItemPosition();
                for(Student item : list){
                    if(positionspinner == 0){
                        listStudent.add(item);
                    }else if(positionspinner == item.getStudentYear()){
                        listStudent.add(item);
                    }
                }
                String contentSearch = edtName.getText().toString().trim();
                if(contentSearch.isEmpty()){
                    adapter.setList(listStudent);
                }else{
                    List<Student> arry = new ArrayList<>();
                    for (Student item : listStudent){
                        if(item.getName().indexOf(contentSearch)>=0){
                            arry.add(item);
                        }
                    }
                    adapter.setList(arry);
                }
                break;
            case R.id.btnAddStudent:
                Intent intent = new Intent(this.getContext(), AddStudentActivity.class);
                startActivityForResult(intent,REQUEST_CODE_STUDENT);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_STUDENT){
            list = db.getListStudent();
            adapter.setList(list);
        }
    }
}