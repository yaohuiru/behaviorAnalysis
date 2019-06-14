package com.unifs.behavioranalysis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

/**
 * @创建人 张恭雨
 * @创建时间 2019/2/28
 * @描述：日期工具类
 */
public class DateUtil {
    private static Logger log=LoggerFactory.getLogger(DateUtil.class);
    /*
     * @author 张恭雨
     * @date
     * @param
     * @return
     * 功能描述: 比较年月是否相同
     */
    public static boolean isSameDate(Date date1, Date date2) {
        try {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);

            boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                    .get(Calendar.YEAR);
            boolean isSameMonth = isSameYear
                    && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
            boolean isSameDate = isSameMonth
                    && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                    .get(Calendar.DAY_OF_MONTH);

            return isSameDate;
        } catch (Exception e) {
            log.error("[RatingEngine] error occurred: ERROR ", e);
        }
        return false;
    }
}
