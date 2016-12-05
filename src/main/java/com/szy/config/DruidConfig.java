/*
package com.szy.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * druid数据库连接池
 * Created by Administrator on 2016/10/21.
 *//*

@Configuration
public class DruidConfig {
    */
/**
     * ServletRegistrationBean,
     * @see com.alibaba.druid.support.http.ResourceServlet
     * @return
     *//*

    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean druid = new ServletRegistrationBean();
        druid.setServlet(new StatViewServlet());
        druid.setUrlMappings(Collections.singletonList("/druid*/
/*"));

        Map<String,String> params = new HashMap<>();
        params.put("loginUsername", "admin");
        params.put("loginPassword", "admin");
        druid.setInitParameters(params);
        return druid;
    }

    */
/**
     * @see com.alibaba.druid.support.http.WebStatFilter
     * @return
     *//*

    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean fitler = new FilterRegistrationBean();
        fitler.setFilter(new WebStatFilter());
        fitler.setUrlPatterns(Collections.singletonList("*/
/*"));
        fitler.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid*/
/*");
        return fitler;
    }
}*/
