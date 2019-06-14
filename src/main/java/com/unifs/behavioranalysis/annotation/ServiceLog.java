package com.unifs.behavioranalysis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version V1.0
 * @title: ServiceLog
 * @projectName ExcetptionAndValid
 * @description: service日志记录注解
 * @author： 张恭雨
 * @date 2019/5/7 16:11
 */
@Target(ElementType.METHOD)     // 这个注解是用来规定注解的作用范围的,这里定义为method方法级别.
@Retention(RetentionPolicy.RUNTIME)    // 这个注解可以理解为定义注解的生命周期,这里标识一直存在(编译和运行之后)
public @interface ServiceLog {

    String context() default "";
}
