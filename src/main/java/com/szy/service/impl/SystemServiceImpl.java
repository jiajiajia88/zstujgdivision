package com.szy.service.impl;

import com.szy.po.*;
import com.szy.mapper.SystemMapper;
import com.szy.mapper.UserMapper;
import com.szy.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 系统相关Service实现类
 * Created by Administrator on 2016/10/18.
 */
@Service("studentService")
public class SystemServiceImpl implements SystemService{

    @Autowired
    SystemMapper systemMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public void addGrade(String grade) throws Exception {
        systemMapper.insertGrade(grade);
    }

    @Override
    public List<Grade> findGradesAll() throws Exception {
        return systemMapper.findGradesAll();
    }

    @Override
    public void deleteGrade(int id) throws Exception {
        systemMapper.deleteGrade(id);
    }

    @Override
    public void addSpecies(Species species) throws Exception {
        systemMapper.insertSpecies(species);
    }

    @Override
    public List<Species> findSpeciesAll() throws Exception {
        return systemMapper.findSpeciesAll();
    }

    @Override
    public void deleteSpecies(int id) throws Exception {
        systemMapper.deleteSpecies(id);
    }

    @Override
    public void updateSpecies(int speciesId, String speciesName) throws Exception {
        systemMapper.updateSpecies(speciesId,speciesName);
    }

    @Override
    public void addPositions(Positions positions) throws Exception {
        systemMapper.insertPositions(positions);
    }

    @Override
    public List<Positions> findPositionsAll() throws Exception {
        return systemMapper.findPositionsAll();
    }

    @Override
    public void deletePositions(int id) throws Exception {
        systemMapper.deletePositions(id);
    }

    @Override
    public void updatePositions(int id, String position) throws Exception {
        systemMapper.updatePositions(id,position);
    }

    @Override
    public Positions getPositionsByDescription(String description) throws Exception {
        return systemMapper.findPositionsByDescription(description);
    }

    @Override
    public void addTeacherInfo(TeacherInfo teacherInfo) throws Exception {
        systemMapper.insertTeacherInfo(teacherInfo);
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
        systemMapper.updateTeacherInfo(teacherInfo);
    }

    @Override
    public void deleteTeacherInfo(int id) throws Exception {
        systemMapper.deleteTeacherInfo(id);
    }

    @Override
    public List<TeacherInfo> getAllTeacherInfo() throws Exception {
        return systemMapper.findAllTeacherInfos();
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
    public void addMajor(Major major) throws Exception {
        systemMapper.insertMajor(major);
    }

    @Override
    public List<Major> findMajorsAll() throws Exception {
        return systemMapper.findMajorsAll();
    }

    @Override
    public void deleteMajor(int id) throws Exception {
        systemMapper.deleteMajors(id);
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
    public TeacherInfo getTeacherInfoById(int id) throws Exception {
        return systemMapper.findTeacherInfoById(id);
    }

}
