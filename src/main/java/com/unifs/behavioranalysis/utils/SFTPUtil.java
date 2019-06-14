package com.unifs.behavioranalysis.utils;
import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;
import java.util.Vector;

/**
 *
 * @ClassName: SFTPUtil
 * @Description: sftp连接工具类
 * @date 2017年5月22日 下午11:17:21
 * @version 1.0.0
 */
public class SFTPUtil {
    private transient Logger log = LoggerFactory.getLogger(this.getClass());

    private ChannelSftp sftp;

    private Session session;
    /** FTP 登录用户名*/
    private String username;
    /** FTP 登录密码*/
    private String password;
    /** FTP 服务器地址IP地址*/
    private String host;
    /** FTP 端口*/
    private int port;


    /**
     * 构造基于密码认证的sftp对象
     * @param
     * @param password
     * @param host
     * @param port
     */
    public SFTPUtil(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    public SFTPUtil(){}

    /**
     * 连接sftp服务器
     *
     * @throws Exception
     */
    public void login(){
        try {
            JSch jsch = new JSch();
            /*if (privateKey != null) {
                jsch.addIdentity(privateKey);// 设置私钥
                log.info("sftp connect,path of private key file：{}" , privateKey);
            }
            log.info("sftp connect by host:{} username:{}",host,username);*/

            session = jsch.getSession(username, host, port);
            log.info("Session is build");
            if (password != null) {
                session.setPassword(password);
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.connect();
            log.info("Session is connected");

            Channel channel = session.openChannel("sftp");
            channel.connect();
            log.info("channel is connected");

            sftp = (ChannelSftp) channel;
            log.info(String.format("sftp server host:[%s] port:[%s] is connect successfull", host, port));
        } catch (JSchException e) {
            log.error("Cannot connect to specified sftp server : {}:{} \n Exception message is: {}", new Object[]{host, port, e.getMessage()});
        }
    }

    /**
     * 关闭连接 server
     */
    public void logout(){
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
                log.info("sftp is closed already");
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
                log.info("sshSession is closed already");
            }
        }
    }

    /**
     * 将输入流的数据上传到sftp作为文件
     *
     * @param directory
     *            上传到该目录
     * @param sftpFileName
     *            sftp端文件名
     * @param
     *
     * @throws SftpException
     * @throws Exception
     */
    public void upload(String directory, String sftpFileName, InputStream input) throws SftpException{
        try {
            sftp.cd(directory);
        } catch (SftpException e) {
            log.warn("directory is not exist");
            sftp.mkdir(directory);
            sftp.cd(directory);
        }
        sftp.put(input, sftpFileName);
        log.info("file:{} is upload successful" , sftpFileName);
    }

    /**
     * 上传单个文件
     *
     * @param directory
     *            上传到sftp目录
     * @param uploadFile
     *            要上传的文件,包括路径
     * @throws FileNotFoundException
     * @throws SftpException
     * @throws Exception
     */
    public void upload(String directory, String uploadFile) throws FileNotFoundException, SftpException{
        File file = new File(uploadFile);
        upload(directory, file.getName(), new FileInputStream(file));
    }

    /**
     * 下载文件
     *
     * @param directory
     *            下载目录
     * @param downloadFile
     *            下载的文件
     * @param : 存在本地的路径
     *
     * @throws SftpException
     * @throws FileNotFoundException
     * @throws Exception
     */
    public void download(String directory, String downloadFile, String saveDirectory) throws SftpException, FileNotFoundException{
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        String saveFile = saveDirectory + "//" + downloadFile;
        File file = new File(saveFile);
        sftp.get(downloadFile, new FileOutputStream(file));
        log.info("file:{} is download successful" , downloadFile);
    }

    /**
     * 删除文件
     *
     * @param directory
     *            要删除文件所在目录
     * @param deleteFile
     *            要删除的文件
     * @throws SftpException
     * @throws Exception
     */
    public void delete(String directory, String deleteFile) throws SftpException{
        sftp.cd(directory);
        sftp.rm(deleteFile);
    }

    /**
     * 列出目录下的文件
     *
     * @param directory
     *            要列出的目录
     * @param
     * @return
     * @throws SftpException
     */
    public Vector<?> listFiles(String directory) throws SftpException {
        return sftp.ls(directory);
    }

    public static void main(String newFileName,InputStream is) throws SftpException, IOException {
        SFTPUtil sftp = new SFTPUtil("fhfsltwxgzhuser", "Nprodfsltwxgzh", "120.52.49.68", 8122);
        sftp.login();
        //上传目的地址待确定
        sftp.upload("/upload/", newFileName, is);
        sftp.logout();
    }
}