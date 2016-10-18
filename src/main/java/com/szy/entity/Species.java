package com.szy.entity;

/**
 * 大类实体类
 */
public class Species {

    private Integer speciesId;

    private String speciesName;

    private Integer stuAmount;

    public Integer getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(Integer speciesId) {
        this.speciesId = speciesId;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName == null ? null : speciesName.trim();
    }

    public Integer getStuAmount() {
        return stuAmount;
    }

    public void setStuAmount(Integer stuAmount) {
        this.stuAmount = stuAmount;
    }
}