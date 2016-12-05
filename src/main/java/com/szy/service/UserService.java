package com.szy.service;

import com.szy.po.User;

import java.util.List;

/**
 *
 * Created by Administrator on 2016/9/15.
 */
public interface UserService {

    /**
     * 增加用户
     * @param user
     * @throws Exception
     */
    void addUser(User user) throws Exception;

    /**
     * 查找所有教师用户
     * @return
     * @throws Exception
     */
    List<User> getAllTeachers() throws Exception;

    /**
     * 查找所有学生用户
     * @return
     * @throws Exception
     */
    List<User> getAllStudents() throws Exception;

    /**
     * 根据学工号查找用户
     * @param number
     * @return
     * @throws Exception
     */
    User getUserByNumber(String number) throws Exception;

    /**
     * 判断是否存在用户
     * @param number
     * @return
     * @throws Exception
     */
    boolean ifExistsUser(String number) throws Exception;

    /**
     * 检查登陆
     * @param number
     * @param password
     * @return
     * @throws Exception
     */
    boolean checkLogin(String number, String password) throws Exception;

    /**
     * 更新用户密码
     * @param number
     * @param password
     * @throws Exception
     */
    void updatePwd(String number, String password) throws Exception;

    /**
     * 删除用户
     * @param number
     * @throws Exception
     */
    void deleteUser(String number) throws Exception;

    /**
     * 是否有登录权限
     * @param number
     * @return
     * @throws Exception
     */
    boolean ifHasAccess(String number,int limit) throws Exception;

}
