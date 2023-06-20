package com.example.project.adapter.studentclassroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.model.Student;

import java.util.List;

public class SpinnerStudent extends BaseAdapter {
    private List<Student> listStudent;
    private Context context;
    public SpinnerStudent(List<Student> listStudent,Context context){
        this.listStudent = listStudent;
        this.context = context;
    }
    @Override
    public int getCount() {
        return listStudent.size();
    }

    @Override
    public Object getItem(int i) {
        return listStudent.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_spinner,null);
        TextView tvName = view.findViewById(R.id.tvContent);
        Student student = listStudent.get(i);
        tvName.setText(student.getId()+" - "+student.getName()+" - "+student.getStudentYear());
        return view;
    }
}
