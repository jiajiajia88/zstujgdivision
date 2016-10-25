package com.szy.inceptor;

/**
 * 注册拦截器
 * Created by Administrator on 2016/10/21.
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserSecurityInterceptor()).addPathPatterns("/loginSubmit");
        registry.addInterceptor(new TeacherSecurityInterceptor()).addPathPatterns("/teacher/*");
        registry.addInterceptor(new StudentSecurityInterceptor()).addPathPatterns("/student/*");
        registry.addInterceptor(new SystemSecurityInterceptor()).addPathPatterns("/system/*");
    }
}
