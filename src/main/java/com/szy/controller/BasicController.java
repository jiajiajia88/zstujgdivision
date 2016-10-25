package com.szy.controller;

import com.alibaba.fastjson.JSON;
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
import java.util.Map;

/**
 *
 * Created by Administrator on 2016/10/22.
 */
@Controller
public class BasicController {

    private Logger logger = LoggerFactory.getLogger(BasicController.class);

    @Autowired
    StudentInfoService studentInfoService;

    @Autowired
    UserService userService;

    @Autowired
    SystemService systemService;

    @RequestMapping({ "/", "index" })
    public String index(Model model, HttpSession session) {
        if(null == session.getAttribute("number")){
            return "redirect:/login";
        } else {
            try {
                System.out.println(userService.getRoleByNumber(String.valueOf(session.getAttribute("number"))));
                session.setAttribute("role",userService.getRoleByNumber(String.valueOf(session.getAttribute("number"))));
            } catch (Exception e) {
                logger.error("根据number查询角色出错");
                e.printStackTrace();
            }
            model.addAttribute("page", "index");
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
    public String loginSubmit(HttpServletRequest request , HttpSession session) throws Exception {
        Map<String, Integer> map = new HashMap<>();
        String number = request.getParameter("number");
        String name = "管理员";
        String role = userService.getRoleByNumber(number);
        if(role.equals("student")){
            name = studentInfoService.getStudentInfoByNumber(number).getName();
        } else if(role.equals("teacher")){
            name = systemService.getTeacherInfoByNumber(number).getName();
        }
        session.setAttribute("number",number);
        session.setAttribute("username",name);
        map.put("result", 200);
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) throws Exception {
        session.removeAttribute("userstudent");
        return "redirect:/login";
    }

    @RequestMapping(value = "/noAccess")
    public String noAccess() throws Exception {
        return "noAccess";
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
            logger.error("account页面查询电话号码出错");
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

    @RequestMapping(value = "/updatePwd" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updatePwd(HttpServletRequest request,HttpSession session) throws Exception {

        String number = String.valueOf(session.getAttribute("number"));

        Map<String, Integer> map = new HashMap<>();
        String origin_pwd = request.getParameter("origin_pwd");
        String new_pwd = request.getParameter("new_pwd");
        String repeat_pwd = request.getParameter("repeat_pwd");

        if(userService.checkLogin(number,origin_pwd)){
            if(new_pwd.equals(repeat_pwd)){
                userService.updatePwd(number,new_pwd);
                map.put("result",200);
            } else {
                map.put("result", 1);
            }
        } else {
            map.put("result", 2);
        }
        return JSON.toJSONString(map);
    }
}
