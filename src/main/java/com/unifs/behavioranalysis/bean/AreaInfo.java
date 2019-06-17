package com.unifs.behavioranalysis.bean;

public class AreaInfo {
    private Integer areaId;

    private String areaName;

    private String parentId;

    private String exStatus;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getExStatus() {
        return exStatus;
    }

    public void setExStatus(String exStatus) {
        this.exStatus = exStatus == null ? null : exStatus.trim();
    }
}