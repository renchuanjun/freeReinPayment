package com.fuqin.model;

import java.util.List;

/**
 * Created by lenovo on 2017/8/18.
 */
public class MenuNode implements java.io.Serializable{

    private String id;
    private String resourceId;
    private Boolean isMenu;
    private String title;
    private String url;
    private String imageUrl;
    private String target;
    private String parentId;
    private String parentResourceId;
    private boolean fixed;
    private String mapClass;

    private boolean hasChildren;
    private List<MenuNode> childNodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Boolean getMenu() {
        return isMenu;
    }

    public void setMenu(Boolean menu) {
        isMenu = menu;
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

    public String getMapClass() {
        return mapClass;
    }

    public void setMapClass(String mapClass) {
        this.mapClass = mapClass;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public List<MenuNode> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<MenuNode> childNodes) {
        this.childNodes = childNodes;
    }
}
