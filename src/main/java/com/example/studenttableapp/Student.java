package com.example.studenttableapp;

public class Student {
    private String name;
    private String course;
    private double average;

    public Student(String name, String course, double average) {
        this.name = name;
        this.course = course;
        this.average = average;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public double getAverage() { return average; }
    public void setAverage(double average) { this.average = average; }
}
