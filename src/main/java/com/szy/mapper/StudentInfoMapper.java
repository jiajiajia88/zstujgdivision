package com.szy.mapper;

import com.szy.po.StudentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 学生信息持久层接口
 * Created by lll on 2016/10/16.
 */
@Mapper
public interface StudentInfoMapper {

    /**
     * 导入学生信息
     * @param studentInfo
     * @throws Exception
     */
    void insertTotalInfo(StudentInfo studentInfo) throws Exception;

    /**
     * 更新学生信息
     * @param studentInfo
     * @throws Exception
     */
    void updateTotalInfo(StudentInfo studentInfo) throws Exception;

    /**
     * 插入学生基础信息
     * @param number
     * @param name
     * @param originalClass
     * @throws Exception
     */
    void insertBasicInfo(String number, String name, String originalClass,int species) throws Exception;

    /**
     * 更新学生基础信息
     * @param number
     * @param name
     * @param originalClass
     * @param species
     * @throws Exception
     */
    void updateBasicInfo(String number, String name, String originalClass,int species) throws Exception;

    /**
     * 插入学生gpa信息
     * @param gpa
     * @param realGpa
     * @param number
     * @throws Exception
     */
    void insertGpa(Double gpa, Double realGpa, String number) throws Exception;

    /**
     * 插入学生gpa信息
     * @param gpa
     * @param realGpa
     * @param number
     * @throws Exception
     */
    void updateGpa(Double gpa, Double realGpa, String number) throws Exception;

    /**
     * 更新学生高考信息
     * @param entranceScore
     * @param admissionScore
     * @param division
     * @param stuFrom
     * @param number
     * @throws Exception
     */
    void insertEntranceScores(int entranceScore, int admissionScore, int division, String stuFrom, String number) throws Exception;

    /**
     * 插入学生高考信息
     * @param entranceScore
     * @param admissionScore
     * @param division
     * @param stuFrom
     * @param number
     * @throws Exception
     */
    void updateEntranceScores(int entranceScore, int admissionScore, int division, String stuFrom, String number) throws Exception;

    /**
     * 插入学生计算后分数
     * @param gradeOne
     * @param gradeTwo
     * @param totalGrade
     * @param number
     * @throws Exception
     */
    void updateRealGrade(Double gradeOne, Double gradeTwo, Double totalGrade, String number) throws Exception;

    /**
     * 插入学生现在班级
     * @param presentClass
     * @param number
     * @throws Exception
     */
    void updatePresentClass(String presentClass, String number) throws Exception;

    /**
     * 用id映射出StudentInfo实体
     * */
    StudentInfo findStudentInfoById(int id) throws Exception;

    /**
     * 根据学号查询学生信息
     * @param number
     * @return
     * @throws Exception
     */
    StudentInfo findStudentInfoByNumber(String number) throws Exception;

    /**
     * 查找出所有学生信息
     * @return
     * @throws Exception
     */
    List<StudentInfo> findStudentInfoAll() throws Exception;

    /**
     * 根据原班级查找出学生信息
     * @param originClass
     * @return
     * @throws Exception
     */
    List<StudentInfo> findStudentByOriginClass(int originClass) throws Exception;

    /**
     * 根据现班级查找出学生信息
     * @param presentClass
     * @return
     * @throws Exception
     */
    List<StudentInfo> findStudentByPresentClass(int presentClass) throws Exception;

    /**
     * 修改手机号（长号）
     * @param number
     * @param telephone
     * @throws Exception
     */
    void updateStudentPhone(String number, String telephone) throws Exception;

    int ifExistsStudentInfo(String number) throws Exception;

}
