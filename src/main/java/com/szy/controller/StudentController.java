package com.szy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 学生模块的路由
 * Created by Administrator on 2016/9/30.
 */
@Controller
@RequestMapping({"/student"})
public class StudentController {



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
    public String intent_fill(Model model){
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
}
