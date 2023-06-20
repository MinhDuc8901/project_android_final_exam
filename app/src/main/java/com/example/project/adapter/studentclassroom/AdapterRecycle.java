package com.example.project.adapter.studentclassroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.database.DatabaseClassroom;
import com.example.project.database.DatabaseStudent;
import com.example.project.model.Classroom;
import com.example.project.model.Student;
import com.example.project.model.StudentClassroom;

import java.text.ParseException;
import java.util.List;

public class AdapterRecycle extends RecyclerView.Adapter<AdapterRecycle.ViewHolder> {
    private List<StudentClassroom> list;
    private Context context;
    private DatabaseStudent dbStudent;
    private DatabaseClassroom dbClassroom;
    public AdapterRecycle(List<StudentClassroom> list,Context context){
        this.list = list;
        this.context = context;
        initDB(context);
    }
    public void initDB(Context context){
        dbClassroom = new DatabaseClassroom(context);
        dbStudent = new DatabaseStudent(context);
    }

    public void setList(List<StudentClassroom> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentClassroom studentClassroom = list.get(position);
        if(studentClassroom == null){
            return;
        }
        Student student = null;
        Classroom classroom = null;
        try {
            student = dbStudent.getStudent(studentClassroom.getStudentId());
            classroom = dbClassroom.getClassroom(studentClassroom.getClassroomId());
            holder.tvName.setText(student.getId()+" - "+student.getName());
            holder.tvNote.setText(classroom.getId()+" - "+classroom.getName());
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName,tvNote;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvNote = itemView.findViewById(R.id.tvNote);
        }
    }
}
