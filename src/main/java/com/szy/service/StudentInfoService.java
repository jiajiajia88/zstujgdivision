package com.szy.service;

import com.szy.po.IntentFill;
import com.szy.po.Major;
import com.szy.po.StudentInfo;

import java.io.InputStream;
import java.util.List;

/**
 *
 * Created by Administrator on 2016/10/18.
 */
public interface StudentInfoService {

    String getPhoneNumber(String number) throws Exception;

    void updatePhoneNUmber(String number, String phoneNumber) throws Exception;

    void importBasicInfo(InputStream in, String fileName) throws Exception;

    void importGpa(InputStream in, String fileName) throws Exception;

    void importEntranceScores(InputStream in, String fileName) throws Exception;

    List<StudentInfo> getStudentInfosAll() throws Exception;

    StudentInfo getStudentInfoById(int id) throws Exception;

    StudentInfo getStudentInfoByNumber(String number) throws Exception;

    List<Major> findMajorAll() throws Exception;

    boolean ifExistIntentId(int id) throws Exception;

    int findMajorIdByName(String name) throws Exception;

    void insertIntent(int id,String name,String classes,String number,String telephone,int first, int second, int third) throws Exception;

    void updateIntent(int id,int first,int second,int third) throws Exception;

    IntentFill findAllIntentById(int id) throws Exception;

    String findMajorNameById(int id) throws Exception;
}
