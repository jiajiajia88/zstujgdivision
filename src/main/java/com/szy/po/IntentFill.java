package com.szy.po;

/**
 * 志愿填报实体
 */
public class IntentFill {

    private Integer id;

    private String name;

    private String classes;

    private String telephone;

    private Integer firstMajor;

    private Integer secondMajor;

    private Integer thirdMajor;

    private String number;

    private int status;

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
        this.name = name == null ? null : name.trim();
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes == null ? null : classes.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Integer getFirstMajor() {
        return firstMajor;
    }

    public void setFirstMajor(Integer firstMajor) {
        this.firstMajor = firstMajor;
    }

    public Integer getSecondMajor() {
        return secondMajor;
    }

    public void setSecondMajor(Integer secondMajor) {
        this.secondMajor = secondMajor;
    }

    public Integer getThirdMajor() {
        return thirdMajor;
    }

    public void setThirdMajor(Integer thirdMajor) {
        this.thirdMajor = thirdMajor;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}