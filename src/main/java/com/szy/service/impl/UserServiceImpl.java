package com.szy.service.impl;

import com.szy.entity.UserStudent;
import com.szy.mapper.StudentMapper;
import com.szy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
 *
 * Created by Administrator on 2016/9/15.
 */
@Service("userService")
public class UserServiceImpl implements UserService, Serializable{

    @Autowired
    StudentMapper studentMapper;

    public String userInfo(){
        UserStudent user = studentMapper.findUserByName("施周勇");
        return user.getName()+"-----"+user.getNumber();
    }

    public List<UserStudent> likeName(String name){
        return studentMapper.likeName(name);
    }
}
