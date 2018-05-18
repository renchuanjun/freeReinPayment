package org.open.model;

import java.util.List;

/**
 * Created by lenovo on 2017/8/2.
 */
public class JqueryTreeNode implements java.io.Serializable{

    //主键
    private String id;
    //显示名
    private String text;
    //节点值
    private String value;
    //是否显示复选框
    private boolean showcheck;
    //是否展开
    private boolean isexpand;
    //状态值（0：未选中，1：选中，2：有部分孩子选中）
    private int checkstate;
    //是否有孩子节点
    private boolean hasChildren;
    //孩子节点集
    private List<JqueryTreeNode> childNodes;
    //是否结束（一般是有孩子节点时为True，否则为False）
    private boolean complete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isShowcheck() {
        return showcheck;
    }

    public void setShowcheck(boolean showcheck) {
        this.showcheck = showcheck;
    }

    public boolean isIsexpand() {
        return isexpand;
    }

    public void setIsexpand(boolean isexpand) {
        this.isexpand = isexpand;
    }

    public int getCheckstate() {
        return checkstate;
    }

    public void setCheckstate(int checkstate) {
        this.checkstate = checkstate;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public List<JqueryTreeNode> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<JqueryTreeNode> childNodes) {
        this.childNodes = childNodes;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
