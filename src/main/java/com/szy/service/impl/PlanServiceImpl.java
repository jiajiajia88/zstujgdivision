package com.szy.service.impl;

import com.itextpdf.text.log.SysoCounter;
import com.szy.mapper.PlanMapper;
import com.szy.mapper.SystemMapper;
import com.szy.po.*;
import com.szy.po.vo.MajorVo;
import com.szy.po.vo.PlanVo;
import com.szy.service.PlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 分流计划相关serviceImpl
 * Created by Administrator on 2016/10/20.
 */
@Service("planService")
public class PlanServiceImpl implements PlanService{

    private Logger logger = LoggerFactory.getLogger(PlanServiceImpl.class);

    private static volatile List<Plan> planList = new ArrayList<>();
    private static volatile List<MajorVo> majorVoList = new ArrayList<>();

    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private SystemMapper systemMapper;

    @Override
    public void checkout() throws Exception{
        planList = planMapper.findPlansAll();
        majorVoList = planMapper.findMajorVoAll();
    }

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
    public List<PlanVo> getPlanMajorsAll() throws Exception {

        List<PlanVo> planVoList = new LinkedList<>();
        System.out.println("--------------"+planList.size());
        if(planList.size()!=0){
            for(Plan plan:planList){
                PlanVo planVo = new PlanVo();
                List<MajorVo> planMajorList = new LinkedList<>();
                int majorNumber = 0;
                for(MajorVo majorVo:majorVoList){
                    if(Objects.equals(majorVo.getPlanId(), plan.getId())){
                        planMajorList.add(majorVo);
                        majorNumber++;
                    }
                }
                planVo.setPlan(plan);
                planVo.setMajorNumber(majorNumber);
                planVo.setMajorList(planMajorList);
                planVo.setSpeciesName(systemMapper.findSpeciesById(plan.getSpecies()).getSpeciesName());
                planVoList.add(planVo);
            }
        }
        return planVoList;
    }

    @Override
    public Major getMajorByName(String majorName) throws Exception {
    return planMapper.findMajorByName(majorName);
    }

    @Override
    public void addPlanMajor(PlanMajor planMajor) throws Exception {
        planMapper.insertPlanMajor(planMajor);
    }

    @Override
    public void addPlanDetails(int grade, String species, int amount, List<HashMap> majorList) throws Exception {
        int species_id = systemMapper.findSpeciesByName(species).getSpeciesId();
        int planId = grade*1000+species_id;

        PlanMajor planMajor = new PlanMajor();
        int amount_major = 0;
        for (HashMap aMajorList : majorList) {
            amount_major++;
            String major_name = String.valueOf(aMajorList.get("major_name"));
            int majorId = systemMapper.findMajorByName(major_name).getMajorId();
            int class_plan_amount = Integer.parseInt(String.valueOf(aMajorList.get("class_plan_amount")));
            int stu_plan_amount = Integer.parseInt(String.valueOf(aMajorList.get("stu_plan_amount")));
            planMajor.setMajorId(majorId);
            planMajor.setPlanId(planId);
            planMajor.setStuNumber(stu_plan_amount);
            planMajor.setClassNumber(class_plan_amount);
            if(ifExistsPlanMajor(planId,majorId)){
                planMapper.updatePlanMajor(planMajor);
            } else {
                planMapper.insertPlanMajor(planMajor);
            }
        }

        Plan plan = new Plan();
        plan.setId(planId);
        plan.setGrade(grade);
        plan.setSpecies(species_id);
        plan.setAmountStudent(amount);
        plan.setAmountMajor(amount_major);
        plan.setStatus(1);
        if(ifExistsPlan(planId)){
            planMapper.updatePlan(plan);
        } else {
            planMapper.insertPlan(plan);
        }
    }

    @Override
    public boolean ifExistsPlan(int planId) throws Exception {
        return (1==planMapper.ifExistsPlan(planId));
    }

    @Override
    public boolean ifExistsPlanMajor(int planId,int majorId) throws Exception {
        return (1==planMapper.ifExistsPlanMajor(planId,majorId));
    }

    @Override
    public void updatePlanStatus(int id, int status) throws Exception {
        planMapper.updatePlanStatus(id,status);
    }

    @Override
    public void deletePlan(int id) throws Exception {
        planMapper.deletePlan(id);
    }

}
