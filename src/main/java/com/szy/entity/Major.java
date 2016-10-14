package com.szy.entity;

public class Major {
    private Integer majorId;

    private String majorName;

    private Integer classPlanNumber;

    private Integer stuPlanNumber;

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName == null ? null : majorName.trim();
    }

    public Integer getClassPlanNumber() {
        return classPlanNumber;
    }

    public void setClassPlanNumber(Integer classPlanNumber) {
        this.classPlanNumber = classPlanNumber;
    }

    public Integer getStuPlanNumber() {
        return stuPlanNumber;
    }

    public void setStuPlanNumber(Integer stuPlanNumber) {
        this.stuPlanNumber = stuPlanNumber;
    }
}