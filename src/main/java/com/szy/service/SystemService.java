package com.szy.service;

import com.szy.entity.Grade;
import com.szy.entity.Positions;
import com.szy.entity.Species;
import com.szy.entity.UserManager;

import java.util.LinkedList;

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
    LinkedList<Grade> findGradesAll() throws Exception;

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
     * @param stuAmount
     * @throws Exception
     */
    void updateSpecies(int speciesId,String speciesName,int stuAmount) throws Exception;

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
    LinkedList<Positions> findPositionsAll() throws Exception;

    /**
     * 删除职位
     * @param id
     * @throws Exception
     */
    void deletePositions(int id) throws Exception;

    /**
     * 更新职位
     * @param grade
     * @throws Exception
     */
    void updatePositions(int id,int grade) throws Exception;

    /**
     * 增加管理员
     * @param userManager
     * @throws Exception
     */
    void addManager(UserManager userManager) throws Exception;

    /**
     * 修改管理员信息
     * @param userManager
     * @throws Exception
     */
    void updateManager(UserManager userManager) throws Exception;

    /**
     * 删除管理员账号
     * @param id
     * @throws Exception
     */
    void deleteManager(int id) throws Exception;
}
