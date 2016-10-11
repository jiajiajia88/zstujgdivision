package com.szy.util;

import com.szy.entity.StudentGrade;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Excel工具类
 * Created by Administrator on 2016/10/6.
 */
public class ImportExcelUtil {

    private final static String excel2003L =".xls";    //2003- 版本的excel
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel

    /**
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象
     */
    public  List<StudentGrade> getBankListByExcel(InputStream in, String fileName) throws Exception{

        List<StudentGrade> list = new LinkedList<>();
        //创建Excel工作薄
        Workbook work = this.getWorkbook(in,fileName);
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        //遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if(sheet==null){continue;}

            //遍历当前sheet中的所有行
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if(row==null||row.getFirstCellNum()==j){continue;}
                if(j>=2){
                    list.add(this.getStudentGrade(row));
                }
            }
        }
        work.close();
        return list;
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     */
    private  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(excel2003L.equals(fileType)){
            wb = new HSSFWorkbook(inStr);  //2003-
        }else if(excel2007U.equals(fileType)){
            wb = new XSSFWorkbook(inStr);  //2007+
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 将Excel里的一条记录转成一个StudentGrade对象
     * @param row
     * @return
     */
    private StudentGrade getStudentGrade(Row row){

        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat df = new DecimalFormat("0.00");  //格式化number
        DecimalFormat df2 = new DecimalFormat("0");
        DecimalFormat df3 = new DecimalFormat("0.000000");

        StudentGrade studentGrade = new StudentGrade();

        //姓名
        Cell cell0 = row.getCell(0);
        studentGrade.setName(cell0.getRichStringCellValue().getString());
        System.out.println(studentGrade.getName());
        //平均学分绩点
        Cell cell1 = row.getCell(1);
        studentGrade.setGPA(Double.parseDouble(df.format(cell1.getNumericCellValue())));
        System.out.println(studentGrade.getGPA());
        //平均学分绩点*70%
        studentGrade.setRealGPA(studentGrade.getGPA()*0.7);
        System.out.println(studentGrade.getRealGPA());
        //生源地
        Cell cell3 = row.getCell(3);
        studentGrade.setStuFrom(cell3.getRichStringCellValue().getString());
        System.out.println(studentGrade.getStuFrom());
        //文理科
        Cell cell4 = row.getCell(4);
        studentGrade.setDivision(cell4.getRichStringCellValue().getString());
        System.out.println(studentGrade.getDivision());
        //高考成绩
        Cell cell5 = row.getCell(5);
        studentGrade.setEntranceScore(Integer.parseInt(df2.format(cell5.getNumericCellValue())));
        System.out.println(studentGrade.getEntranceScore());
        //生源省高考录取线
        Cell cell6 = row.getCell(6);
        studentGrade.setAdmissionScore(Integer.parseInt(df2.format(cell6.getNumericCellValue())));
        System.out.println(studentGrade.getAdmissionScore());
        //高考成绩/生源省高考录取线
        try{
            double gradeOne = studentGrade.getEntranceScore()/studentGrade.getAdmissionScore();
            studentGrade.setGradeOne(Double.parseDouble(df3.format(gradeOne)));
            System.out.println(studentGrade.getGradeOne());
        } catch (Exception e){
            studentGrade.setGradeOne(Double.parseDouble("0"));
            System.out.println(studentGrade.getGradeOne());
        }
        //30%*高考成绩/生源省高考录取线
        studentGrade.setGradeTwo(Double.parseDouble(df3.format(studentGrade.getGradeOne()*0.3)));
        System.out.println(studentGrade.getGradeTwo());
        //总成绩=70%*平均学分绩点 + 30%*高考成绩/生源省高考录取线
        studentGrade.setTotalGrade(Double.parseDouble(df3.format(studentGrade.getRealGPA()+studentGrade.getGradeTwo())));
        System.out.println(studentGrade.getTotalGrade());
        return studentGrade;
    }

}
