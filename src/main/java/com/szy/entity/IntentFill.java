package com.szy.entity;

public class IntentFill {
    private Integer id;

    private String name;

    private String classes;

    private String teleTelephone;

    private Integer firstMajor;

    private Integer secondMajor;

    private Integer thirdMajor;

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
        this.name = name == null ? null : name.trim();
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes == null ? null : classes.trim();
    }

    public String getTeleTelephone() {
        return teleTelephone;
    }

    public void setTeleTelephone(String teleTelephone) {
        this.teleTelephone = teleTelephone == null ? null : teleTelephone.trim();
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
}