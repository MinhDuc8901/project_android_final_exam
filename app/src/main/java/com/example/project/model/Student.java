package com.example.project.model;

import java.util.Date;

public class Student {
    private int id;
    private String name;
    private String place;
    private Date birth;
    private int studentYear;

    public Student(int id, String name, Date birth, String place, int studentYear) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.birth = birth;
        this.studentYear = studentYear;
    }

    public Student(String name, Date birth, String place, int studentYear) {
        this.name = name;
        this.place = place;
        this.birth = birth;
        this.studentYear = studentYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public int getStudentYear() {
        return studentYear;
    }

    public void setStudentYear(int studentYear) {
        this.studentYear = studentYear;
    }
}
