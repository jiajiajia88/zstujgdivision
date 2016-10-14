package com.szy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

/**
 * 系统模块的路由
 * Created by Administrator on 2016/9/23.
 */
@Controller
public class SystemController {

    @Value("${application.message:1234556677}")
    private String message = "hi,hello world......";

    @RequestMapping({ "/", "index" })
    public String index(Model model){
        model.addAttribute("page","index");
        return "index";//返回的内容就是templetes下面文件的名称
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("page","login");
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(Model model){
        return "logout";
    }

    @RequestMapping("/account")
    public String account(Model model){
        model.addAttribute("page", "account");
        return "account";
    }

    @RequestMapping("/system_settings")
    public String system_settings(Model model){
        model.addAttribute("page", "system_settings");
        return "system_settings";
    }

}
