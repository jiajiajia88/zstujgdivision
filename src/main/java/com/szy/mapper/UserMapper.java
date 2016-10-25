package com.szy.mapper;

import com.szy.po.User;
import com.szy.po.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedList;
import java.util.List;

/**
 * 用户相关持久层接口
 * Created by Administrator on 2016/10/16.
 */
@Mapper
public interface UserMapper {

    /**
     * 插入用户
     * @param user
     * @throws Exception
     */
    void insertUser(User user) throws Exception;

    /**
     * 分配用户角色
     * @param userId
     * @param roleId
     * @throws Exception
     */
    void insertUserRole(int userId,int roleId) throws Exception;

    /**
     * 查找所有教师用户
     * @return
     * @throws Exception
     */
    List<User> findAllTeachers() throws Exception;

    /**
     * 查找所有学生用户
     * @return
     * @throws Exception
     */
    List<User> findAllStudents() throws Exception;

    /**
     * 根据学工号查找用户
     * @param number
     * @return
     * @throws Exception
     */
    User findUserByNumber(String number) throws Exception;

    /**
     * 根据学工号查找角色
     * @param number
     * @return
     * @throws Exception
     */
    String findRoleByNumber(String number) throws Exception;

    /**
     * 判断是否存在用户
     * @param number
     * @return
     * @throws Exception
     */
    int ifExistsUserByNumber(String number) throws Exception;

    /**
     * 判断用户名密码是否正确
     * @param number
     * @param password
     * @return
     * @throws Exception
     */
    int ifExistsUserByNumberAndPwd(String number, String password) throws Exception;

    /**
     * 更新密码
     * @param number
     * @param password
     * @throws Exception
     */
    void updatePwdByNumber(String number, String password) throws Exception;

    /**
     * 删除用户
     * @param number
     * @throws Exception
     */
    void deleteUserByNumber(String number) throws Exception;

    /**
     * 查询用户对应模块的权限
     * @param number
     * @throws Exception
     */
    int ifHasAccess(String number, String module) throws Exception;

    /**
     * 根据学工号查询拥有的权限
     * @param number
     * @return
     * @throws Exception
     */
    List<String> getAccessByNumber(String number) throws Exception;



}
