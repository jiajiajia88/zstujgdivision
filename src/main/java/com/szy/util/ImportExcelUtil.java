package com.szy.util;

import com.szy.po.StudentInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static Logger logger = LoggerFactory.getLogger(ImportExcelUtil.class);

    private final static String excel2003L =".xls";    //2003- 版本的excel
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
    private final static DecimalFormat df = new DecimalFormat("0");  //格式化number
    private final static DecimalFormat df2 = new DecimalFormat("0.00");
    private final static DecimalFormat df3 = new DecimalFormat("0.000");
    private final static DecimalFormat df6 = new DecimalFormat("0.000000");

    /**
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象
     */
    public static List<StudentInfo> getStuInfoListByExcel(InputStream in, String fileName, int type) throws Exception{

        List<StudentInfo> list = new LinkedList<>();
        //创建Excel工作薄
        Workbook work = getWorkbook(in,fileName);
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        //遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if(sheet == null) {
                continue;
            }

            //遍历当前sheet中的所有行
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {

                row = sheet.getRow(j);
                if(row == null || row.getFirstCellNum() == j){
                    continue;
                }
                if(j >= 1){
                    try {
                        if (type == 1) {
                            list.add(parseBasicInfo(row));
                        } else if (type == 2) {
                            list.add(parseGpaInfo(row));
                        } else if(type == 3){
                            list.add(parseEntranceInfo(row));
                        }
                    } catch(Exception e){
                        logger.error("遍历第"+j+"条记录是出现问题！");
                    }
                }
            }
        }
        work.close();
        System.out.println(list.size());
        return list;
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     */
    private static Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
        Workbook wb;
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

    private static StudentInfo parseBasicInfo(Row row){

        StudentInfo basicInfo = new StudentInfo();

        //学号
        Cell cell1 = row.getCell(1);
        basicInfo.setNumber(cell1.getRichStringCellValue().getString());
        //System.out.println(basicInfo.getNumber());

        //姓名
        Cell cell2 = row.getCell(2);
        basicInfo.setName(cell2.getRichStringCellValue().getString());
        //System.out.println(basicInfo.getName());

        //原班级
        Cell cell3 = row.getCell(3);
        basicInfo.setOriginalClass(cell3.getRichStringCellValue().getString());
        //System.out.println(basicInfo.getOriginalClass());

        return basicInfo;
    }

    private static StudentInfo parseGpaInfo(Row row){

        StudentInfo gpaInfo = new StudentInfo();

        //学号
        Cell cell1 = row.getCell(0);
        gpaInfo.setNumber(cell1.getRichStringCellValue().getString());
        //System.out.println(gpaInfo.getNumber());

        //平均学分绩点
        Cell cell2 = row.getCell(2);
        gpaInfo.setGpa(Double.parseDouble(df2.format(cell2.getNumericCellValue())));
        //System.out.println(gpaInfo.getGpa());

        //平均学分绩点*70%
        gpaInfo.setRealgpa(Double.parseDouble(df3.format(gpaInfo.getGpa()*0.7)));
        //System.out.println(gpaInfo.getRealgpa());

        return gpaInfo;
    }

    private static StudentInfo parseEntranceInfo(Row row){

        StudentInfo entranceInfo = new StudentInfo();

        //生源地
        Cell cell1 = row.getCell(2);
        entranceInfo.setStufrom(cell1.getRichStringCellValue().getString());
        //System.out.println(entranceInfo.getStufrom());

        //文理科
        Cell cell2 = row.getCell(3);
        String division = cell2.getRichStringCellValue().getString();
        if(division.equals("文科") || division.equals("文")){
            entranceInfo.setDivision(1);
        } else if(division.equals("理科") || division.equals("理")){
            entranceInfo.setDivision(2);
        } else {
            entranceInfo.setDivision(3);
        }
        //System.out.println(entranceInfo.getDivision());

        //高考成绩
        Cell cell3 = row.getCell(4);
        entranceInfo.setEntrancescore(Integer.parseInt(df.format(cell3.getNumericCellValue())));
        //System.out.println(entranceInfo.getEntrancescore());

        //生源省高考录取线
        Cell cell4 = row.getCell(5);
        entranceInfo.setAdmissionscore(Integer.parseInt(df.format(cell4.getNumericCellValue())));
        //System.out.println(entranceInfo.getEntrancescore());

        //学号
        Cell cell5 = row.getCell(0);
        entranceInfo.setNumber(cell5.getRichStringCellValue().getString());
        //System.out.println(entranceInfo.getNumber());

        return entranceInfo;
    }



    /**
     * 将Excel里的一条记录转成一个StudentGrade对象
     * @param row
     * @return
     */
    private static StudentInfo getStudentGrade(Row row){

        StudentInfo studentGrade = new StudentInfo();

        //姓名
        /*Cell cell0 = row.getCell(0);
        studentGrade.setName(cell0.getRichStringCellValue().getString());
        System.out.println(studentGrade.getName());*/
        //平均学分绩点
        Cell cell1 = row.getCell(1);
        studentGrade.setGpa(Double.parseDouble(df.format(cell1.getNumericCellValue())));
        System.out.println(studentGrade.getGpa());
        //平均学分绩点*70%
        studentGrade.setRealgpa(studentGrade.getGpa()*0.7);
        System.out.println(studentGrade.getRealgpa());
        //生源地
        Cell cell3 = row.getCell(3);
        studentGrade.setStufrom(cell3.getRichStringCellValue().getString());
        System.out.println(studentGrade.getStufrom());
        //文理科
        /*Cell cell4 = row.getCell(4);
        studentGrade.setDivision(cell4.getRichStringCellValue().getString());
        System.out.println(studentGrade.getDivision());*/
        //高考成绩
        Cell cell5 = row.getCell(5);
        studentGrade.setEntrancescore(Integer.parseInt(df2.format(cell5.getNumericCellValue())));
        System.out.println(studentGrade.getEntrancescore());
        //生源省高考录取线
        Cell cell6 = row.getCell(6);
        studentGrade.setAdmissionscore(Integer.parseInt(df2.format(cell6.getNumericCellValue())));
        System.out.println(studentGrade.getAdmissionscore());
        //高考成绩/生源省高考录取线
        try{
            double gradeOne = studentGrade.getEntrancescore()/studentGrade.getAdmissionscore();
            studentGrade.setGradeone(Double.parseDouble(df3.format(gradeOne)));
            System.out.println(studentGrade.getGradeone());
        } catch (Exception e){
            studentGrade.setGradeone(Double.parseDouble("0"));
            System.out.println(studentGrade.getGradeone());
        }
        //30%*高考成绩/生源省高考录取线
        studentGrade.setGradetwo(Double.parseDouble(df3.format(studentGrade.getGradeone()*0.3)));
        System.out.println(studentGrade.getGradetwo());
        //总成绩=70%*平均学分绩点 + 30%*高考成绩/生源省高考录取线
        studentGrade.setTotalgrade(Double.parseDouble(df3.format(studentGrade.getRealgpa()+studentGrade.getGradetwo())));
        System.out.println(studentGrade.getTotalgrade());
        return studentGrade;
    }

}
