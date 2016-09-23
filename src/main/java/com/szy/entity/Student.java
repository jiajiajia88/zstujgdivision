package com.szy.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 用户类
 * Created by Administrator on 2016/9/15.
 */
@Component("Student")
public class Student implements Serializable{

    private static final long serialVersionUID = 2120869894112984147L;

    private Integer id;
    private String name;
    private Long number;

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

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long age) {
        this.number = age;
    }
}
