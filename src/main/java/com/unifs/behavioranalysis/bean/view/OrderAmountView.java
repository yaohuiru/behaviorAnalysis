package com.unifs.behavioranalysis.bean.view;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName: OrderAmountView
 * @Author: fanweiqiang
 * @Date: 2019/6/19 19:55
 * @Description: TODO
 */
@Data
@ToString
public class OrderAmountView {
    private String product_name;
    private String month;
    private String sum;
    private String last_month;
    private String last_sum;
}
