package com.szy.controller;

import com.alibaba.fastjson.JSON;
import com.szy.po.IntentFill;
import com.szy.po.Major;
import com.szy.po.StudentInfo;
import com.szy.po.User;
import com.szy.service.StudentInfoService;
import com.szy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生模块的路由
 * Created by Administrator on 2016/9/30.
 */
@Controller
@RequestMapping({"/student"})
public class StudentController {

    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private StudentInfoService studentInfoService;

    @RequestMapping("/major_info")
    public String major_info(Model model){
        model.addAttribute("page", "major_info");
        return "major_info";
    }

    @RequestMapping("/stu_info")
    public String stu_info(Model model){
        model.addAttribute("page", "stu_info");
        return "stu_info";
    }

    @RequestMapping("/intent_fill")
    public String intent_fill(Model model, HttpSession session){

        try {
            //测试方便
            /*User student = new User();
            student.setId(1);
            student.setNumber("2013333502028");
            student.setPassword("123456");
            session.setAttribute("userStudent",student);*/
            //测试方便
            String number = String.valueOf(session.getAttribute("number"));
            StudentInfo studentInfo = studentInfoService.getStudentInfoByNumber(number);
            User student = userService.getUserByNumber(number);
            int id = student.getId();
            if (studentInfoService.ifExistIntentId(id)){
                IntentFill intentFill = studentInfoService.findAllIntentById(id);
                String first = studentInfoService.findMajorNameById(intentFill.getFirstMajor());
                String second = studentInfoService.findMajorNameById(intentFill.getSecondMajor());
                String third = studentInfoService.findMajorNameById(intentFill.getThirdMajor());
                if(first == null){
                    first = "未知";
                }
                if(second == null){
                    second = "未知";
                }
                if(third == null){
                    third = "未知";
                }
                model.addAttribute("firstmajor",first);
                model.addAttribute("secondmajor",second);
                model.addAttribute("thirdmajor",third);
                model.addAttribute("message","已填报志愿如下：");
            } else {
                model.addAttribute("message","尚未填报分流志愿！");
                model.addAttribute("firstmajor","未知");
                model.addAttribute("secondmajor","未知");
                model.addAttribute("thirdmajor","未知");
            }
            model.addAttribute("studentinfo",studentInfo);
            //System.out.println(studentInfo.getId()+"/"+studentInfo.getName()+"/"+studentInfo.getGpa()+"/"+studentInfo.getEntrancescore()+"/");
        } catch (Exception e){
            e.printStackTrace();
            logger.error("根据id查询学生信息出错1！");
        }

        try {
            List<Major> majorList = null;
            majorList=studentInfoService.findMajorAll();
            model.addAttribute("major",majorList);
        } catch (Exception e){
            e.printStackTrace();
            logger.error("查询专业信息出错！");
        }

        model.addAttribute("page", "intent_fill");
        return "intent_fill";
    }

    @RequestMapping("/grades_query")
    public String grades_query(Model model){
        model.addAttribute("page", "grades_query");
        return "grades_query";
    }

    @RequestMapping("/result")
    public String result(Model model){
        model.addAttribute("page", "result");
        return "result";
    }

    /**
     * 保存学生志愿
     * */
    @RequestMapping(value = "/saveIntent" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String saveIntent(HttpServletRequest request, HttpSession session) throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            String number = String.valueOf(session.getAttribute("number"));
            User student = userService.getUserByNumber(number);
            int id = student.getId();
            System.out.println("----"+id);

            String first = request.getParameter("first");
            String second = request.getParameter("second");
            String third = request.getParameter("third");
            System.out.println(first+second+third);

            int firstId = studentInfoService.findMajorIdByName(first);
            int secondId = studentInfoService.findMajorIdByName(second);
            int thirdId = studentInfoService.findMajorIdByName(third);

            if(studentInfoService.ifExistIntentId(id)){

                studentInfoService.updateIntent(id,firstId,secondId,thirdId);

                System.out.println("更改志愿");
            } else {
                StudentInfo studentInfo = studentInfoService.getStudentInfoById(id);
                String studentName = studentInfo.getName();
                String studentNumber = studentInfo.getNumber();

                //               System.out.println(studentName+"---0"+studentNumber);
                studentInfoService.insertIntent(id,studentName,"fuck",studentNumber,"15151",firstId,secondId,thirdId);
                System.out.println("添加志愿");
            }
            map.put("result", 200);
        } catch (Exception e){
            map.put("result",0);
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }
}
