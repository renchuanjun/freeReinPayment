package com.fuqin;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by lenovo on 2017/4/17.
 */
@ConfigurationProperties(prefix="config")
public class ConfigProperties {

    private boolean rsa;

    private boolean rsaTimestamp;

    private long rsaTimeOutTimestamp;

	private boolean rsaSign;

	private String publicKey;

    private boolean jdbcAES;
    private boolean sendEmail;
  	private String emailUrl;
  	private String method;
  	private String appSecret;
  	private String accessToken;
  	private String fromUser;
  	private String userName;
  	private String userPwd;
	public boolean isRsa() {
		return rsa;
	}
	public void setRsa(boolean rsa) {
		this.rsa = rsa;
	}
	public boolean isRsaTimestamp() {
		return rsaTimestamp;
	}
	public void setRsaTimestamp(boolean rsaTimestamp) {
		this.rsaTimestamp = rsaTimestamp;
	}
	public long getRsaTimeOutTimestamp() {
		return rsaTimeOutTimestamp;
	}
	public void setRsaTimeOutTimestamp(long rsaTimeOutTimestamp) {
		this.rsaTimeOutTimestamp = rsaTimeOutTimestamp;
	}
	public boolean isRsaSign() {
		return rsaSign;
	}
	public void setRsaSign(boolean rsaSign) {
		this.rsaSign = rsaSign;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public boolean isJdbcAES() {
		return jdbcAES;
	}
	public void setJdbcAES(boolean jdbcAES) {
		this.jdbcAES = jdbcAES;
	}
	public boolean isSendEmail() {
		return sendEmail;
	}
	public void setSendEmail(boolean sendEmail) {
		this.sendEmail = sendEmail;
	}
	public String getEmailUrl() {
		return emailUrl;
	}
	public void setEmailUrl(String emailUrl) {
		this.emailUrl = emailUrl;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
}
