package com.szy.controller;

import com.alibaba.fastjson.JSON;
import com.szy.service.StudentInfoService;
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

    @RequestMapping({ "/", "index" })
    public String index(Model model, HttpSession session) {
        if(null == session.getAttribute("number")){
            return "redirect:/login";
        } else {
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
        Map<String, Integer> map = new HashMap<String, Integer>();
        String number = request.getParameter("number");
        session.setAttribute("number",number);
        map.put("result", 200);
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
}
