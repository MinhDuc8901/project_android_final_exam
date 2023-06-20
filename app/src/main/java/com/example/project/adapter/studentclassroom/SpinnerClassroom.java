package com.example.project.adapter.studentclassroom;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.model.Classroom;

import java.util.List;

public class SpinnerClassroom extends BaseAdapter {
    private List<Classroom> list;
    private Context context;
    public SpinnerClassroom(List<Classroom> list,Context context){
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount()
    {
        if(list == null){
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_spinner,null);
        TextView tvName = view.findViewById(R.id.tvContent);
        Classroom classroom = list.get(i);
        Log.i("test1",classroom.getName());
        tvName.setText(classroom.getId()+" - "+classroom.getName());
        return view;
    }
}
