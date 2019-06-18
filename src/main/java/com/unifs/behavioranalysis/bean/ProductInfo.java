package com.unifs.behavioranalysis.bean;

import lombok.Data;

import java.util.Date;

@Data
public class ProductInfo {
    private String productId;

    private String productName;

    private String productExplain;

    private String netTypeCode;

    private String brandCode;

    private Date createDate;

    private Date startDate;

    private Date endDate;

    private String updateStaffId;

    private String provinceCode;

    private String cityCode;

    private String districtCode;


}