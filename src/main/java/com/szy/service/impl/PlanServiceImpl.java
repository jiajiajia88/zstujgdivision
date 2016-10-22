package com.szy.service.impl;

import com.szy.mapper.PlanMapper;
import com.szy.po.Major;
import com.szy.po.Plan;
import com.szy.service.PlanService;
import com.szy.vo.PlanMajor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分流计划相关serviceImpl
 * Created by Administrator on 2016/10/20.
 */
@Service("planService")
public class PlanServiceImpl implements PlanService{

    @Autowired
    private PlanMapper planMapper;

    @Override
    public void addPlan(Plan plan) throws Exception {
        planMapper.insertPlan(plan);
    }

    @Override
    public void addMajor(Major major) throws Exception {
        planMapper.insertMajor(major);
    }

    @Override
    public List<Plan> getPlansAll() throws Exception {
        return planMapper.findPlansAll();
    }

    @Override
    public List<Major> getMajorsAll() throws Exception {
        return planMapper.findMajorsAll();
    }

    @Override
    public List<PlanMajor> getPlanMajorsAll() throws Exception {

        List<Plan> planList = planMapper.findPlansAll();
        List<PlanMajor> planMajorList = null;
        PlanMajor planMajor = new PlanMajor();
        for(Plan plan:planList){
            planMajor.setId(plan.getId());
            planMajor.setGrade(plan.getGrade());
            planMajor.setSpecies(plan.getSpecies());
            planMajor.setAmountStudent(plan.getAmountStudent());
            planMajor.setAmountMajor(plan.getAmountMajor());
            planMajor.setStatus(plan.getStatus());
            /*planMajor.setMajorList();*/
        }
        return null;
    }
}
