package com.example.studentreg.database;

public class StudentModel {

    private int id;
    private String name;
    private String course;
    private String priority;

    public StudentModel() {
    }

    public StudentModel(int id, String name, String course, String priority) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.priority = priority;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
