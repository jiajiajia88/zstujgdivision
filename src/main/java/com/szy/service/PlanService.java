package com.szy.service;

import com.szy.po.Major;
import com.szy.po.Plan;
import com.szy.vo.PlanMajor;

import java.util.List;

/**
 * 分流计划相关service
 * Created by Administrator on 2016/10/20.
 */
public interface PlanService {

    void addPlan(Plan plan) throws Exception;

    void addMajor(Major major) throws Exception;

    List<Plan> getPlansAll() throws Exception;

    List<Major> getMajorsAll() throws Exception;

    List<PlanMajor> getPlanMajorsAll() throws Exception;

}
