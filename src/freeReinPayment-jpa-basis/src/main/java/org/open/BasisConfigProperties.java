package org.open;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by lenovo on 2017/4/17.
 */
@ConfigurationProperties(prefix="config")
public class BasisConfigProperties {

    private boolean rsa;
    private boolean rsaTimestamp;
    private long rsaTimeOutTimestamp;
	private boolean rsaSign;

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

	
}
