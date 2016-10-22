package com.szy.vo;

import com.szy.po.Major;
import com.szy.po.Plan;

import java.util.List;

/**
 *
 * Created by Administrator on 2016/10/22.
 */
public class PlanMajor extends Plan{

    private List<Major> majorList;

    public List<Major> getMajorList() {
        return majorList;
    }

    public void setMajorList(List<Major> majorList) {
        this.majorList = majorList;
    }
}
