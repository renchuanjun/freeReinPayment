package com.fuqin.system.viewmodel;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by lenovo on 2017/8/7.
 */
public class OrganForm {

    private Integer organId;

    @NotBlank(message="不能为空")
    private String organName;

    private Byte organLevel;

    private Byte orderBy;

    @NotBlank(message="不能为空")
    private String organCode;

    private Integer parentId;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public Integer getOrganId() {
        return organId;
    }

    public void setOrganId(Integer organId) {
        this.organId = organId;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public Byte getOrganLevel() {
        return organLevel;
    }

    public void setOrganLevel(Byte organLevel) {
        this.organLevel = organLevel;
    }

    public Byte getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Byte orderBy) {
        this.orderBy = orderBy;
    }
}
