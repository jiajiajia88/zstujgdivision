package com.szy.inceptor;

import com.szy.RespEnum;
import com.szy.cache.Session;
import com.szy.controller.TeacherController;
import com.szy.po.User;
import com.szy.service.UserService;
import com.szy.util.CacheUtil;
import com.szy.util.UserLimitUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * 登陆控制拦截器
 * Created by Administrator on 2016/10/21.
 */
public class UserSecurityInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(UserSecurityInterceptor.class);

    private static UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        int retCode = 0;
        String number = request.getParameter("number");
        String password = request.getParameter("password");
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        userService = (UserService) factory.getBean("userService");
        System.out.println(number);
        System.out.println(password);

        if(userService.ifExistsUser(number)){
            if(userService.checkLogin(number, password)){
                if(!userService.ifHasAccess(number, UserLimitUtil.USER_LOGIN))
                    retCode = RespEnum.NO_ACCESS.getRetCode();
            } else
                retCode = RespEnum.PASSWD_ERR.getRetCode();
        } else
            retCode = RespEnum.NO_USERNAME.getRetCode();


        if(retCode == 0){
            return true;
        } else {
            PrintWriter out = response.getWriter();
            out.print(retCode);
            out.flush();
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
