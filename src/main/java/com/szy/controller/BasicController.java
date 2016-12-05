package com.szy.controller;

import com.alibaba.fastjson.JSON;
import com.szy.cache.Session;
import com.szy.po.User;
import com.szy.service.StudentInfoService;
import com.szy.service.SystemService;
import com.szy.service.UserService;
import com.szy.util.UserLimitUtil;
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
    public String index(Model model,HttpSession session) {
        int limit = 0;
        if(session.getAttribute("cache")!=null)
            limit = ((Session)session.getAttribute("cache")).getLimit();
        else
            return "login";
        model.addAttribute("limit", limit);
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/loginSubmit" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String loginSubmit(HttpServletRequest request , HttpSession session) throws Exception {
        Map<String, Integer> map = new HashMap<>();
        String number = request.getParameter("number");
        User user = userService.getUserByNumber(number);
        int limit = user.getLimit();
        String name = "";
        System.out.println(limit);
        if(UserLimitUtil.verify(limit, UserLimitUtil.USER_STUDENT))
            name = studentInfoService.getStudentInfoByNumber(number).getName();
        else if(UserLimitUtil.verify(limit, UserLimitUtil.USER_TEACHER))
            name = systemService.getTeacherInfoByNumber(number).getName();
        else if(UserLimitUtil.verify(limit, UserLimitUtil.USER_MANAGER))
            name = "manager";
        final long cur = System.currentTimeMillis() / 1000;
        Session cache = new Session();
        cache.setUserId(user.getId());
        cache.setNumber(number);
        cache.setName(name);
        cache.setLimit(user.getLimit());
        cache.setLoginTime(cur);
        session.setAttribute("cache", cache);
        session.setMaxInactiveInterval(30*60*24);
        map.put("result", 200);
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) throws Exception {
        session.removeAttribute("cache");
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
        if(phoneNumber==null)
            tag = 1;
        else {
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
            } else
                map.put("result", 1);
        } else
            map.put("result", 2);
        return JSON.toJSONString(map);
    }
}
