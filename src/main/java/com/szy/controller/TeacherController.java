package com.szy.controller;

import com.alibaba.fastjson.JSON;
import com.szy.po.*;
import com.szy.service.PlanService;
import com.szy.service.StudentInfoService;
import com.szy.service.SystemService;
import com.szy.po.PlanVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教师模块的路由
 * Created by Administrator on 2016/9/30.
 */
@Controller
@RequestMapping({"/teacher"})
public class TeacherController {

    private Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private PlanService planService;

    @RequestMapping(value = "/plan_settings", method = RequestMethod.GET)
    public String plan_settings(Model model){

        List<Grade> gradeList = null;
        List<Species> speciesList = null;
        List<Plan> planList = null;
        List<Major> majorList = null;
        List<PlanVo> planVoList = null;

        try {
            gradeList = systemService.findGradesAll();
            speciesList = systemService.findSpeciesAll();
            planList = planService.getPlansAll();
            majorList = planService.getMajorsAll();
            planVoList = planService.getPlanMajorsAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询年级或大类出错！");
        }

        model.addAttribute("gradeList", gradeList);
        model.addAttribute("speciesList", speciesList);
        model.addAttribute("planList",planList);
        model.addAttribute("majorList",majorList);
        model.addAttribute("planVoList",planVoList);
        model.addAttribute("page", "plan_settings");

        return "plan_settings";
    }

    @RequestMapping(value = "/savePlan" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String savePlan(HttpServletRequest request){

        Map<String, Integer> map = new HashMap<>();

        int grade = Integer.parseInt(request.getParameter("grade"));
        String species = request.getParameter("species");
        int amount = Integer.parseInt(request.getParameter("amount"));
        String json_majors = request.getParameter("json_majors");
        List<HashMap> majorList =JSON.parseArray(json_majors, HashMap.class);

        try {
            planService.addPlanDetails(grade,species,amount,majorList);
            map.put("result",200);
        } catch (Exception e) {
            logger.error("添加计划出错！");
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/notice")
    public String notice(Model model){
        model.addAttribute("page", "notice");
        return "notice";
    }

    @RequestMapping("/stu_info_search")
    public String stu_info(Model model){

        List<Grade> gradeList = null;
        List<Species> speciesList = null;

        List<StudentInfoVo> stuInfoList;
        try {
            stuInfoList = studentInfoService.getStudentInfosAll();
            gradeList = systemService.findGradesAll();
            speciesList = systemService.findSpeciesAll();
            model.addAttribute("stuInfoList",stuInfoList);
            model.addAttribute("gradeList", gradeList);
            model.addAttribute("speciesList", speciesList);
        } catch (Exception e) {
            logger.info("查询studentInfo出错！");
            e.printStackTrace();
        }

        model.addAttribute("page", "stu_info_search");
        return "stu_info_search";
    }

    @RequestMapping("/stu_info_basic")
    public String stu_info_basic(Model model){
        List<StudentInfoVo> stuBasicInfoList;
        List<Grade> gradeList = null;
        List<Species> speciesList = null;
        try {
            stuBasicInfoList = studentInfoService.getStudentInfosAll();
            gradeList = systemService.findGradesAll();
            speciesList = systemService.findSpeciesAll();
            model.addAttribute("stuBasicInfoList",stuBasicInfoList);
            model.addAttribute("gradeList", gradeList);
            model.addAttribute("speciesList", speciesList);
        } catch (Exception e) {
            logger.info("查询studentInfo出错！");
            e.printStackTrace();
        }
        model.addAttribute("page", "stu_info_basic");
        return "stu_info_basic";
    }

    @RequestMapping("/stu_info_gpa")
    public String stu_info_gpa(Model model){
        List<StudentInfoVo> stugpaInfoList;
        List<Grade> gradeList = null;
        List<Species> speciesList = null;
        try {
            stugpaInfoList = studentInfoService.getStudentInfosAll();
            gradeList = systemService.findGradesAll();
            speciesList = systemService.findSpeciesAll();
            model.addAttribute("gradeList", gradeList);
            model.addAttribute("speciesList", speciesList);
            model.addAttribute("stugpaInfoList",stugpaInfoList);
        } catch (Exception e) {
            logger.info("查询studentInfo出错！");
            e.printStackTrace();
        }
        model.addAttribute("page", "stu_info_gpa");
        return "stu_info_gpa";
    }

    @RequestMapping("/stu_info_entrance")
    public String stu_info_entrance(Model model){
        List<StudentInfoVo> stuEntranceInfoList;
        List<Grade> gradeList = null;
        List<Species> speciesList = null;
        try {
            stuEntranceInfoList = studentInfoService.getStudentInfosAll();
            gradeList = systemService.findGradesAll();
            speciesList = systemService.findSpeciesAll();
            model.addAttribute("gradeList", gradeList);
            model.addAttribute("speciesList", speciesList);
            model.addAttribute("stuEntranceInfoList",stuEntranceInfoList);
        } catch (Exception e) {
            logger.info("查询studentInfo出错！");
            e.printStackTrace();
        }
        model.addAttribute("page", "stu_info_entrance");
        return "stu_info_entrance";
    }

    @RequestMapping(value = "/getOptionsOfMajor" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getOptionsOfMajor(HttpServletRequest request){

        Map<String, Object> map = new HashMap<>();
        int speciesId = Integer.parseInt(request.getParameter("speciesId"));
        List<Major> majorNameList;

        try {
            majorNameList = systemService.getMajorsBySpeciesId(speciesId);
            map.put("result",200);
            map.put("majorNameList",majorNameList);
        } catch (Exception e) {
            logger.error("添加计划出错！");
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/changePlanStatus" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String changePlanStatus(HttpServletRequest request){

        Map<String, Object> map = new HashMap<>();
        int status = Integer.parseInt(request.getParameter("status"));
        int planId = Integer.parseInt(request.getParameter("planId"));
        try {
            if(status==1){
                planService.updatePlanStatus(planId,0);
            } else {
                planService.updatePlanStatus(planId,1);
            }
            map.put("result",200);
        } catch (Exception e) {
            logger.error("修改分流计划状态失败！");
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/deletePlan" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deletePlan(HttpServletRequest request){

        Map<String, Object> map = new HashMap<>();
        int planId = Integer.parseInt(request.getParameter("planId"));
        try {
            planService.deletePlan(planId);
            map.put("result",200);
        } catch (Exception e) {
            logger.error("删除分流计划状态失败！");
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/task")
    public String time_line(Model model){
        model.addAttribute("page", "task");
        return "task";
    }

    @RequestMapping("/intent_adjust")
    public String intent_adjust(Model model){
        List<Grade> gradeList = null;
        List<Species> speciesList = null;
        try {
            gradeList = systemService.findGradesAll();
            speciesList = systemService.findSpeciesAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("gradeList", gradeList);
        model.addAttribute("speciesList", speciesList);
        model.addAttribute("page", "intent_adjust");
        return "intent_adjust";
    }

}
