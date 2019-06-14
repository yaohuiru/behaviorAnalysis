package com.unifs.behavioranalysis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 张恭雨
 * @创建时间 2018/11/12
 * @描述：文件操作工具类
 */
public class FileUtil {

    private static Logger log = LoggerFactory.getLogger(FileUtil.class);

    /*
     * @author 张恭雨
     * @date
     * @param  strings ,path
     * @return
     * 功能描述: 将list按行写到txt文件中
     */
    public static void writeFile(List<String> strings, String path) throws Exception {
        File file = new File(path);
        //判断文件是否存在，没有就创建
        if (!file.isFile()) {
            //创建文件
            file.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        //按行写文件
        for (String s : strings) {
            writer.write(s + "\r\n");
        }
        //关闭文件流
        writer.close();
    }

    /**
     * 获取路径下的所有文件/文件夹
     *
     * @param directoryPath  需要遍历的文件夹路径
     * @param isAddDirectory 是否将子文件夹的路径也添加到list集合中
     * @return
     */
    public static List<String> getAllFile(String directoryPath, boolean isAddDirectory) {
        List<String> list = new ArrayList<String>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        //遍历文件集合
        for (File file : files) {
            //判断是否是文件
            if (file.isDirectory()) {
                //判断是否为txt文件
                if (file.getName().endsWith("txt")) {
                    //是否添加子文件
                    if (isAddDirectory) {
                        list.add(file.getAbsolutePath());
                    }
                    list.addAll(getAllFile(file.getAbsolutePath(), isAddDirectory));
                }
            } else {
                list.add(file.getAbsolutePath());
            }
        }
        return list;
    }

}
