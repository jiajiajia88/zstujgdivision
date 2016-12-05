package com.szy.po.vo;

import com.szy.po.TeacherInfo;

/**
 * 将职位id转换成职位描述
 * Created by Administrator on 2016/10/23.
 */
public class TeacherInfoVo extends TeacherInfo {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
