package com.unifs.behavioranalysis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication(exclude = {
//        DataSourceAutoConfiguration.class}) //多数据源 禁用自动配置数据源，使用durid
@SpringBootApplication
@MapperScan("com.unifs.behavioranalysis.dao") //配置dao扫描路径
public class BehaviorAnalysisApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BehaviorAnalysisApplication.class, args);
    }
    /**
     　　* @description: 项目打包配置
     　　* @param ${tags}
     　　* @return ${return_type}
     　　* @throws
     　　* @author 张恭雨
     　　* @date 2019/5/8 11:48
     　　*/
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BehaviorAnalysisApplication.class);
    }

}
