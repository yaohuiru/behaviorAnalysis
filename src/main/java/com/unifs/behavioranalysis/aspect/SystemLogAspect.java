package com.unifs.behavioranalysis.aspect;

import com.alibaba.fastjson.JSON;
import com.unifs.behavioranalysis.annotation.ControlLog;
import com.unifs.behavioranalysis.annotation.ServiceLog;
import com.unifs.behavioranalysis.base.LogEntity;
import com.unifs.behavioranalysis.base.Resp;
import com.unifs.behavioranalysis.service.LogService;
import com.unifs.behavioranalysis.utils.HttpClientUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @title: DetailLogAspect
 * @projectName ExcetptionAndValid
 * @description: 系统日志处理切面
 * @author： 张恭雨
 * @date 2019/4/29 11:59
 */
@Component
@Aspect
public class SystemLogAspect {
    @Autowired
    private LogService logService;

    private Logger log = LoggerFactory.getLogger(SystemLogAspect.class);

    /* 控制层切点
     这里定义下切点的位置,也就是刚才我们自定义的注解.
     这里的切点为注解的方式，我们也使用直接切入到service或control
     eg：@Pointcut("execution(public * com.unifs.baseframe.controller..*.*(..))")
    */
    @Pointcut("@annotation(com.unifs.behavioranalysis.annotation.ControlLog)")
    public void controlPointcut() {
    }

    /*service层切点*/
    @Pointcut("@annotation(com.unifs.behavioranalysis.annotation.ServiceLog)")
    public void servicePointcut() {
    }

    //before，在切点之前触发，
    @Before("controlPointcut()")
    public void controlBeforeLog(JoinPoint joinPoint) throws Exception {
        //初始化日志信息
        LogEntity logEntity = initLog(joinPoint);
        //记录日志
        logService.saveLog(logEntity);



    }


    //消息通知 @AfterReturning,在切点方法运行之后触发returning 为目标函数返回值
    @AfterReturning(returning = "resp", value = "controlPointcut()")
    public void controlAfterLog(JoinPoint joinPoint, Resp resp) {

        String status;
        // 获取操作状态
        if (resp.getCode() == 1) {
            status = "成功";
        } else {
            status = "失败";
        }

        LogEntity logEntity = initLog(joinPoint);
        //设置访问结果状态
        logEntity.setStatus(status);
        //设置详细信息
        logEntity.setDetail(resp.getMsg());
        //记录日志
        logService.saveLog(logEntity);


    }


    //异常通知
    @AfterThrowing(value = "controlPointcut()", throwing = "e")
    public void controlAfterThrowing(JoinPoint joinPoint, Throwable e) {
        //初始化日志信息
        LogEntity logEntity = initLog(joinPoint);
        logEntity.setStatus("失败！");
        logEntity.setDetail(e.getStackTrace().toString());
        //记录日志
        logService.saveLog(logEntity);

    }

    //服务层，访问之前记录
    @Before(value = "servicePointcut()")
    public void serviceBeforeLog(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        String context = "";       //  定义操作内容

        // 获取注解中的操作方式
        if (method != null && !"".equals(method)) {
            // 获取自定义注解操作
            ServiceLog serviceLog = method.getAnnotation(ServiceLog.class);
            // 获取用户操作内容
            context = serviceLog.context();
        }
        StringBuffer interfacePath = new StringBuffer();
        // 获取请求的类名
        interfacePath.append(joinPoint.getTarget().getClass().getName());
        // 获取请求的方法名
        interfacePath.append("." + method.getName());

        // 获取请求的ip地址
        String ip = HttpClientUtil.getClientIp(request);

        // 获取请求的参数
        String argsName[] = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        Map<String, Object> paramMap = new HashMap<>();
        if (argsName.length > 0) {
            paramMap = getParam(joinPoint, argsName, method.getName());
        }
        String args = JSON.toJSONString(paramMap);


        // 日志实体类封装
        LogEntity logEntity = new LogEntity();

        logEntity.setIp(ip);
        logEntity.setContent(context);
        logEntity.setDetail(args);
        logEntity.setTime(new Date());
        logEntity.setInterfacePath(interfacePath.toString());

        logService.saveLog(logEntity);

    }

