package com.szy.controller;

import com.alibaba.fastjson.JSON;
import com.szy.po.*;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统模块的路由
 * Created by Administrator on 2016/9/23.
 */
@Controller
@RequestMapping({"/system"})
public class SystemController {

    private Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    UserService userService;

    @Autowired
    StudentInfoService studentInfoService;

    @Autowired
    SystemService systemService;

    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    @RequestMapping("/system_settings")
    public String system_settings(Model model) {
        List<Grade> gradeList = null;
        List<Species> speciesList = null;
        List<TeacherInfoVo> teacherList = null;
        List<Positions> positionList = null;

        try {
            gradeList = systemService.findGradesAll();
            speciesList = systemService.findSpeciesAll();
            positionList = systemService.findPositionsAll();
            teacherList = systemService.getAllTeacherInfo();

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("查询年级、大类、职位数据时发生错误！");
        }
        model.addAttribute("page", "system_settings");
        model.addAttribute("gradeList", gradeList);
        model.addAttribute("speciesList", speciesList);
        model.addAttribute("positionList", positionList);
        model.addAttribute("teacherList", teacherList);
        return "system_settings";
    }

    @RequestMapping("/system_teacher_settings")
    public String system_teacher_settings(Model model) {
        List<TeacherInfoVo> teacherList = null;
        List<Positions> positionList = null;
        try {
            teacherList = systemService.getAllTeacherInfo();
            positionList = systemService.findPositionsAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("查询年级、大类、职位数据时发生错误！");
        }
        model.addAttribute("positionList", positionList);
        model.addAttribute("page", "system_teacher_settings");
        model.addAttribute("teacherList", teacherList);
        return "system_teacher_settings";
    }

    @RequestMapping("/system_basic")
    public String system_basic(Model model) {
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
        model.addAttribute("page", "system_basic");
        model.addAttribute("gradeList", gradeList);
        model.addAttribute("speciesList", speciesList);
        model.addAttribute("positionList", positionList);
        return "system_basic";
    }


