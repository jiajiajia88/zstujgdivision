package com.szy.mapper;

import com.szy.po.IntentFill;
import com.szy.po.Major;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedList;

/**
 * 志愿相关持久层接口
 * Created by lll on 2016/10/19.
 */
@Mapper
public interface IntentMapper {


    /**
     *遍历所有专业
     * */
    LinkedList<Major> findMajorAll() throws Exception;

    void updateIntent(int id,int first,int second,int third) throws Exception;

    void insertIntent(int id,String name,String classes,String number,String telephone,int first,int second,int third) throws Exception;

    int ifExistIntentId(int id) throws Exception;

    int findMajorIdByName(String name) throws Exception;

    String findMajorNameById(int id) throws Exception;

    IntentFill findAllIntentById(int id) throws Exception;

    int ifExistMajorName(String name) throws Exception;

}
