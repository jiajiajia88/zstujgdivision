package com.szy.service;

import com.szy.po.*;

import java.util.List;

/**
 * 系统相关Service接口
 * Created by Administrator on 2016/10/18.
 */
public interface SystemService {

    /**
     * 增加年级
     * @param grade
     * @throws Exception
     */
    void addGrade(String grade) throws Exception;

    /**
     * 查找所有的年级
     * @return
     * @throws Exception
     */
    List<Grade> findGradesAll() throws Exception;

    /**
     * 删除年级
     * @param id
     * @throws Exception
     */
    void deleteGrade(int id) throws Exception;

    /**
     * 增加大类
     * @param species
     * @throws Exception
     */
    void addSpecies(Species species) throws Exception;

    /**
     * 查找所有全部大类
     * @return
     * @throws Exception
     */
    List<Species> findSpeciesAll() throws Exception;

    /**
     * 删除大类
     * @param id
     * @throws Exception
     */
    void deleteSpecies(int id) throws Exception;

    /**
     * 更新大类
     * @param speciesId
     * @param speciesName
     * @throws Exception
     */
    void updateSpecies(int speciesId,String speciesName) throws Exception;

    /**
     * 增加职位
     * @param positions
     * @throws Exception
     */
    void addPositions(Positions positions) throws Exception;

    /**
     * 查找所有全部职位
     * @return
     * @throws Exception
     */
    List<Positions> findPositionsAll() throws Exception;

    /**
     * 删除职位
     * @param id
     * @throws Exception
     */
    void deletePositions(int id) throws Exception;

    /**
     * 更新职位
     * @param position
     * @throws Exception
     */
    void updatePositions(int id, String position) throws Exception;

    /**
     * 获得职位信息
     * @return
     * @throws Exception
     */
    Positions getPositionsByDescription(String description) throws Exception;

    /**
     * 增加教师用户
     * @param teacherInfo
     * @throws Exception
     */
    void addTeacherInfo(TeacherInfo teacherInfo) throws Exception;

    /**
     * 添加教师用户
     * @param teacherInfo
     * @throws Exception
     */
    void addTeacher(TeacherInfo teacherInfo) throws Exception;

    /**
     * 修改教师信息
     * @param teacherInfo
     * @throws Exception
     */
    void updateTeacherInfo(TeacherInfo teacherInfo) throws Exception;

    /**
     * 删除教师账号
     * @param id
     * @throws Exception
     */
    void deleteTeacherInfo(int id) throws Exception;

    /**
     * 查找最后一个Species的id
     * @return
     * @throws Exception
     */
    int findLastSpeciesId() throws Exception;

    /**
     * 根据大类名查找大类信息
     * @return
     * @throws Exception
     */
    Species getSpeciesByName(String speciesName) throws Exception;

    /**
     * 根据大类Id查找大类信息
     * @param speciesId
     * @return
     * @throws Exception
     */
    Species getSpeciesById(int speciesId) throws Exception;

    /**
     * 增加专业
     * @param major
     * @throws Exception
     */
    void addMajor(Major major) throws Exception;

    /**
     * 查找所有全部专业
     * @return
     * @throws Exception
     */
    List<Major> findMajorsAll() throws Exception;

    /**
     * 删除专业
     * @param id
     * @throws Exception
     */
    void deleteMajor(int id) throws Exception;

    /**
     * 根据专业名查找专业信息
     * @return
     * @throws Exception
     */
    Major getMajorByName(String majorName) throws Exception;

    /**
     * 根据大类id获取已有专业数量
     * @param speciesId
     * @return
     * @throws Exception
     */
    int getMajorCountBySpeciesId(int speciesId) throws Exception;

    List<TeacherInfoVo> getAllTeacherInfo() throws Exception;

    TeacherInfoVo getTeacherInfoById(int id) throws Exception;

    TeacherInfoVo getTeacherInfoByNumber(String number) throws Exception;

    List<Major> getMajorsBySpeciesId(int speciesId) throws Exception;

}
