package com.szy.service.impl;

import com.szy.mapper.UserMapper;
import com.szy.po.User;
import com.szy.po.UserRole;
import com.szy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by Administrator on 2016/9/15.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;


    @Override
    public void addUser(User user) throws Exception {
        userMapper.insertUser(user);
    }

    @Override
    public List<User> getAllTeachers() throws Exception {
        return userMapper.findAllTeachers();
    }

    @Override
    public List<User> getAllStudents() throws Exception {
        return userMapper.findAllStudents();
    }

    @Override
    public User getUserByNumber(String number) throws Exception {
        return userMapper.findUserByNumber(number);
    }

    @Override
    public String getRoleByNumber(String number) throws Exception {
        return userMapper.findRoleByNumber(number);
    }

    @Override
    public boolean ifExistsUser(String number) throws Exception {
        return (1==userMapper.ifExistsUserByNumber(number));
    }

    @Override
    public boolean checkLogin(String number, String password) throws Exception {
        return (1==userMapper.ifExistsUserByNumberAndPwd(number,password));
    }

    @Override
    public void updatePwd(String number, String password) throws Exception {
        userMapper.updatePwdByNumber(number,password);
    }

    @Override
    public void deleteUser(String number) throws Exception {
        userMapper.deleteUserByNumber(number);
    }

    @Override
    public boolean ifHasAccessOfLogin(String number) throws Exception {
        return (1==userMapper.ifHasAccessOfLogin(number));
    }
}
