package org.open.model;

import java.util.List;



/***
 * 该类用于解决前端多登录的实体情况
 * @author lenovo
 *
 */
public class MyUser<T,K> implements java.io.Serializable{

	private String userId;
	
	private String userName;
	
	private String name;
	
	private Integer superUser;//默认为1  0超级管理员
	
	private T t;

	private K k;
	
	//一期切换首个路径
	private String url1;
	//二期切换首个路径
	private String url2;

	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public K getK() {
		return k;
	}

	public void setK(K k) {
		this.k = k;
	}

	public Integer getSuperUser() {
		return superUser;
	}

	public void setSuperUser(Integer superUser) {
		this.superUser = superUser;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
