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
import android.widget.Button;

import com.example.project.AddClassroomActivity;
import com.example.project.R;
import com.example.project.adapter.classroom.AdapterRecycle;
import com.example.project.database.DatabaseClassroom;
import com.example.project.model.Classroom;

import java.util.List;

public class ClassroomFragment extends Fragment implements View.OnClickListener{
    private View view;
    private RecyclerView recyclerView;
    private Button btnAdd;
    private DatabaseClassroom db;
    private List<Classroom> list;
    private AdapterRecycle adapterRecycle;
    private static final int REQUEST_CODE_CLASSROOM = 100023;


    public ClassroomFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_classroom, container, false);
        initView();
        setupRecycleView();
        btnAdd.setOnClickListener(this);
        return view;
    }

    void initView(){
        recyclerView = view.findViewById(R.id.recycleClassroom);
        btnAdd = view.findViewById(R.id.btnAddClassroom);

        // khởi tạo database
        db = new DatabaseClassroom(this.getContext());
        list = db.getListClassroom();
    }

    void setupRecycleView(){
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
        adapterRecycle = new AdapterRecycle(list);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterRecycle);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddClassroom:
                Intent intent = new Intent(this.getContext(), AddClassroomActivity.class);
                startActivityForResult(intent,REQUEST_CODE_CLASSROOM);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_CLASSROOM){
            list = db.getListClassroom();
            adapterRecycle.setList(list);
        }
    }
}