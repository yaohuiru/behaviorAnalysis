package com.unifs.behavioranalysis.config;

import com.unifs.behavioranalysis.interceptor.LoginInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
        * @创建人 张恭雨
        * @创建时间 2018/8/22
        * @描述 拦截器配置器*/


@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    LoginInterceptor loginInterceptor;
    private Logger log=LoggerFactory.getLogger(WebConfig.class);
    //不拦截资源路径
    final String[] notLoginInterceptPaths={"/error/*","/css/**","/js/**"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截器注册
       registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(notLoginInterceptPaths);
       super.addInterceptors(registry);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
