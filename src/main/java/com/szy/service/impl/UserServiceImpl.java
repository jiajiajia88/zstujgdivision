package com.szy.service.impl;

import com.szy.entity.StudentInfo;
import com.szy.entity.UserManager;
import com.szy.entity.UserStudent;
import com.szy.mapper.StudentInfoMapper;
import com.szy.mapper.UserMapper;
import com.szy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by Administrator on 2016/9/15.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean ifExistsUserWithPwd(String number, String password) throws Exception {
        return (1==userMapper.ifExistsStudentWithPwd(number,password));
    }

    @Override
    public boolean ifExistsManagerWithPwd(String number, String password) throws Exception {
        return (1==userMapper.ifExistsManagerWithPwd(number,password));
    }

    public UserStudent findUserStudent(String number) throws Exception{
        return userMapper.findUserStudentByNumber(number);
    }

    @Override
    public UserManager findUserManager(String number) throws Exception {
        return userMapper.findUserManagerByNumber(number);
    }

    @Override
    public void updatePwd(String number, String newPwd, int type) throws Exception {
        if(type==2){
            userMapper.updateStudentPwd(number, newPwd);
        } else {
            userMapper.updateManagerPwd(number, newPwd);
        }
    }

}
