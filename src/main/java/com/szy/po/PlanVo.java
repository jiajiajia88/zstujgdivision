package com.szy.po;

import java.util.List;

/**
 * 计划包装类
 * Created by Administrator on 2016/10/22.
 */
public class PlanVo {

    private Plan plan;

    private List<MajorVo> majorList;

    private String speciesName;

    private Integer majorNumber;

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public List<MajorVo> getMajorList() {
        return majorList;
    }

    public void setMajorList(List<MajorVo> majorList) {
        this.majorList = majorList;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public Integer getMajorNumber() {
        return majorNumber;
    }

    public void setMajorNumber(Integer majorNumber) {
        this.majorNumber = majorNumber;
    }
}
