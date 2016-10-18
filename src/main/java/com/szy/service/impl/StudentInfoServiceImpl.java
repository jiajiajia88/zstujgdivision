package com.szy.service.impl;

import com.szy.entity.StudentInfo;
import com.szy.mapper.StudentInfoMapper;
import com.szy.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by Administrator on 2016/10/18.
 */
@Service("studentInfoService")
public class StudentInfoServiceImpl implements StudentInfoService{

    @Autowired
    StudentInfoMapper studentInfoMapper;



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
    public void updatePhoneNUmber(String number, String phoneNumber) throws Exception {
        studentInfoMapper.updateStudentPhone(number, phoneNumber);
    }
}
