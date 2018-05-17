package com.fuqin.system.model;

import java.util.Date;

public class SysOrgan implements java.io.Serializable{
    private Integer organId;

    private Integer parentId;

    private String organFullId;

    private String organCode;

    private String organName;

    private String organFullName;

    private Byte organLevel;

    private Byte orderBy;

    private String telphone;

    private String email;

    private String officeAddress;

    private String postalCode;

    private String faxphone;

    private String linkMan;

    private String description;

    private Byte foreBackType;

    private Byte isEnable;

    private Byte isDeleted;

    private String createBy;

    private Date createOn;

    private String createByName;

    private String updateBy;

    private Date updateOn;

    private String updateByName;

    private String platformId;

    private String dataPermissionId;

    public Integer getOrganId() {
        return organId;
    }

    public void setOrganId(Integer organId) {
        this.organId = organId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getOrganFullId() {
        return organFullId;
    }

    public void setOrganFullId(String organFullId) {
        this.organFullId = organFullId;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getOrganFullName() {
        return organFullName;
    }

    public void setOrganFullName(String organFullName) {
        this.organFullName = organFullName;
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

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getFaxphone() {
        return faxphone;
    }

    public void setFaxphone(String faxphone) {
        this.faxphone = faxphone;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getForeBackType() {
        return foreBackType;
    }

    public void setForeBackType(Byte foreBackType) {
        this.foreBackType = foreBackType;
    }

    public Byte getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Byte isEnable) {
        this.isEnable = isEnable;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }

    public String getUpdateByName() {
        return updateByName;
    }

    public void setUpdateByName(String updateByName) {
        this.updateByName = updateByName;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getDataPermissionId() {
        return dataPermissionId;
    }

    public void setDataPermissionId(String dataPermissionId) {
        this.dataPermissionId = dataPermissionId;
    }
}