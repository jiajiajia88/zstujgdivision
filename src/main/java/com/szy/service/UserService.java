package com.szy.service;

import com.szy.entity.Student;

import java.util.List;

/**
 *
 * Created by Administrator on 2016/9/15.
 */
public interface UserService {

    List<Student> likeName(String name);

    String userInfo();
}