    @RequestMapping(value = "/addGrade" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addGrade(Model model, HttpServletRequest request){

        Map<String, Integer> map = new HashMap<String, Integer>();
        String grade = request.getParameter("grade");
        System.out.println(grade);
        try {
            systemService.addGrade(grade);
            System.out.println(grade);
            map.put("result",200);
        } catch (Exception e) {
            map.put("result",0);
            e.printStackTrace();
        }

        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/deleteGrade" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteGrade(Model model, HttpServletRequest request) {

        Map<String, Integer> map = new HashMap<String, Integer>();

        int gradeId = Integer.parseInt(request.getParameter("gradeId"));
        System.out.println(gradeId);

        try {
            systemService.deleteGrade(gradeId);
            map.put("result",200);
        } catch (Exception e) {
            map.put("result",0);
            e.printStackTrace();
        }

        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/deleteSpecies" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteSpecies(Model model, HttpServletRequest request) {

        Map<String, Integer> map = new HashMap<String, Integer>();
        int speciesId = Integer.parseInt(request.getParameter("speciesId"));
        System.out.println(speciesId);
        try {
            systemService.deleteSpecies(speciesId);
            map.put("result",200);
        } catch (Exception e) {
            map.put("result",0);
            e.printStackTrace();
        }

        return JSON.toJSONString(map);
    }

    /**
     * 添加species
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/addSpecies" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addSpecies(Model model, HttpServletRequest request){

        Map<String, Integer> map = new HashMap<String, Integer>();
        String species = request.getParameter("species");

        try {
            Species speciesadd = new Species();
            int lastID = systemService.findLastSpeciesId();//找出最后一位id
            speciesadd.setSpeciesId(lastID+1);//新添加的大类id值为最后一位id值加一
            speciesadd.setSpeciesName(species);
            speciesadd.setStuAmount(0);//生成species对象完毕
            systemService.addSpecies(speciesadd);

            map.put("result",200);
        } catch (Exception e) {
            map.put("result",0);
            e.printStackTrace();
        }

        return JSON.toJSONString(map);
    }

    /**
     * 更新大类名字
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateSpecies" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateSpecies(Model model, HttpServletRequest request){

        Map<String, Integer> map = new HashMap<String, Integer>();

        try {
            int speciesId = Integer.parseInt(request.getParameter("speciesId"));
            String newSpeciesName = request.getParameter("newSpeciesName");
            System.out.println(speciesId+"/"+newSpeciesName);
            systemService.updateSpecies(speciesId,newSpeciesName);
            map.put("result",200);
        } catch (Exception e) {
            map.put("result",0);
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }

    /**
     * 增加教师用户
     * @param request
     * @return
     */
    @RequestMapping(value = "/addTeacher" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addTeacher(HttpServletRequest request){

        Map<String, Integer> map = new HashMap<>();
        String username = request.getParameter("username");
        String number = request.getParameter("number");
        String position = request.getParameter("position");

        System.out.println(number);
        System.out.println(position);

        try {
            TeacherInfo teacherInfo = new TeacherInfo();
            User teacher = new User();
            teacherInfo.setName(username);
            teacherInfo.setNumber(number);
            teacherInfo.setPositionId(systemService.getPositionsByDescription(position).getId());
            teacher.setNumber(number);
            teacher.setPassword("123456");
            teacher.setCreateTime(new Date());
            systemService.addTeacherInfo(teacherInfo);
            userService.addUser(teacher);
            map.put("result",200);
        } catch (Exception e) {
            map.put("result",0);
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }

    /**
     * 删除管理员
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteTeacherr" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteTeacherr(HttpServletRequest request){

        Map<String, Integer> map = new HashMap<>();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            systemService.deleteTeacherInfo(id);
            String number = systemService.getTeacherInfoById(id).getNumber();
            userService.deleteUser(number);
            map.put("result",200);
        } catch (Exception e) {
            map.put("result",0);
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }

    /**
     * 删除管理员
     * 只能更改学工号，其他列需要在UserMapper修改
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateTeacher" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateTeacher(HttpServletRequest request){

        Map<String, Integer> map = new HashMap<>();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("username");
            String number = request.getParameter("number");
            String position = request.getParameter("position");
            int positionId = systemService.getPositionsByDescription(position).getId();

            TeacherInfo teacherInfo = new TeacherInfo();
            teacherInfo.setId(id);
            teacherInfo.setName(username);
            teacherInfo.setNumber(number);
            teacherInfo.setPositionId(positionId);
            systemService.updateTeacherInfo(teacherInfo);
            map.put("result",200);
        } catch (Exception e) {
            map.put("result",0);
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }

    /**
     * 增加职务
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/addPosition" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addPosition(Model model, HttpServletRequest request){

        Map<String, Integer> map = new HashMap<String, Integer>();

        try {
            String position = request.getParameter("position");
            Positions positions = new Positions();
            positions.setDescription(position);
            systemService.addPositions(positions);
            map.put("result",200);
        } catch (Exception e) {
            map.put("result",0);
            e.printStackTrace();
        }

        return JSON.toJSONString(map);
    }

    /**
     * 删除职务
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/deletePosition" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deletePosition(Model model, HttpServletRequest request) {

        Map<String, Integer> map = new HashMap<String, Integer>();

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            systemService.deletePositions(id);
            map.put("result",200);
        } catch (Exception e) {
            map.put("result",0);
            e.printStackTrace();
        }

        return JSON.toJSONString(map);
    }

    /**
     * 更新职务名字
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/updatePosition" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updatePosition(Model model, HttpServletRequest request){

        Map<String, Integer> map = new HashMap<String, Integer>();


        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String newposition = request.getParameter("newposition");
            systemService.updatePositions(id,newposition);
            map.put("result",200);
        } catch (Exception e) {
            map.put("result",0);
            e.printStackTrace();
        }


        return JSON.toJSONString(map);
    }


    @RequestMapping(value = "/deleteMajor" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteMajor(HttpServletRequest request) {

        Map<String, Integer> map = new HashMap<String, Integer>();
        int majorId = Integer.parseInt(request.getParameter("majorId"));
        //System.out.println(majorId);
        try {
            systemService.deleteMajor(majorId);
            map.put("result",200);
        } catch (Exception e) {
            map.put("result",0);
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }


    @RequestMapping("/system_major_settings")
    public String system_major_settings(Model model) {
        List<Species> speciesList = null;
        List<Major> majorList = null;
        try {
            speciesList = systemService.findSpeciesAll();
            majorList = systemService.findMajorsAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("majorList", majorList);
        model.addAttribute("speciesList", speciesList);
        model.addAttribute("page", "system_major_settings");
        return "system_major_settings";
    }

    /**
     * 添加species
     * @param request
     * @return
     */
    @RequestMapping(value = "/addMajor" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addMajor(HttpServletRequest request){

        Map<String, Integer> map = new HashMap<>();
        String major_name = request.getParameter("major_name");
        String major_species = request.getParameter("major_species");
        try {
            Major major = new Major();
            int speciesId = systemService.getSpeciesByName(major_species).getSpeciesId();
            int majorId = speciesId*100 + systemService.getMajorCountBySpeciesId(speciesId);
            major.setMajorId(majorId);
            major.setMajorName(major_name);
            systemService.addMajor(major);
            map.put("result",200);
        } catch (Exception e) {
            map.put("result",0);
            logger.error("数据库查询出错！");
            e.printStackTrace();
        }

        return JSON.toJSONString(map);
    }


}
