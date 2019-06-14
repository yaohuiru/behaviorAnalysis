package com.unifs.behavioranalysis.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @创建人 张恭雨
 * @创建时间 2018/8/20
 * @描述httpClient远程接口访问工具类
 */
public class HttpClientUtil {
    private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    /*
     *功能描述 向远程接口发起请求，返回字符类型结果
     * @author 张恭雨
     * @param url 接口地址
     * @param requestType 请求类型
     * @param params 传递参数
     * @return String 返回结果
     */
    public static String httpRequestToString(String url, String requestType,
                                             Map<String, Object> params) {
        log.info("当前位置：HttpClientUtil httpRequestToString" +
                "进入远程接口调用");
        //接口返回结果
        String requestResult = null;

        try {
            String parameters = "";
            boolean hasParams = false;    //判断是否传入了参数，默认为false（无参数）
            //将参数集合拼接成字符串
            for (String key : params.keySet()) {
                String value = URLEncoder.encode(params.get(key).toString(), "UTF-8");
                parameters += key + "=" + value + "&";
                hasParams = true;
            }
            //除去字符串末尾了&符号
            if (hasParams) {
                parameters = parameters.substring(0, parameters.length() - 1);
            }
            //判断请求类型并创建对应的请求对象
            boolean isGet = "get".equalsIgnoreCase(requestType);
            boolean isPost = "post".equalsIgnoreCase(requestType);
            boolean isDelete = "delete".equalsIgnoreCase(requestType);
            boolean isPut = "put".equalsIgnoreCase(requestType);

            //创建连接对象
            DefaultHttpClient client = new DefaultHttpClient();
            HttpRequestBase method = null;
            if (isGet) {
                url += "?" + parameters;
                method = new HttpGet(url);
            } else if (isPost) {
                method = new HttpPut(url);
                HttpPut putMethod = (HttpPut) method;
                StringEntity entity = new StringEntity(parameters);
                putMethod.setEntity(entity);

            } else if (isDelete) {
                url += "?" + parameters;
                method = new HttpDelete(url);

            } else if (isPut) {
                method = new HttpPut(url);
                HttpPut putMethod = (HttpPut) method;
                StringEntity entity = new StringEntity(parameters);
                putMethod.setEntity(entity);
            }
            //设置请求超时时间
            method.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 6000);
            //设置请求头
            method.addHeader("Content-Type", "application/x-www-form-urlencoded");
            //访问接口，返回状态码
            HttpResponse response = client.execute(method);
            //判断状态码为200
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                requestResult = EntityUtils.toString(response.getEntity());   //返回json格式
                log.info("接口调用成功");
            }
            client.close();
        } catch (UnsupportedEncodingException e) {
            log.error("接口调用失败，错误：" + e);
            e.printStackTrace();
        } catch (IOException e) {
            log.error("接口调用失败，错误：" + e);
            e.printStackTrace();
        }
        return requestResult;
    }

    public static String getClientIp(HttpServletRequest request) {

        //String ip = request.getHeader("x-forwarded-for");
        String ip = request.getHeader("X-real-ip");
        log.debug("x-forwarded-for = {}", ip);
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            log.debug("Proxy-Client-IP = {}", ip);
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            log.debug("WL-Proxy-Client-IP = {}", ip);
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            log.debug("RemoteAddr-IP = {}", ip);
        }
        if(StringUtils.isNotBlank(ip)) {
            ip = ip.split(",")[0];
        }
        return ip;

    }
}
