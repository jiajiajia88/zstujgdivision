package com.szy.mapper;

import com.szy.po.*;
import com.szy.po.vo.TeacherInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedList;
import java.util.List;

/**
 * 系统相关持久层接口
 * Created by Administrator on 2016/10/17.
 */

@Mapper
public interface SystemMapper {

    /**
     * 插入年级
     * @param grade
     * @throws Exception
     */
    void insertGrade(String grade) throws Exception;

    /**
     * 查找所有的年级
     * @return
     * @throws Exception
     */
    LinkedList<Grade> findGradesAll() throws Exception;

    /**
     * 删除年级
     * @param id
     * @throws Exception
     */
    void deleteGrade(int id) throws Exception;

    /**
     * 插入大类
     * @param species
     * @throws Exception
     */
    void insertSpecies(Species species) throws Exception;

    /**
     * 查找所有全部大类
     * @return
     * @throws Exception
     */
    LinkedList<Species> findSpeciesAll() throws Exception;

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
     * 根据大类名获取整个大类信息
     * @param speciesName
     * @return
     * @throws Exception
     */
    Species findSpeciesByName(String speciesName) throws Exception;

    /**
     * 根据大类名获取整个大类信息
     * @param speciesId
     * @return
     * @throws Exception
     */
    Species findSpeciesById(int speciesId) throws Exception;

    /**
     * 插入职位
     * @param description
     * @throws Exception
     */
    void insertPositions(Positions description) throws Exception;

    /**
     * 查找所有全部职位
     * @return
     * @throws Exception
     */
    LinkedList<Positions> findPositionsAll() throws Exception;

    /**
     * 删除职位
     * @param id
     * @throws Exception
     */
    void deletePositions(int id) throws Exception;

    /**
     * 更新职位
     * @param description
     * @throws Exception
     */
    void updatePositions(int id,String description) throws Exception;

    /**
     * 查询职位信息
     * @param description
     * @return
     * @throws Exception
     */
    Positions findPositionsByDescription(String description) throws Exception;

    /**
     * 找到大类编号最后一位
     */
    Species findLastSpeciesId() throws Exception;

    /**
     * 插入专业
     * @param major
     * @throws Exception
     */
    void insertMajor(Major major) throws Exception;

    /**
     * 查找所有全部专业
     * @return
     * @throws Exception
     */
    LinkedList<Major> findMajorsAll() throws Exception;

    /**
     * 删除专业
     * @param major_id
     * @throws Exception
     */
    void deleteMajors(int major_id) throws Exception;

    /**
     * 根据专业名获取整个专业信息
     * @param majorName
     * @return
     * @throws Exception
     */
    Major findMajorByName(String majorName) throws Exception;

    List<Major> findMajorsBySpeciesId(int speciesId) throws Exception;

    /**
     * 根据大类id获取已有专业数量
     * @param speciesId
     * @return
     * @throws Exception
     */
    int getMajorCountBySpeciesId(int speciesId) throws Exception;

    /**
     * 插入教师信息
     * @param teacherInfo
     * @throws Exception
     */
    void insertTeacherInfo(TeacherInfo teacherInfo) throws Exception;

    /**
     * 更新教师用户信息
     * @param teacherInfo
     * @throws Exception
     */
    void updateTeacherInfo(TeacherInfo teacherInfo) throws Exception;

    /**
     * 删除教师用户
     * @param id
     * @throws Exception
     */
    void deleteTeacherInfo(int id) throws Exception;

    /**
     * 获取所有教师用户信息
     * @return
     * @throws Exception
     */
    List<TeacherInfoVo> findAllTeacherInfos() throws Exception;

    TeacherInfoVo findTeacherInfoById(int id) throws Exception;

    TeacherInfoVo findTeacherInfoByNumber(String number) throws Exception;

}
