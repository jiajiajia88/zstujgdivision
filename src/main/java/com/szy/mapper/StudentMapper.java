package com.szy.mapper;

import com.szy.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 *
 * Created by Administrator on 2016/9/15.
 */
@Mapper
public interface StudentMapper {

    List<Student> likeName(String name);

    Student getById(int id);

    String getNameById(int id);

    @Select("select * from tb_user_student where name = #{name}")
    Student findUserByName(@Param("name")String name);
}
