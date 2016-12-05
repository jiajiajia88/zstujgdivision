package com.szy.mapper;

import com.szy.po.*;
import com.szy.po.vo.MajorVo;
import com.szy.po.vo.PlanVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 分流计划相关持久层接口
 * Created by Administrator on 2016/10/19.
 */
@Mapper
public interface PlanMapper {

    void insertPlan(Plan plan) throws Exception;

    List<Plan> findPlansAll() throws Exception;

    void updateStatus(int status,int id) throws Exception;

    void insertMajor(Major major) throws Exception;

    List<Major> findMajorsAll() throws Exception;

    List<PlanVo> findPlanMajorsAll() throws Exception;

    List<MajorVo> findMajorVoAll() throws Exception;

    Major findMajorByName(String majorName) throws Exception;

    void insertPlanMajor(PlanMajor planMajor) throws Exception;

    int ifExistsPlan(int planId) throws Exception;

    void updatePlan(Plan plan) throws Exception;

    void deletePlanMajor(int planId,int majorId) throws Exception;

    int ifExistsPlanMajor(int planId,int majorId) throws Exception;

    void updatePlanMajor(PlanMajor planMajor) throws Exception;

    void updatePlanStatus(int id,int status) throws Exception;

    void deletePlan(int id) throws Exception;

}
