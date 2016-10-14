package com.szy.api;

import com.szy.entity.UserStudent;
import com.szy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * Created by Administrator on 2016/9/15.
 */
@RestController
@RequestMapping({"/home"})
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/likeName")
    public List<UserStudent> likeName(@RequestParam String name){
        System.out.println(name);
        return userService.likeName(name);
    }

    @RequestMapping("/user")
    @ResponseBody
    public String user(){
        return userService.userInfo();
    }

}
