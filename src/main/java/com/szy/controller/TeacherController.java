package com.szy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 教师模块的路由
 * Created by Administrator on 2016/9/30.
 */
@Controller
@RequestMapping({"/teacher"})
public class TeacherController {

    @RequestMapping("/plan_settings")
    public String plan_settings(Model model){
        model.addAttribute("page", "plan_settings");
        return "plan_settings";
    }

    @RequestMapping("/notice")
    public String notice(Model model){
        model.addAttribute("page", "notice");
        return "notice";
    }

    @RequestMapping("/stu_info")
    public String stu_info(Model model){
        model.addAttribute("page", "stu_info");
        return "stu_info";
    }

    @RequestMapping("/fileUpload")
    public String fileUpload(Model model) {
        model.addAttribute("page", "grades");
        return "grades";
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
