package com.szy.service.impl;

import com.szy.mapper.IntentMapper;
import com.szy.mapper.StudentInfoMapper;
import com.szy.po.IntentFill;
import com.szy.po.Major;
import com.szy.po.StudentInfo;
import com.szy.po.StudentInfoVo;
import com.szy.service.StudentInfoService;
import com.szy.service.SystemService;
import com.szy.util.ImportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Created by Administrator on 2016/10/18.
 */
@Service("studentInfoService")
public class StudentInfoServiceImpl implements StudentInfoService{

    @Autowired
    StudentInfoMapper studentInfoMapper;

    @Autowired
    IntentMapper intentMapper;

    @Autowired
    SystemService systemService;

    @Override
    public String getPhoneNumber(String number) throws Exception {
        StudentInfo studentInfo = studentInfoMapper.findStudentInfoByNumber(number);
        if(studentInfo == null){
            return "";
        } else {
            return studentInfo.getTelephone();
        }
    }

    @Override
    public void updatePhoneNumber(String number, String phoneNumber) throws Exception {
        studentInfoMapper.updateStudentPhone(number, phoneNumber);
    }

    @Override
    public void importBasicInfo(InputStream in, String fileName,int species) throws Exception {
        List<StudentInfo> stuBasicInfoList =  ImportExcelUtil.getStuInfoListByExcel(in,fileName,1);
        for (StudentInfo stuBasicInfo : stuBasicInfoList) {
            studentInfoMapper.insertBasicInfo(stuBasicInfo.getNumber(),stuBasicInfo.getName(),stuBasicInfo.getOriginalClass(),species);
        }
    }

    @Override
    public void importGpa(InputStream in, String fileName) throws Exception {

        List<StudentInfo> gpaInfoList =  ImportExcelUtil.getStuInfoListByExcel(in,fileName,2);
        System.out.println(gpaInfoList.size());
        for (StudentInfo gpaInfo : gpaInfoList){
            studentInfoMapper.updateGpa(gpaInfo.getGpa(),gpaInfo.getRealgpa(),gpaInfo.getNumber());
        }
    }

    @Override
    public void importEntranceScores(InputStream in, String fileName) throws Exception {
        List<StudentInfo> entranceInfoList =  ImportExcelUtil.getStuInfoListByExcel(in,fileName,3);
        for(StudentInfo entranceInfo : entranceInfoList){
            studentInfoMapper.updateEntranceScores(entranceInfo.getEntrancescore(),entranceInfo.getAdmissionscore(),
                    entranceInfo.getDivision(),entranceInfo.getStufrom(),entranceInfo.getNumber());
        }
    }

    @Override
    public List<StudentInfoVo> getStudentInfosAll() throws Exception {
        List<StudentInfoVo> studentInfoVoList = new LinkedList<>();
        List<StudentInfo> studentInfoList = studentInfoMapper.findStudentInfoAll();
        for(StudentInfo studentInfo:studentInfoList){
            StudentInfoVo studentInfoVo = new StudentInfoVo();
            studentInfoVo.setStudentInfo(studentInfo);
            studentInfoVo.setSpeciesName(systemService.getSpeciesById(studentInfo.getSpecies()%1000).getSpeciesName());
            studentInfoVoList.add(studentInfoVo);
        }
        return studentInfoVoList;
    }

    @Override
    public StudentInfo getStudentInfoById(int id) throws Exception {
        System.out.println("---------------");
        StudentInfo studentInfo = studentInfoMapper.findStudentInfoById(id);
        System.out.println(studentInfo.getName());
        return studentInfo;
    }

    @Override
    public StudentInfo getStudentInfoByNumber(String number) throws Exception {
        return studentInfoMapper.findStudentInfoByNumber(number);
    }

    @Override
    public LinkedList<Major> findMajorAll() throws Exception {
        return intentMapper.findMajorAll();
    }

    @Override
    public boolean ifExistIntentId(int id) throws Exception {
        return (intentMapper.ifExistIntentId(id)!=0);
    }

    @Override
    public int findMajorIdByName(String name) throws Exception {
        return intentMapper.findMajorIdByName(name);
    }

    @Override
    public void insertIntent(int id, String name, String classes, String number, String telephone, int first, int second, int third) throws Exception {

        intentMapper.insertIntent(id,name,classes,number,telephone,first,second,third);
    }

    @Override
    public void updateIntent(int id,int first, int second, int third) throws Exception {
        intentMapper.updateIntent(id,first, second, third);
    }

    @Override
    public IntentFill findAllIntentById(int id) throws Exception {
        return intentMapper.findAllIntentById(id);
    }

    @Override
    public String findMajorNameById(int id) throws Exception {
        return intentMapper.findMajorNameById(id);
    }

    @Override
    public Boolean studentIntentUpdate(int id, String first, String second, String third) throws Exception {
        int firstId,secondId,thirdId;

        if(this.ifExistMajorName(first)||this.ifExistMajorName(second)||this.ifExistMajorName(third)) {
            firstId = this.findMajorIdByName(first);
            secondId = this.findMajorIdByName(second);
            thirdId = this.findMajorIdByName(third);
            System.out.println(firstId + "/" + secondId + "/" + thirdId);


            if(this.ifExistIntentId(id)){

                this.updateIntent(id,firstId,secondId,thirdId);

                System.out.println("更改志愿");
            } else {
                StudentInfo studentInfo = this.getStudentInfoById(id);
                String studentName = studentInfo.getName();
                String studentNumber = studentInfo.getNumber();

                //               System.out.println(studentName+"---0"+studentNumber);
                this.insertIntent(id,studentName,"fuck",studentNumber,"15151",firstId,secondId,thirdId);
                System.out.println("添加志愿");
            }
            return true;
        } else{
            return false;
        }
    }

    @Override
    public Boolean ifExistMajorName(String name) throws Exception {
        return intentMapper.ifExistMajorName(name)==1;
    }

}
