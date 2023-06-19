package com.example.project.adapter.student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.model.Student;

import java.util.List;

public class AdapterSpinner extends BaseAdapter {
    private List<String> list;
    private Context context;

    public AdapterSpinner(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
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
        TextView tvContent = view.findViewById(R.id.tvContent);
        tvContent.setText(list.get(i));
        return view;
    }
}
