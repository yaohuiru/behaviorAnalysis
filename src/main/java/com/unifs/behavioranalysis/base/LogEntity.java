package com.unifs.behavioranalysis.base;


import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @version V1.0
 * @title: LogEntity
 * @projectName ExcetptionAndValid
 * @description: TODO
 * @author： 张恭雨
 * @date 2019/4/29 11:54
 */

public class LogEntity {
    private String username;    //来访者姓名
    private String ip;          //来访者IP地址
    private String args;        //参数
    private String interfacePath; //接口路径
    private String status;      //访问结果状态
    private String type;        //操作类型
    private String detail;      //详细
    private Date time;          //访问时间
    private String content;     //内容

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getArgs()
    {
        return args;
    }

    public void setArgs(String args)
    {
        this.args = args;
    }

    public String getInterfacePath()
    {
        return interfacePath;
    }

    public void setInterfacePath(String interfacePath)
    {
        this.interfacePath = interfacePath;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getDetail()
    {
        return detail;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }

    public Date getTime()
    {
        return time;
    }

    public void setTime(Date time)
    {
        this.time = time;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    @Override
    public String toString()
    {
        return "LogEntity{" + "username='" + username + '\'' + ", ip='" + ip + '\'' + ", args='" + args + '\'' + ", " +
                "interfacePath='" + interfacePath + '\'' + ", status='" + status + '\'' + ", type='" + type + '\'' +
                ", detail='" + detail + '\'' + ", time=" + time + ", content='" + content + '\'' + '}';
    }
}
