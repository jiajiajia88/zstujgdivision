package com.szy.service;

import com.szy.po.Major;
import com.szy.po.Plan;
import com.szy.po.PlanMajor;
import com.szy.po.PlanVo;

import java.util.HashMap;
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

    List<PlanVo> getPlanMajorsAll() throws Exception;

    Major getMajorByName(String majorName) throws Exception;

    void addPlanMajor(PlanMajor planMajor) throws Exception;

    void addPlanDetails(int grade,String species,int amount,List<HashMap> majorList) throws Exception;

    boolean ifExistsPlan(int planId) throws Exception;

    boolean ifExistsPlanMajor(int planId,int majorId) throws Exception;

    void updatePlanStatus(int id,int status) throws Exception;

    void deletePlan(int id) throws Exception;

}
