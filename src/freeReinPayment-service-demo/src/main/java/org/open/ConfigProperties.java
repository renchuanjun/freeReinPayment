package org.open;

import org.open.config.BaseConfigProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 任传君
 * @create 2018-05-23 11:56
 **/
@ConfigurationProperties(prefix="config")
public class ConfigProperties extends BaseConfigProperties {

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
