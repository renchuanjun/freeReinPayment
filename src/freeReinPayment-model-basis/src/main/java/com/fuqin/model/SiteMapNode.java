package com.fuqin.model;


public class SiteMapNode implements java.io.Serializable {

	private String id;
	private String resourceID;
	private Boolean isMenu;
	private String title;
	private String url;
	private String imageUrl;
	private String target;
	private String parentId;
	private String parentResourceId;
	private boolean fixed;
	private String mapClass;
	
	public String getMapClass() {
		return mapClass;
	}
	public void setMapClass(String mapClass) {
		this.mapClass = mapClass;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResourceID() {
		return resourceID;
	}
	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}
	public Boolean getIsMenu() {
		return isMenu;
	}
	public void setIsMenu(Boolean isMenu) {
		this.isMenu = isMenu;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentResourceId() {
		return parentResourceId;
	}
	public void setParentResourceId(String parentResourceId) {
		this.parentResourceId = parentResourceId;
	}
	public boolean isFixed() {
		return fixed;
	}
	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}
	
}
