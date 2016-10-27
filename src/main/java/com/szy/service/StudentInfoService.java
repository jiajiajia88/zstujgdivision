package com.szy.service;

import com.szy.po.IntentFill;
import com.szy.po.Major;
import com.szy.po.StudentInfo;
import com.szy.po.StudentInfoVo;

import java.io.InputStream;
import java.util.List;

/**
 *
 * Created by Administrator on 2016/10/18.
 */
public interface StudentInfoService {

    String getPhoneNumber(String number) throws Exception;

    void updatePhoneNumber(String number, String phoneNumber) throws Exception;

    void importBasicInfo(InputStream in, String fileName, int species) throws Exception;

    void importGpa(InputStream in, String fileName) throws Exception;

    void importEntranceScores(InputStream in, String fileName) throws Exception;

    void importTotalScores(InputStream in, String fileName,int species) throws Exception;

    List<StudentInfoVo> getStudentInfosAll() throws Exception;

    List<StudentInfo> getSimpleStudentInfosAll() throws Exception;

    StudentInfo getStudentInfoById(int id) throws Exception;

    StudentInfo getStudentInfoByNumber(String number) throws Exception;

    List<Major> findMajorAll() throws Exception;

    boolean ifExistIntent(String number) throws Exception;

    boolean ifExistIntentId(int id) throws Exception;

    int findMajorIdByName(String name) throws Exception;

    void insertIntent(String name,String classes,String number,String telephone,int first, int second, int third) throws Exception;

    void updateIntent(String number,int first,int second,int third) throws Exception;

    IntentFill findAllIntentByNumber(String number) throws Exception;

    IntentFill findAllIntentById(int id) throws Exception;

    String findMajorNameById(int id) throws Exception;

    Boolean studentIntentUpdate(int id,String first,String second,String third,String number) throws Exception;

    Boolean ifExistMajorName(String name) throws Exception;

    Boolean ifExistsStudentInfo(String number) throws Exception;
}
