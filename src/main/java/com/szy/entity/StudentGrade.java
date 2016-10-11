package com.szy.entity;

/**
 * 学生高考成绩类
 * Created by Administrator on 2016/10/6.
 */
public class StudentGrade {

    private int stuId;
    private String name;
    private double GPA;
    private double RealGPA;
    private String stuFrom;
    private String division;
    private double entranceScore;
    private double admissionScore;
    private double gradeOne;
    private double gradeTwo;
    private double totalGrade;

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public double getRealGPA() {
        return RealGPA;
    }

    public void setRealGPA(double realGPA) {
        RealGPA = realGPA;
    }

    public String getStuFrom() {
        return stuFrom;
    }

    public void setStuFrom(String stuFrom) {
        this.stuFrom = stuFrom;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public double getEntranceScore() {
        return entranceScore;
    }

    public void setEntranceScore(double entranceScore) {
        this.entranceScore = entranceScore;
    }

    public double getAdmissionScore() {
        return admissionScore;
    }

    public void setAdmissionScore(double admissionScore) {
        this.admissionScore = admissionScore;
    }

    public double getGradeOne() {
        return gradeOne;
    }

    public void setGradeOne(double gradeOne) {
        this.gradeOne = gradeOne;
    }

    public double getGradeTwo() {
        return gradeTwo;
    }

    public void setGradeTwo(double gradeTwo) {
        this.gradeTwo = gradeTwo;
    }

    public double getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(double totalGrade) {
        this.totalGrade = totalGrade;
    }
}
