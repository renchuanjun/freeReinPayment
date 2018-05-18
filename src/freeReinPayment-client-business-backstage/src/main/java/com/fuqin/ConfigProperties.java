package com.fuqin;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.fuqin.config.BaseConfigProperties;

/**
 * Created by lenovo on 2017/4/17.
 */
@ConfigurationProperties(prefix="config")
public class ConfigProperties extends BaseConfigProperties {

    private boolean encrypt;

    private boolean siteMapNode;
    
    private boolean buttonNode;
    
    private boolean xmlValidating;

    private boolean permission;
    
    private String aesKey;
    
	public String getAesKey() {
		return aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}

	public boolean isPermission() {
		return permission;
	}

	public void setPermission(boolean permission) {
		this.permission = permission;
	}

	public boolean isSiteMapNode() {
		return siteMapNode;
	}

	public void setSiteMapNode(boolean siteMapNode) {
		this.siteMapNode = siteMapNode;
	}

	public boolean isButtonNode() {
		return buttonNode;
	}

	public void setButtonNode(boolean buttonNode) {
		this.buttonNode = buttonNode;
	}

	public boolean isXmlValidating() {
		return xmlValidating;
	}

	public void setXmlValidating(boolean xmlValidating) {
		this.xmlValidating = xmlValidating;
	}

	public boolean getEncrypt() {
		return encrypt;
	}

	public void setEncrypt(boolean encrypt) {
		this.encrypt = encrypt;
	}

    
}
