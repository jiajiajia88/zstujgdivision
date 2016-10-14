package com.szy.mapper;

import com.szy.entity.UserStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 * Created by Administrator on 2016/9/15.
 */
@Mapper
public interface StudentMapper {

    List<UserStudent> likeName(String name);

    UserStudent getById(int id);

    String getNameById(int id);

    @Select("select * from tb_user_student where name = #{name}")
    UserStudent findUserByName(@Param("name")String name);
}
