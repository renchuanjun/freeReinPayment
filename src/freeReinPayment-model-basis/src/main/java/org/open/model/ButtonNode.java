package org.open.model;

public class ButtonNode implements java.io.Serializable {

	private String id;
	private String title;
	private String btnId;
	private String resourceId;
	private String fixed;
	private String enable;
	private String onClientClick;
	
	private String key;
	private String pageTitle;
	private String pageResourceId;
	private String groupId;
	private String groupTitle;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBtnId() {
		return btnId;
	}
	public void setBtnId(String btnId) {
		this.btnId = btnId;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getFixed() {
		return fixed;
	}
	public void setFixed(String fixed) {
		this.fixed = fixed;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getOnClientClick() {
		return onClientClick;
	}
	public void setOnClientClick(String onClientClick) {
		this.onClientClick = onClientClick;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public String getPageResourceId() {
		return pageResourceId;
	}
	public void setPageResourceId(String pageResourceId) {
		this.pageResourceId = pageResourceId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupTitle() {
		return groupTitle;
	}
	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}
	
	
}
