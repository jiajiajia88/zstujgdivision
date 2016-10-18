package com.szy.controller;

import com.alibaba.fastjson.JSON;
import com.szy.entity.*;
import com.szy.service.StudentInfoService;
import com.szy.service.SystemService;
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
 * 系统模块的路由
 * Created by Administrator on 2016/9/23.
 */
@Controller
public class SystemController {

    private Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    UserService userService;

    @Autowired
    StudentInfoService studentInfoService;

    @Autowired
    SystemService systemService;

    @RequestMapping({ "/", "index" })
    public String index(Model model, HttpSession session) {
        int type = 0;
        if(session.getAttribute("userStudent") != null) {
            UserStudent userStudent = (UserStudent) session.getAttribute("userStudent");
            type = 2;
            session.setAttribute("number", userStudent.getNumber());
        } else if(session.getAttribute("userManager") != null){
            UserManager userManager = (UserManager) session.getAttribute("usermanager");
            type = 1;
            session.setAttribute("number", userManager.getNumber());
            session.setAttribute("name", userManager.getUsername());
        }
        if (session.getAttribute("userStudent") == null && session.getAttribute("userManager") == null) {
            return "redirect:/login";//返回的内容就是templetes下面文件的名称
        } else{
            model.addAttribute("page", "index");
            session.setAttribute("type", type);
            return "index";
        }
    }

    @RequestMapping(value = "/login")
    public String login(Model model){
        model.addAttribute("page","login");
        return "login";
    }

    @RequestMapping(value = "/loginSubmit" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String loginSubmit(Model model, HttpServletRequest request ,HttpSession session) throws Exception {

        Map<String, Integer> map = new HashMap<String, Integer>();
        String number = request.getParameter("number");
        String password = request.getParameter("password");
        if(userService.ifExistsUserWithPwd(number, password)){
            UserStudent userStudent = userService.findUserStudent(number);
            session.setAttribute("userStudent",userStudent);
            map.put("result", 200);
        } else if (userService.ifExistsManagerWithPwd(number, password)){
            UserManager userManager = userService.findUserManager(number);
            session.setAttribute("userManager",userManager);
            map.put("result", 200);
        } else{
            System.out.println("wrong");
            map.put("result", 0);
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/logout")
    public String logout(Model model,HttpSession session) throws Exception {
        session.removeAttribute("userstudent");
        return "redirect:/login";
    }

    @RequestMapping("/account")
    public String account(Model model,HttpSession session){
        int tag = 0;
        String number = String.valueOf(session.getAttribute("number"));
        String phoneNumber = null;
        try {
            phoneNumber = studentInfoService.getPhoneNumber(number);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("account页面查询电话号码出错");
        }
        if(phoneNumber.equals("")){
            tag = 1;
        } else {
            model.addAttribute("phoneNumber", phoneNumber);
            tag = 2;
        }
        model.addAttribute("page", "account");
        model.addAttribute("tag", tag);
        return "account";
    }

    @RequestMapping("/system_settings")
    public String system_settings(Model model) {
        List<Grade> gradeList = null;
        List<Species> speciesList = null;
        List<Positions> positionList = null;
        try {
            gradeList = systemService.findGradesAll();
            speciesList = systemService.findSpeciesAll();
            positionList = systemService.findPositionsAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("查询年级、大类、职位数据时发生错误！");
        }
        model.addAttribute("page", "system_settings");
        model.addAttribute("gradeList", gradeList);
        model.addAttribute("speciesList", speciesList);
        model.addAttribute("positionList", positionList);
        return "system_settings";
    }

    @RequestMapping(value = "/updatePwd" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updatePwd(Model model, HttpServletRequest request,HttpSession session) throws Exception {

        String number = String.valueOf(session.getAttribute("number"));
        System.out.println(number);

        Map<String, Integer> map = new HashMap<String, Integer>();
        String origin_pwd = request.getParameter("origin_pwd");
        String new_pwd = request.getParameter("new_pwd");
        String repeat_pwd = request.getParameter("repeat_pwd");

        int type = (int)session.getAttribute("type");
        System.out.println(type);

        if(userService.ifExistsUserWithPwd(number,origin_pwd) || userService.ifExistsManagerWithPwd(number,origin_pwd)){
            if(new_pwd.equals(repeat_pwd)){
                userService.updatePwd(number,new_pwd,type);
                map.put("result",200);
            } else {
                map.put("result", 1);
            }
        } else {
            map.put("result", 2);
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/updatePhone" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updatePhone(Model model, HttpServletRequest request,HttpSession session) throws Exception {

        String number = String.valueOf(session.getAttribute("number"));

        Map<String, Integer> map = new HashMap<String, Integer>();
        String phoneNumber = request.getParameter("phoneNumber");

        studentInfoService.updatePhoneNUmber(number,phoneNumber);
        map.put("result",200);
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/addGrade" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addGrade(Model model, HttpServletRequest request) throws Exception {

        Map<String, Integer> map = new HashMap<String, Integer>();
        String grade = request.getParameter("grade");
        systemService.addGrade(grade);
        map.put("result",200);
        return JSON.toJSONString(map);
    }

}
