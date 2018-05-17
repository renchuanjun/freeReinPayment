package com.fuqin.http.model;

/**
 * @author zhengluyao
 * @param 
 * sysCode 系统级code优先级最高--00000000 8 个0表示正常 除此之外， 系统不正常， 不进行业务处理 
 * sysMsg  系统信息描述
 * android 安卓版本信息
 * ios     IOS版本信息
 * accountStatus 用户账户状态
 * bankStatus 银行状态 是否维护中 可为对象，标识充值提现投资绑卡换卡接口状态
 */
public class ResponseHeaderEntity {

	private String sysCode = "";//系统级code优先级最高--00000000 8 个0表示正常 除此之外， 系统不正常， 不进行业务处理 
	
	private String sysMsg = "";//--系统信息描述

	private String android = "";//安卓版本信息
	
	private String ios = "";//IOS版本信息
	
	private String accountStatus = "";//用户账户状态
	
	private String bankStatus = "";//银行状态 是否维护中 可为对象，标识充值提现投资绑卡换卡接口状态

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getSysMsg() {
		return sysMsg;
	}

	public void setSysMsg(String sysMsg) {
		this.sysMsg = sysMsg;
	}

	public String getAndroid() {
		return android;
	}

	public void setAndroid(String android) {
		this.android = android;
	}

	public String getIos() {
		return ios;
	}

	public void setIos(String ios) {
		this.ios = ios;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getBankStatus() {
		return bankStatus;
	}

	public void setBankStatus(String bankStatus) {
		this.bankStatus = bankStatus;
	}

	@Override
	public String toString() {
		return "ResponseHeaderEntity [sysCode=" + sysCode + ", sysMsg="
				+ sysMsg + ", android=" + android + ", ios=" + ios
				+ ", accountStatus=" + accountStatus + ", bankStatus="
				+ bankStatus + "]";
	}
	
	

	

}
