package com.example.project.file;

import android.content.Context;
import android.content.ContextWrapper;

import com.example.project.model.Student;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StudentFileStore {
    private static final String FILE_NAME = "students.txt";
    private static final String PATH = "managerstudent";
    private File internalFile;
    private Context context;
    private static SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");

    public StudentFileStore(Context context){
        this.context = context;
        ContextWrapper contextWrapper = new ContextWrapper(context.getApplicationContext());
        File dicrionary = contextWrapper.getDir(PATH,Context.MODE_PRIVATE);
        internalFile = new File(dicrionary,FILE_NAME);
    }

    public boolean addStudent(Student student){
        try {
            FileOutputStream os = new FileOutputStream(internalFile,true);
            os.write((student.getId()+"-"+student.getName()+"-"+sf.format(student.getBirth())+"-"+student.getPlace()+"-"+student.getStudentYear()+"\n").getBytes());
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

    public List<Student> getListStudent(){
        List<Student> list = new ArrayList<>();
        try {
            FileInputStream os = new FileInputStream(internalFile);
            DataInputStream in = new DataInputStream(os);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strline = "";
            while ((strline = br.readLine())!=null){
                String[] temp = strline.split("-");
                Student student = new Student(Integer.parseInt(temp[0]),temp[1],sf.parse(temp[2]),temp[3],Integer.parseInt(temp[4]));
                list.add(student);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Student getStudent(int id){
        List<Student> list = new ArrayList<>();
        try {
            FileInputStream os = new FileInputStream(internalFile);
            DataInputStream in = new DataInputStream(os);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strline = "";
            while ((strline = br.readLine())!=null){
                String[] temp = strline.split("-");
                Student student = new Student(Integer.parseInt(temp[0]),temp[1],sf.parse(temp[2]),temp[3],Integer.parseInt(temp[4]));
                list.add(student);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(list.size()!=0){
            for (Student item : list){
                if(item.getId() == id){
                    return item;
                }
            }
        }
        return null;
    }
}
