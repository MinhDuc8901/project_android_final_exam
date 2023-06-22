package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.database.DatabaseStudent;
import com.example.project.file.StudentFileStore;
import com.example.project.model.Student;

import java.sql.Struct;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener {
    private SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
    private Button btnAdd;
    private EditText edtName,edtBirth,editPlace,editYear;
//    private DatabaseStudent db;
    private StudentFileStore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        initView();
        btnAdd.setOnClickListener(this);
    }

    void initView(){
        btnAdd = findViewById(R.id.btnAddDataStudent);
        edtName = findViewById(R.id.edtnamestu);
        edtBirth = findViewById(R.id.edtbirthstu);
        editPlace = findViewById(R.id.edtplacestu);
        editYear = findViewById(R.id.edtstuyear);

//         db = new DatabaseStudent(this);
        db = new StudentFileStore(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddDataStudent:
                String name = edtName.getText().toString().trim();
                String birth = edtBirth.getText().toString().trim();
                String place = editPlace.getText().toString().trim();
                String year = editYear.getText().toString().trim();
                if(year.isEmpty()||name.isEmpty()||birth.isEmpty()||place.isEmpty()){
                    Toast.makeText(this,"Vui lòng tiền đủ thông tin",Toast.LENGTH_LONG).show();
                }else{
                    try {
                        Student student = new Student(name,sf.parse(birth),place,Integer.parseInt(year));
                        db.addStudent(student);
                        setResult(RESULT_OK);
                        finish();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}