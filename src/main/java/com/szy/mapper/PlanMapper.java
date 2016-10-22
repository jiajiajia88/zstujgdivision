package com.szy.mapper;

import com.szy.po.Major;
import com.szy.po.Plan;
import com.szy.vo.PlanMajor;
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

    List<PlanMajor> findPlanMajorsAll() throws Exception;

}
