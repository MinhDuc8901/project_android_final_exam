package com.example.project.file;

import android.content.Context;
import android.content.ContextWrapper;

import com.example.project.model.Classroom;

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

public class ClassroomFileStore {
    private static final String FILE_NAME = "classrooms";
    private static final String PATH = "managerstudent";
    private File internalfile;
    private Context context;
    public ClassroomFileStore(Context context){
        this.context = context;
        ContextWrapper contextWrapper = new ContextWrapper(context.getApplicationContext());
        File dictionary = contextWrapper.getDir(PATH,Context.MODE_PRIVATE);
        internalfile = new File(dictionary,FILE_NAME);
    }

    public boolean addClassroom(Classroom classroom){
        try {
            FileOutputStream os = new FileOutputStream(internalfile,true);
            os.write((classroom.getId()+"-"+classroom.getName()+"-"+classroom.getDescription()+"\n").getBytes());
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

    public List<Classroom> getListClassroom(){
        List<Classroom> list = new ArrayList<>();
        try {
            FileInputStream os = new FileInputStream(internalfile);
            DataInputStream di = new DataInputStream(os);
            BufferedReader br = new BufferedReader(new InputStreamReader(di));
            String temp = "";
            while ((temp = br.readLine())!=null){
                String [] tempString = temp.split("-");
                Classroom classroom = new Classroom(Integer.parseInt(tempString[0]),tempString[1],tempString[2]);
                list.add(classroom);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(list.size() !=0){
            return list;
        }
        return null;
    }

    public Classroom getClassroom(int id){
        List<Classroom> list = new ArrayList<>();
        try {
            FileInputStream os = new FileInputStream(internalfile);
            DataInputStream di = new DataInputStream(os);
            BufferedReader br = new BufferedReader(new InputStreamReader(di));
            String temp = "";
            while ((temp = br.readLine())!=null){
                String [] tempString = temp.split("-");
                Classroom classroom = new Classroom(Integer.parseInt(tempString[0]),tempString[1],tempString[2]);
                list.add(classroom);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(list.size() !=0){
            for (Classroom item : list){
                if(item.getId() == id){
                    return item;
                }
            }
        }
        return null;
    }

}
