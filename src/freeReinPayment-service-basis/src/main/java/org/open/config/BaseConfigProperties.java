package org.open.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by lenovo on 2017/4/27.
 */
@ConfigurationProperties(prefix="config")
public class BaseConfigProperties {

	private String keyPrefix;

    private boolean rsa;

    private boolean login;

    private boolean jdbcAES;

    private boolean validateCSRF;

    private boolean filter;

    private boolean filterChar;

    private String inputFilter;

    private String inputFilterChar;

    private String authorityAllowUrl;

    private String authorityExcludeUrl;

    private String securityAllowUrl;

    private String securityExcludeUrl;

    private String csrfAllowUrl;

    private String csrfExcludeUrl;

    private String requestNameKey;

    private String requestLikeNameKey;
    
	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public boolean isValidateCSRF() {
		return validateCSRF;
	}

	public void setValidateCSRF(boolean validateCSRF) {
		this.validateCSRF = validateCSRF;
	}

	public boolean isFilter() {
		return filter;
	}

	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	public boolean isFilterChar() {
		return filterChar;
	}

	public void setFilterChar(boolean filterChar) {
		this.filterChar = filterChar;
	}

	public String getInputFilter() {
		return inputFilter;
	}

	public void setInputFilter(String inputFilter) {
		this.inputFilter = inputFilter;
	}

	public String getInputFilterChar() {
		return inputFilterChar;
	}

	public void setInputFilterChar(String inputFilterChar) {
		this.inputFilterChar = inputFilterChar;
	}

	public String getRequestNameKey() {
		return requestNameKey;
	}

	public void setRequestNameKey(String requestNameKey) {
		this.requestNameKey = requestNameKey;
	}

	public String getRequestLikeNameKey() {
		return requestLikeNameKey;
	}

	public void setRequestLikeNameKey(String requestLikeNameKey) {
		this.requestLikeNameKey = requestLikeNameKey;
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	public boolean isRsa() {
		return rsa;
	}

	public void setRsa(boolean rsa) {
		this.rsa = rsa;
	}

	public boolean isJdbcAES() {
		return jdbcAES;
	}

	public void setJdbcAES(boolean jdbcAES) {
		this.jdbcAES = jdbcAES;
	}

	public String getAuthorityAllowUrl() {
		return authorityAllowUrl;
	}

	public void setAuthorityAllowUrl(String authorityAllowUrl) {
		this.authorityAllowUrl = authorityAllowUrl;
	}

	public String getAuthorityExcludeUrl() {
		return authorityExcludeUrl;
	}

	public void setAuthorityExcludeUrl(String authorityExcludeUrl) {
		this.authorityExcludeUrl = authorityExcludeUrl;
	}

	public String getSecurityAllowUrl() {
		return securityAllowUrl;
	}

	public void setSecurityAllowUrl(String securityAllowUrl) {
		this.securityAllowUrl = securityAllowUrl;
	}

	public String getSecurityExcludeUrl() {
		return securityExcludeUrl;
	}

	public void setSecurityExcludeUrl(String securityExcludeUrl) {
		this.securityExcludeUrl = securityExcludeUrl;
	}

	public String getCsrfAllowUrl() {
		return csrfAllowUrl;
	}

	public void setCsrfAllowUrl(String csrfAllowUrl) {
		this.csrfAllowUrl = csrfAllowUrl;
	}

	public String getCsrfExcludeUrl() {
		return csrfExcludeUrl;
	}

	public void setCsrfExcludeUrl(String csrfExcludeUrl) {
		this.csrfExcludeUrl = csrfExcludeUrl;
	}
	
	
}
