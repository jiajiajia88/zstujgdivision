package com.szy.entity;

public class StudentInfo {

    private Integer id;

    private String name;

    private String telephone;

    private int originalClass;

    private int presentClass;

    private Double gpa;

    private Double realgpa;

    private String stufrom;

    private Integer division;

    private Integer entrancescore;

    private Integer admissionscore;

    private Double gradeone;

    private Double gradetwo;

    private Double totalgrade;

    private String number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getOriginalClass() {
        return originalClass;
    }

    public void setOriginalClass(int originalClass) {
        this.originalClass = originalClass;
    }

    public int getPresentClass() {
        return presentClass;
    }

    public void setPresentClass(int presentClass) {
        this.presentClass = presentClass;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public Double getRealgpa() {
        return realgpa;
    }

    public void setRealgpa(Double realgpa) {
        this.realgpa = realgpa;
    }

    public String getStufrom() {
        return stufrom;
    }

    public void setStufrom(String stufrom) {
        this.stufrom = stufrom == null ? null : stufrom.trim();
    }

    public Integer getDivision() {
        return division;
    }

    public void setDivision(Integer division) {
        this.division = division;
    }

    public Integer getEntrancescore() {
        return entrancescore;
    }

    public void setEntrancescore(Integer entrancescore) {
        this.entrancescore = entrancescore;
    }

    public Integer getAdmissionscore() {
        return admissionscore;
    }

    public void setAdmissionscore(Integer admissionscore) {
        this.admissionscore = admissionscore;
    }

    public Double getGradeone() {
        return gradeone;
    }

    public void setGradeone(Double gradeone) {
        this.gradeone = gradeone;
    }

    public Double getGradetwo() {
        return gradetwo;
    }

    public void setGradetwo(Double gradetwo) {
        this.gradetwo = gradetwo;
    }

    public Double getTotalgrade() {
        return totalgrade;
    }

    public void setTotalgrade(Double totalgrade) {
        this.totalgrade = totalgrade;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }
}