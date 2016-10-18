package com.szy.service;

import com.szy.entity.UserManager;
import com.szy.entity.UserStudent;

/**
 *
 * Created by Administrator on 2016/9/15.
 */
public interface UserService {

    boolean ifExistsUserWithPwd(String number,String password) throws Exception;

    boolean ifExistsManagerWithPwd(String number,String password) throws Exception;

    UserStudent findUserStudent(String number) throws Exception;

    UserManager findUserManager(String number) throws Exception;

    void updatePwd(String number, String newPwd, int type) throws Exception;

}
