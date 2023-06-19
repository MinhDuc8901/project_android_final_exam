package com.example.project.model;

public class StudentClassroom {
    private int id;
    private int studentId;
    private int classroomId;
    private String semeter;
    private int credits;

    public StudentClassroom(int studentId, int classroomId, String semeter, int credits) {
        this.studentId = studentId;
        this.classroomId = classroomId;
        this.semeter = semeter;
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public String getSemeter() {
        return semeter;
    }

    public void setSemeter(String semeter) {
        this.semeter = semeter;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
