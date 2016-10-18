package com.szy.mapper;

import com.szy.entity.Grade;
import com.szy.entity.Positions;
import com.szy.entity.Species;
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
     * @param stuAmount
     * @throws Exception
     */
    void updateSpecies(int speciesId,String speciesName,int stuAmount) throws Exception;

    /**
     * 插入职位
     * @param positions
     * @throws Exception
     */
    void  insertPositions(Positions positions) throws Exception;

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

}
