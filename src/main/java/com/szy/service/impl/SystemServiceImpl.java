package com.szy.service.impl;

import com.szy.po.*;
import com.szy.mapper.SystemMapper;
import com.szy.mapper.UserMapper;
import com.szy.po.vo.TeacherInfoVo;
import com.szy.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 系统相关Service实现类
 * Created by Administrator on 2016/10/18.
 */
@Service("systemService")
public class SystemServiceImpl implements SystemService{

    @Autowired
    SystemMapper systemMapper;

    @Autowired
    UserMapper userMapper;

    private static volatile List<Grade> gradeList = new ArrayList<>();
    private static volatile List<Major> majorList = new ArrayList<>();
    private static volatile List<Species> speciesList = new ArrayList<>();
    private static volatile List<Positions> positionList = new ArrayList<>();
    private static volatile List<TeacherInfoVo> teacherInfoList = new ArrayList<>();

    @Override
    public void checkout() throws Exception{
        gradeList = systemMapper.findGradesAll();
        majorList = systemMapper.findMajorsAll();
        speciesList = systemMapper.findSpeciesAll();
        positionList = systemMapper.findPositionsAll();
        teacherInfoList = systemMapper.findAllTeacherInfos();
    }

    @Override
    public void addGrade(String grade) throws Exception {
        systemMapper.insertGrade(grade);
        gradeList = systemMapper.findGradesAll();
    }

    @Override
    public List<Grade> findGradesAll() throws Exception {
        return gradeList;
    }

    @Override
    public void deleteGrade(int id) throws Exception {
        systemMapper.deleteGrade(id);
        gradeList = systemMapper.findGradesAll();
    }

    @Override
    public void addSpecies(Species species) throws Exception {
        systemMapper.insertSpecies(species);
        speciesList = systemMapper.findSpeciesAll();
    }

    @Override
    public List<Species> findSpeciesAll() throws Exception {
        return speciesList;
    }

    @Override
    public void deleteSpecies(int id) throws Exception {
        systemMapper.deleteSpecies(id);
        speciesList = systemMapper.findSpeciesAll();
    }

    @Override
    public void updateSpecies(int speciesId, String speciesName) throws Exception {
        systemMapper.updateSpecies(speciesId,speciesName);
        speciesList = systemMapper.findSpeciesAll();
    }

    @Override
    public void addPositions(Positions positions) throws Exception {
        systemMapper.insertPositions(positions);
        positionList = systemMapper.findPositionsAll();
    }

    @Override
    public List<Positions> findPositionsAll() throws Exception {
        return positionList;
    }

    @Override
    public void deletePositions(int id) throws Exception {
        systemMapper.deletePositions(id);
        positionList = systemMapper.findPositionsAll();
    }

    @Override
    public void updatePositions(int id, String position) throws Exception {
        systemMapper.updatePositions(id,position);
        positionList = systemMapper.findPositionsAll();
    }

    @Override
    public Positions getPositionsByDescription(String description) throws Exception {
        return systemMapper.findPositionsByDescription(description);
    }

    @Override
    public void addTeacherInfo(TeacherInfo teacherInfo) throws Exception {
        systemMapper.insertTeacherInfo(teacherInfo);
        teacherInfoList = systemMapper.findAllTeacherInfos();
    }

    @Override
    public void addTeacher(TeacherInfo teacherInfo) throws Exception {
        systemMapper.insertTeacherInfo(teacherInfo);
        User teacher = new User();
        teacher.setNumber(teacherInfo.getNumber());
        teacher.setPassword("123456");
        teacher.setCreateTime(new Date());
        userMapper.insertUser(teacher);
        userMapper.insertUserRole(userMapper.findUserByNumber(teacherInfo.getNumber()).getId(),2);
    }

    @Override
    public void updateTeacherInfo(TeacherInfo teacherInfo) throws Exception {
        //System.out.println(teacherInfo.getNumber()+"-----"+teacherInfo.getName());
        systemMapper.updateTeacherInfo(teacherInfo);
        teacherInfoList = systemMapper.findAllTeacherInfos();
    }

    @Override
    public void deleteTeacherInfo(int id) throws Exception {
        systemMapper.deleteTeacherInfo(id);
        teacherInfoList = systemMapper.findAllTeacherInfos();
    }


    @Override
    public int findLastSpeciesId() {
        try {
            return systemMapper.findLastSpeciesId().getSpeciesId();
        } catch (Exception e) {
            return 100;
        }
    }

    @Override
    public Species getSpeciesByName(String speciesName) throws Exception {
        return systemMapper.findSpeciesByName(speciesName);
    }

    @Override
    public Species getSpeciesById(int speciesId) throws Exception {
        return systemMapper.findSpeciesById(speciesId);
    }

    @Override
    public void addMajor(Major major) throws Exception {
        systemMapper.insertMajor(major);
        majorList = systemMapper.findMajorsAll();
    }

    @Override
    public List<Major> findMajorsAll() throws Exception {
        return majorList;
    }

    @Override
    public void deleteMajor(int id) throws Exception {
        systemMapper.deleteMajors(id);
        majorList = systemMapper.findMajorsAll();
    }

    @Override
    public Major getMajorByName(String majorName) throws Exception {
        return systemMapper.findMajorByName(majorName);
    }

    @Override
    public int getMajorCountBySpeciesId(int speciesId) throws Exception {
        return systemMapper.getMajorCountBySpeciesId(speciesId);
    }

    @Override
    public List<TeacherInfoVo> getAllTeacherInfo() throws Exception {
        return teacherInfoList;
    }

    @Override
    public TeacherInfoVo getTeacherInfoById(int id) throws Exception {
        return systemMapper.findTeacherInfoById(id);
    }

    @Override
    public TeacherInfoVo getTeacherInfoByNumber(String number) throws Exception {
        return systemMapper.findTeacherInfoByNumber(number);
    }

    @Override
    public List<Major> getMajorsBySpeciesId(int speciesId) throws Exception {
        return systemMapper.findMajorsBySpeciesId(speciesId);
    }

}
