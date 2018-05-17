package com.fuqin.system.model;

import java.util.Date;

public class SysUser implements java.io.Serializable{
    private String userId;

    private String userName;

    private String name;

    private Byte sex;

    private String password;

    private Date birthday;

    private String description;

    private String defaultHome;

    private String mobliePhone;

    private String telPhone;

    private String email;

    private String postTitle;

    private String organFullId;

    private String organId;

    private String identityCardId;

    private String cardId;

    private Byte orderBy;

    private String defaultLangage;

    private Integer passwordChangeDay;

    private Date passwordLastChangeDate;

    private String officPhone;

    private String officeAddress;

    private Date lastLoginTime;

    private Byte loginNum;

    private Byte superUser;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultHome() {
        return defaultHome;
    }

    public void setDefaultHome(String defaultHome) {
        this.defaultHome = defaultHome;
    }

    public String getMobliePhone() {
        return mobliePhone;
    }

    public void setMobliePhone(String mobliePhone) {
        this.mobliePhone = mobliePhone;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getOrganFullId() {
        return organFullId;
    }

    public void setOrganFullId(String organFullId) {
        this.organFullId = organFullId;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getIdentityCardId() {
        return identityCardId;
    }

    public void setIdentityCardId(String identityCardId) {
        this.identityCardId = identityCardId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Byte getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Byte orderBy) {
        this.orderBy = orderBy;
    }

    public String getDefaultLangage() {
        return defaultLangage;
    }

    public void setDefaultLangage(String defaultLangage) {
        this.defaultLangage = defaultLangage;
    }

    public Integer getPasswordChangeDay() {
		return passwordChangeDay;
	}

	public void setPasswordChangeDay(Integer passwordChangeDay) {
		this.passwordChangeDay = passwordChangeDay;
	}

	public Date getPasswordLastChangeDate() {
        return passwordLastChangeDate;
    }

    public void setPasswordLastChangeDate(Date passwordLastChangeDate) {
        this.passwordLastChangeDate = passwordLastChangeDate;
    }

    public String getOfficPhone() {
        return officPhone;
    }

    public void setOfficPhone(String officPhone) {
        this.officPhone = officPhone;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Byte getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Byte loginNum) {
        this.loginNum = loginNum;
    }

    public Byte getSuperUser() {
        return superUser;
    }

    public void setSuperUser(Byte superUser) {
        this.superUser = superUser;
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

    //~~~~~~~~~~~~~~
    private String organName;

    private String roleId;

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}