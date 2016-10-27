package com.szy.po;

public class StudentInfo {

    private Integer id;

    private String name;

    private String telephone;

    private Integer species;

    private String originalClass;

    private String presentClass;

    private Integer sex;

    private String dorm;

    private String note;

    private Double gpa;

    private Double realgpa;

    private String stufrom;

    private Integer division;

    private Integer entrancescore;

    private Integer admissionscore;

    private Double gradeone;

    private Double gradetwo;

    private Double totalgrade;

    private Integer rank;

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Integer getSpecies() {
        return species;
    }

    public void setSpecies(Integer species) {
        this.species = species;
    }

    public String getOriginalClass() {
        return originalClass;
    }

    public void setOriginalClass(String originalClass) {
        this.originalClass = originalClass == null ? null : originalClass.trim();
    }

    public String getPresentClass() {
        return presentClass;
    }

    public void setPresentClass(String presentClass) {
        this.presentClass = presentClass == null ? null : presentClass.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm == null ? null : dorm.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }
}