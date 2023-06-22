package com.example.project.file;

import android.content.Context;
import android.content.ContextWrapper;

import com.example.project.model.StudentClassroom;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ClassroomStudentFileStore {
    private static final String FILE_NAME = "classroomstudent.txt";
    private static final String PATH = "managerstudent";
    private Context context;
    private File internalFile ;
    public ClassroomStudentFileStore(Context context){
        this.context = context;
        ContextWrapper contextWrapper = new ContextWrapper(context);
        File directory = contextWrapper.getDir(PATH,Context.MODE_PRIVATE);
        internalFile = new File(directory,FILE_NAME);
    }

    public boolean addClassroomStudent(StudentClassroom studentClassroom){
        try {
            FileOutputStream os = new FileOutputStream(internalFile,true);
            os.write((studentClassroom.getId()+"-"+studentClassroom.getStudentId()+"-"+studentClassroom.getClassroomId()+"-"+
                    studentClassroom.getSemeter()+"-"+studentClassroom.getCredits()+"\n").getBytes());
            os.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<StudentClassroom> getListClassroomStudent(){
        List<StudentClassroom> list = new ArrayList<>();
        try {
            FileInputStream os = new FileInputStream(internalFile);
            DataInputStream di = new DataInputStream(os);
            BufferedReader bf = new BufferedReader(new InputStreamReader(di));
            String dataLine = "";
            while ((dataLine = bf.readLine())!= null){
                String [] temp = dataLine.split("-");
                StudentClassroom studentClassroom = new StudentClassroom(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),
                        Integer.parseInt(temp[2]),temp[3],Integer.parseInt(temp[4]));
                list.add(studentClassroom);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
