package com.szy.controller;

import com.szy.entity.UserStudent;
import com.szy.util.ImportExcelUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * 文件相关控制器
 * Created by Administrator on 2016/10/5.
 */
@Controller
@RequestMapping({"/file"})
public class FileController {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping("/fileUpload")
    public String fileUpload(Model model,@RequestParam("file") MultipartFile file,
                             HttpServletRequest request) throws Exception {
        System.out.println("通过传统方式form表单提交方式导入excel文件！");
        long startTime = System.currentTimeMillis();

        InputStream in =null;
        List<List<Object>> listob = null;
        if(file.isEmpty()){
            throw new Exception("文件不存在！");
        }
        in = file.getInputStream();
        new ImportExcelUtil().getBankListByExcel(in,file.getOriginalFilename());
        in.close();

        long endTime = System.currentTimeMillis();
        logger.info("导入文件花费时间为:"+(endTime-startTime));

        //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
        /*for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            if(i==0){
                System.out.println(lo.get(0));
                System.out.println(lo.get(1));
                System.out.println(lo.get(2));
                System.out.println(lo.get(3));
            } else {
                System.out.println(lo.get(0));
                System.out.println(lo.get(1));
                System.out.println(Double.parseDouble(String.valueOf(lo.get(1))));
                System.out.println(lo.get(3));
            }
        }*/
        return "redirect:/teacher/stu_info";
    }

    @RequestMapping("/fileImpl")
    public String fileImpl(Model model, HttpServletRequest request) {
        //解析上传数据，得到数据集合
        List<FileItem> list = analyticalFile(request);
        System.out.println(list.size());
        for (FileItem fileItem : list) {
            //如果fileItem封装的是普通输入项的数据
            if(fileItem.isFormField()){
                //得到输入项字段名称
                String fieldName = fileItem.getFieldName();
                //结局普通输入项的数据中文乱码问题
                String value = "";
                try {
                    value = fileItem.getString("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                System.out.println(fieldName+">>>"+value);
            }else{
                //如果fileItem封装的是上传文件
                //得到上传文件名称
                String name = fileItem.getName();
                if(name==null || name.trim().equals("")){
                    continue;
                }
                //注意：不同的浏览器提交的文件名称的是不同的，有些浏览器提交上来的的文件名是带路径的，如D:\a\b\c.txt
                name = name.substring(name.indexOf("\\")+1);
                System.out.println(name);
                //获取上传文件输入流
                InputStream input = null;
                try {
                    input = fileItem.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //解析excel文件
                List<UserStudent> analyticalExcel = analyticalExcel(input);
                for (UserStudent stu : analyticalExcel) {
                    System.out.println(stu.getId()+" "+stu.getNumber());
                }/*
                UserStudentService.insertList(analyticalExcel);*/
            }
        }
        return "redirect:/teacher/grades";
    }

    /**
     * 解析上传数据
     * @param request
     * @return
     */
    public List<FileItem> analyticalFile(HttpServletRequest request) {
        // 创建DiskFileItemFactory工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 创建文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 解决上传文件名的中文乱码
        upload.setHeaderEncoding("utf-8");
        // 判断上传来的数据是否是上传表单的数据
        if (!ServletFileUpload.isMultipartContent(request)) {
            return null;
        }
        // 使用ServletFileUpload解析器解析上传数据，解析返回结果是一个List<FileItem>的集合，每个FileItem都对应一个form表单的输入项
        List<FileItem> list = null;
        try {
            list = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 解析excel
     * @param input
     */
    @SuppressWarnings("resource")
    public List<UserStudent> analyticalExcel(InputStream input){
        List<UserStudent> list = new ArrayList<UserStudent>();
        //创建工作簿
        HSSFWorkbook wb =  null;
        try {
            wb = new HSSFWorkbook(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取第一个sheet
        HSSFSheet sheet = wb.getSheetAt(0);
        //获取sheet所占行数
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            //获取行
            HSSFRow row = sheet.getRow(i);
            //获取所占列数
            int lastCellNum = row.getLastCellNum();
            UserStudent stu = new UserStudent();
            for (int j = 0; j < lastCellNum; j++) {
                HSSFCell cell = row.getCell(j);
                String value = cell.toString();
                if(j == 0){
                    stu.setId(Integer.parseInt(value.substring(0,value.indexOf("."))));
                }else if(j == 1){
                    //stu.setName(value);
                }else{
                    stu.setNumber(value.substring(0,value.indexOf(".")));
                }
            }
            list.add(stu);
        }
        return list;
    }
}
