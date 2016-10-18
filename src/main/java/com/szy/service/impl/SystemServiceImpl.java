package com.szy.service.impl;

import com.szy.entity.Grade;
import com.szy.entity.Positions;
import com.szy.entity.Species;
import com.szy.entity.UserManager;
import com.szy.mapper.SystemMapper;
import com.szy.mapper.UserMapper;
import com.szy.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

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
    public LinkedList<Grade> findGradesAll() throws Exception {
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
    public LinkedList<Species> findSpeciesAll() throws Exception {
        return systemMapper.findSpeciesAll();
    }

    @Override
    public void deleteSpecies(int id) throws Exception {
        systemMapper.deleteSpecies(id);
    }

    @Override
    public void updateSpecies(int speciesId, String speciesName, int stuAmount) throws Exception {
        systemMapper.updateSpecies(speciesId,speciesName,stuAmount);
    }

    @Override
    public void addPositions(Positions positions) throws Exception {
        systemMapper.insertPositions(positions);
    }

    @Override
    public LinkedList<Positions> findPositionsAll() throws Exception {
        return systemMapper.findPositionsAll();
    }

    @Override
    public void deletePositions(int id) throws Exception {
        systemMapper.deletePositions(id);
    }

    @Override
    public void updatePositions(int id, int grade) throws Exception {
        systemMapper.updatePositions(id,grade);
    }

    @Override
    public void addManager(UserManager userManager) throws Exception {
        userMapper.insertUserManager(userManager);
    }

    @Override
    public void updateManager(UserManager userManager) throws Exception {
        userMapper.updateManager(userManager);
    }

    @Override
    public void deleteManager(int id) throws Exception {
        userMapper.deleteManager(id);
    }
}
