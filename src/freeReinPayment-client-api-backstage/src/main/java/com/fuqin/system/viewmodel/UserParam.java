package com.fuqin.system.viewmodel;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UserParam {
	
	private String userId;
	
	private String userName;
	
	private String cName;
	
	private String userpwd;
	
	private Byte sex;
	
	private String mobliePhone;

    private String email;
    
    private String organId;
    
    private Byte superUser;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createOn;
    
	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

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

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public String getMobliePhone() {
		return mobliePhone;
	}

	public void setMobliePhone(String mobliePhone) {
		this.mobliePhone = mobliePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Byte getSuperUser() {
		return superUser;
	}

	public void setSuperUser(Byte superUser) {
		this.superUser = superUser;
	}
    
    
}
