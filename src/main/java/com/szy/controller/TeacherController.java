package com.szy.controller;

import com.alibaba.fastjson.JSON;
import com.szy.po.*;
import com.szy.service.PlanService;
import com.szy.service.StudentInfoService;
import com.szy.service.SystemService;
import com.szy.vo.PlanMajor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/plan_settings")
    public String plan_settings(Model model){

        List<Grade> gradeList = null;
        List<Species> speciesList = null;
        List<Plan> planList = null;
        List<Major> majorList = null;
        List<PlanMajor> planMajorList = null;

        try {
            gradeList = systemService.findGradesAll();
            speciesList = systemService.findSpeciesAll();
            planList = planService.getPlansAll();
            majorList = planService.getMajorsAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询年级或大类出错！");
        }

        model.addAttribute("gradeList", gradeList);
        model.addAttribute("speciesList", speciesList);
        model.addAttribute("planList",planList);
        model.addAttribute("majorList",majorList);
        model.addAttribute("page", "plan_settings");
        return "plan_settings";
    }

    @RequestMapping(value = "/savePlan" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String savePlan(HttpServletRequest request){

        Map<String, Integer> map = new HashMap<String, Integer>();

        int grade = Integer.parseInt(request.getParameter("grade"));
        String species = request.getParameter("species");
        int amount = Integer.parseInt(request.getParameter("amount"));

        int species_id = 0;
        try {
            species_id = systemService.getSpeciesByName(species).getSpeciesId();
        } catch (Exception e) {
            logger.error("根据大类名查询大类信息出错！");
            e.printStackTrace();
        }

        String json_majors = request.getParameter("json_majors");
        List<HashMap> majorList =JSON.parseArray(json_majors, HashMap.class);

        Major major = new Major();

        int amount_major = 0;
        for (HashMap aMajorList : majorList) {
            amount_major++;
            int major_id = grade * 100000 + species_id * 100 + amount_major;
            String major_name = String.valueOf(aMajorList.get("major_name"));
            int class_plan_amount = Integer.parseInt(String.valueOf(aMajorList.get("class_plan_amount")));
            int stu_plan_amount = Integer.parseInt(String.valueOf(aMajorList.get("stu_plan_amount")));

            major.setMajorId(major_id);
            major.setMajorName(major_name);
            /*major.setClassPlanNumber(class_plan_amount);
            major.setStuPlanNumber(stu_plan_amount);*/
            try {
                planService.addMajor(major);
            } catch (Exception e) {
                logger.error("添加计划专业信息失败！");
                e.printStackTrace();
            }
        }

        Plan plan = new Plan();
        plan.setId(grade*1000+species_id);
        plan.setGrade(grade);
        plan.setSpecies(species_id);
        plan.setAmountStudent(amount);
        plan.setAmountMajor(amount_major);
        plan.setStatus(1);
        try {
            planService.addPlan(plan);
            map.put("result",200);
        } catch (Exception e) {
            logger.error("添加分流计划失败！");
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

        List<StudentInfo> stuInfoList;
        try {
            stuInfoList = studentInfoService.getStudentInfosAll();
            model.addAttribute("stuInfoList",stuInfoList);
        } catch (Exception e) {
            logger.info("查询studentInfo出错！");
            e.printStackTrace();
        }
        model.addAttribute("page", "stu_info_search");
        return "stu_info_search";
    }

    @RequestMapping("/stu_info_basic")
    public String stu_info_basic(Model model){
        List<StudentInfo> stuBasicInfoList;
        try {
            stuBasicInfoList = studentInfoService.getStudentInfosAll();
            model.addAttribute("stuBasicInfoList",stuBasicInfoList);
        } catch (Exception e) {
            logger.info("查询studentInfo出错！");
            e.printStackTrace();
        }
        model.addAttribute("page", "stu_info_basic");
        return "stu_info_basic";
    }

    @RequestMapping("/stu_info_gpa")
    public String stu_info_gpa(Model model){
        List<StudentInfo> stugpaInfoList;
        try {
            stugpaInfoList = studentInfoService.getStudentInfosAll();
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
        List<StudentInfo> stuEntranceInfoList;
        try {
            stuEntranceInfoList = studentInfoService.getStudentInfosAll();
            model.addAttribute("stuEntranceInfoList",stuEntranceInfoList);
        } catch (Exception e) {
            logger.info("查询studentInfo出错！");
            e.printStackTrace();
        }
        model.addAttribute("page", "stu_info_entrance");
        return "stu_info_entrance";
    }

    @RequestMapping("/task")
    public String time_line(Model model){
        model.addAttribute("page", "task");
        return "task";
    }

    @RequestMapping("/intent_adjust")
    public String intent_adjust(Model model){
        model.addAttribute("page", "intent_adjust");
        return "intent_adjust";
    }



}
