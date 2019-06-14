package com.unifs.behavioranalysis.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @title: ProbabilityRandomUtil
 * @projectName RedEnvelopeScene
 * @description: 概率随机数生成器, 指定随机数
 * @author： 张恭雨
 * @date 2019/5/5 16:18
 */
public class ProbabilityRandomUtil {

    private List<Double> probability;//随机数生成的概率列表
    private List<Double> randoms;//随机数的范围（指定）随机数概率与相应位置对应


    //初始化随机集合指定大小
    public ProbabilityRandomUtil(int size) {
        //初始化概率，概率之和为1
        probability = new ArrayList<>(size);
        //初始化随机对象
        randoms = new ArrayList<>(size);
    }

    /*
     * 初始化概率表，判断概率之和是否为1
     * 初始化随机数列表
     */
    public boolean init(List<Double> prob,List<Double> randoms) {
        double count = 0;
        for (double i : prob)
            count = count + i;
        if (count == 1) {
            for (int i = 0; i < prob.size(); i++){
                this.probability.add(prob.get(i));
                this.randoms.add(randoms.get(i));
            }
            return true;
        } else
            return false;
    }

    /*
     * 生成随机数，范围（0-randoms）
     */
    public double random() {
        if (randoms.size() <= 0)
            return -1;
        else {
            double randomNumber = Math.random();
            double count = 0;
            double out = -1;
            for (int i = 0; i < randoms.size(); i++) {
                count = count + probability.get(i);

                if (randomNumber <= count) {
                    out = randoms.get(i);
                    break;
                } else {
                    continue;
                }
            }
            return out;
        }
    }

    /*
     * 获取（0-randoms）之间的概率
     */
    public double getProbability(int k) {
        return probability.get(k);
    }

}
