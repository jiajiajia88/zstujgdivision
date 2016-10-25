package com.szy.po;

/**
 * 分流计划实体
 */
public class Plan {

    private Integer id;

    private Integer grade;

    private Integer species;

    private Integer amountStudent;

    private Integer amountMajor;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getSpecies() {
        return species;
    }

    public void setSpecies(Integer species) {
        this.species = species;
    }

    public Integer getAmountStudent() {
        return amountStudent;
    }

    public void setAmountStudent(Integer amountStudent) {
        this.amountStudent = amountStudent;
    }

    public Integer getAmountMajor() {
        return amountMajor;
    }

    public void setAmountMajor(Integer amountMajor) {
        this.amountMajor = amountMajor;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}