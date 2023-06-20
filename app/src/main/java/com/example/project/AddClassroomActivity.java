package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.project.database.DatabaseClassroom;
import com.example.project.model.Classroom;

public class AddClassroomActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editName,editNote;
    private Button btnAdd;

    private DatabaseClassroom db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_classroom);
        initView();
        btnAdd.setOnClickListener(this);
    }

    void initView(){
        editName = findViewById(R.id.edtNameClassroom);
        editNote = findViewById(R.id.edtNoteClassroom);
        btnAdd = findViewById(R.id.btnAddDataClassroom);

        db = new DatabaseClassroom(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddDataClassroom:
                String name = editName.getText().toString().trim();
                String note = editNote.getText().toString().trim();
                if(!name.isEmpty() || !note.isEmpty()){
                    Classroom classroom = new Classroom(name,note);
                    db.addClassroom(classroom);
                    setResult(RESULT_OK);
                    finish();
                }else{
                    setResult(RESULT_CANCELED);
                    finish();
                }
        }
    }
}