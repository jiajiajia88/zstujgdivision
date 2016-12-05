package com.szy.service.impl;

import com.szy.mapper.IntentMapper;
import com.szy.mapper.StudentInfoMapper;
import com.szy.mapper.UserMapper;
import com.szy.po.*;
import com.szy.po.vo.StudentInfoVo;
import com.szy.service.StudentInfoService;
import com.szy.service.SystemService;
import com.szy.service.UserService;
import com.szy.util.ImportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;
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

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

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
            if(ifExistsStudentInfo(stuBasicInfo.getNumber())){
                studentInfoMapper.updateBasicInfo(stuBasicInfo.getNumber(),stuBasicInfo.getName(),
                        stuBasicInfo.getOriginalClass(),species);
            } else {
                studentInfoMapper.insertBasicInfo(stuBasicInfo.getNumber(),stuBasicInfo.getName(),
                        stuBasicInfo.getOriginalClass(),species);

            }
       }
    }

    @Override
    public void importGpa(InputStream in, String fileName) throws Exception {

        List<StudentInfo> gpaInfoList =  ImportExcelUtil.getStuInfoListByExcel(in,fileName,2);
        System.out.println(gpaInfoList.size());
        for (StudentInfo gpaInfo : gpaInfoList){
            if(ifExistsStudentInfo(gpaInfo.getNumber())){
                //System.out.println(ifExistsStudentInfo(gpaInfo.getNumber()));
                studentInfoMapper.updateGpa(gpaInfo.getGpa(),gpaInfo.getRealgpa(),gpaInfo.getNumber());
            } else {
                //System.out.println(ifExistsStudentInfo(gpaInfo.getNumber()));
                studentInfoMapper.insertGpa(gpaInfo.getGpa(),gpaInfo.getRealgpa(),gpaInfo.getNumber());
            }

        }
    }

    @Override
    public void importEntranceScores(InputStream in, String fileName) throws Exception {
        List<StudentInfo> entranceInfoList =  ImportExcelUtil.getStuInfoListByExcel(in,fileName,3);
        for(StudentInfo entranceInfo : entranceInfoList){
            if(ifExistsStudentInfo(entranceInfo.getNumber())){
                studentInfoMapper.updateEntranceScores(entranceInfo.getEntrancescore(),entranceInfo.getAdmissionscore(),
                        entranceInfo.getDivision(),entranceInfo.getStufrom(),entranceInfo.getNumber());
            } else {
                studentInfoMapper.insertEntranceScores(entranceInfo.getEntrancescore(),entranceInfo.getAdmissionscore(),
                        entranceInfo.getDivision(),entranceInfo.getStufrom(),entranceInfo.getNumber());
            }

        }
    }

    @Override
    public void importTotalScores(InputStream in, String fileName,int species) throws Exception {
        List<StudentInfo> studentInfoList =  ImportExcelUtil.getStuInfoListByExcel(in,fileName,4);
        for(StudentInfo studentInfo : studentInfoList){
            studentInfo.setSpecies(species);
            if(ifExistsStudentInfo(studentInfo.getNumber())){
                studentInfoMapper.updateTotalInfo(studentInfo);
            } else {
                User user = new User();
                user.setNumber(studentInfo.getNumber());
                user.setPassword("123456");
                user.setCreateTime(new Date());
                userService.addUser(user,3);
                studentInfoMapper.insertTotalInfo(studentInfo);
            }
        }
    }

    @Override
    public List<StudentInfoVo> getStudentInfosAll() throws Exception {
        List<StudentInfoVo> studentInfoVoList = new LinkedList<>();
        List<StudentInfo> studentInfoList = studentInfoMapper.findStudentInfoAll();
        for(StudentInfo studentInfo:studentInfoList){
            StudentInfoVo studentInfoVo = new StudentInfoVo();
            if(studentInfo.getSpecies()!=0){
                studentInfoVo.setSpeciesName(systemService.getSpeciesById(studentInfo.getSpecies()%1000).getSpeciesName());
                studentInfo.setSpecies(studentInfo.getSpecies()/1000);
            }
            studentInfoVo.setStudentInfo(studentInfo);
            studentInfoVoList.add(studentInfoVo);
        }
        return studentInfoVoList;
    }

    @Override
    public List<StudentInfo> getSimpleStudentInfosAll() throws Exception {
        return studentInfoMapper.findStudentInfoAll();
    }

    @Override
    public StudentInfo getStudentInfoById(int id) throws Exception {
        StudentInfo studentInfo = studentInfoMapper.findStudentInfoById(id);
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
    public boolean ifExistIntent(String number) throws Exception {
        return (1==intentMapper.ifExistIntent(number));
    }

    @Override
    public boolean ifExistIntentId(int id) throws Exception {
        return 1==intentMapper.ifExistIntentId(id);
    }

    @Override
    public int findMajorIdByName(String name) throws Exception {
        return intentMapper.findMajorIdByName(name);
    }

    @Override
    public void insertIntent(String name, String classes, String number, String telephone, int first, int second, int third) throws Exception {

        intentMapper.insertIntent(name,classes,number,telephone,first,second,third);
    }

    @Override
    public void updateIntent(String number,int first, int second, int third) throws Exception {
        intentMapper.updateIntent(number,first, second, third);
    }

    @Override
    public IntentFill findAllIntentByNumber(String number) throws Exception {
        return intentMapper.findAllIntentByNumber(number);
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
    public Boolean studentIntentUpdate(int id, String first, String second, String third,String number) throws Exception {

        int firstId,secondId,thirdId;

        firstId = this.findMajorIdByName(first);
        secondId = this.findMajorIdByName(second);
        if(third==null){
            thirdId = 0;
        } else {
            thirdId = this.findMajorIdByName(third);
        }

        System.out.println(firstId + "/" + secondId + "/" + thirdId);

        if(this.ifExistIntent(number)){
            this.updateIntent(number,firstId,secondId,thirdId);
            System.out.println("更改志愿");
        } else {
            StudentInfo studentInfo = this.getStudentInfoByNumber(number);
            String studentName = studentInfo.getName();
            String studentNumber = studentInfo.getNumber();
            String classes = studentInfo.getOriginalClass();
            String telephone = studentInfo.getTelephone();
            this.insertIntent(studentName,classes,studentNumber,telephone,firstId,secondId,thirdId);
            System.out.println("添加志愿");
        }
        return true;
    }

    @Override
    public Boolean ifExistMajorName(String name) throws Exception {
        return intentMapper.ifExistMajorName(name)==1;
    }

    @Override
    public Boolean ifExistsStudentInfo(String number) throws Exception {
        return (1==studentInfoMapper.ifExistsStudentInfo(number));
    }

}
