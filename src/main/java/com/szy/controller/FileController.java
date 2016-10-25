package com.szy.controller;

import com.szy.service.StudentInfoService;
import com.szy.service.SystemService;
import com.szy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;


/**
 * 文件相关控制器
 * Created by Administrator on 2016/10/5.
 */
@Controller
@RequestMapping({"/file"})
public class FileController {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    UserService userService;

    @Autowired
    StudentInfoService studentInfoService;

    @Autowired
    SystemService systemService;

    /**
     * 导入学生基础信息
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws Exception {

        int uploadType = Integer.parseInt(request.getParameter("uploadType"));
        String grade_post = request.getParameter("grade");
        String species_post = request.getParameter("species");
        int grade = Integer.parseInt(grade_post.substring(0,4));
        int speciesId = systemService.getSpeciesByName(species_post).getSpeciesId();
        int species = grade*1000+speciesId;
        long startTime = System.currentTimeMillis();

        InputStream in;
        if(file.isEmpty()){
            throw new Exception("文件不存在！");
        }
        in = file.getInputStream();
        if(uploadType == 1){
            studentInfoService.importBasicInfo(in,file.getOriginalFilename(),species);
        } else if (uploadType == 2) {
            studentInfoService.importGpa(in,file.getOriginalFilename());
        } else {
            studentInfoService.importEntranceScores(in,file.getOriginalFilename());
        }

        in.close();

        long endTime = System.currentTimeMillis();
        logger.info("导入文件花费时间为:"+(endTime-startTime)+"毫秒");
        if(uploadType == 1){
            return "redirect:/teacher/stu_info_basic";
        } else if (uploadType == 2) {
            return "redirect:/teacher/stu_info_gpa";
        } else if (uploadType == 3){
            return "redirect:/teacher/stu_info_entrance";
        } else {
            return "redirect:/teacher/stu_info_search";
        }

    }

}