    //服务层，返回之后记录
    @AfterReturning(value = "servicePointcut()")
    public void serviceAfterLog(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        String context = "";       //  定义操作内容

        // 获取注解中的操作方式
        if (method != null && !"".equals(method)) {
            // 获取自定义注解操作
            ServiceLog serviceLog = method.getAnnotation(ServiceLog.class);
            // 获取用户操作内容
            context = serviceLog.context();
        }
        StringBuffer interfacePath = new StringBuffer();
        // 获取请求的类名
        interfacePath.append(joinPoint.getTarget().getClass().getName());
        // 获取请求的方法名
        interfacePath.append("." + method.getName());

        // 获取请求的ip地址
        String ip = HttpClientUtil.getClientIp(request);

        // 获取请求的参数
        String argsName[] = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        Map<String, Object> paramMap = new HashMap<>();
        if (argsName.length > 0) {
            paramMap = getParam(joinPoint, argsName, method.getName());
        }
        String args = JSON.toJSONString(paramMap);

        method.getReturnType();


        // 日志实体类封装
        LogEntity logEntity = new LogEntity();

        logEntity.setIp(ip);
        logEntity.setContent(context);
        logEntity.setDetail(args);
        logEntity.setTime(new Date());
        logEntity.setInterfacePath(interfacePath.toString());

        logService.saveLog(logEntity);

    }

    // 处理参数格式,并返回需要的参数
    public static Map<String, Object> getParam(JoinPoint joinPoint, String argsName[], String methodName) {
        Map<String, Object> detailMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();

        // 获取参数值
        Object args[] = joinPoint.getArgs();
        // 获取参数名
        argsName = ((CodeSignature) joinPoint.getSignature()).getParameterNames();

        for (int i = 0; i < argsName.length; i++) {
            if (!argsName[i].equals("model")) {
                map.put(argsName[i], args[i]);
            }
        }
        detailMap.put("method", methodName);
        detailMap.put("params", map);

        return detailMap;
    }

    private LogEntity initLog(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        String operateType = "";         //  定义操作方式
        String operateContent = "";       //  定义操作内容

        // 获取注解中的操作方式
        if (method != null && !"".equals(method)) {
            // 获取自定义注解操作
            ControlLog controlLog = method.getAnnotation(ControlLog.class);
            // 获取用户操作方式
            operateType = controlLog.operateType();
            // 获取用户操作内容
            operateContent = controlLog.context();
        }
        StringBuffer interfacePath = new StringBuffer();     //定义类路径
        // 获取请求的类名
        interfacePath.append(joinPoint.getTarget().getClass().getName());
        // 获取请求的方法名
        interfacePath.append("." + method.getName());

        // 获取请求的ip地址
        String ip = HttpClientUtil.getClientIp(request);

        // 获取请求的参数
        String argsName[] = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        Map<String, Object> paramMap = new HashMap<>();
        if (argsName.length > 0) {
            paramMap = getParam(joinPoint, argsName, method.getName());
        }
        String args = JSON.toJSONString(paramMap);

        LogEntity logEntity = new LogEntity();
        //初始化日志信息
        //设置来访者真实IP地址
        logEntity.setIp(ip);
        //设置访问参数
        logEntity.setArgs(args);
        //设置内容
        logEntity.setContent(operateContent);
        //设置开始访问接口时间
        logEntity.setTime(new Date());
        //设置操作类型
        logEntity.setType(operateType);
        //设置访问类路径
        logEntity.setInterfacePath(interfacePath.toString());

        return logEntity;
    }

}
