package org.open.system.viewmodel;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by lenovo on 2017/8/9.
 */
public class OperategroupForm {

    private String groupId;

    @NotBlank(message="不能为空")
    private String groupName;

    private String groupDescription;

    /***
     * 全选状态id
     */
    private String permissionIds;

    /***
     * 半选状态id
     */
    private String hkPermissionIds;

    public String getHkPermissionIds() {
        return hkPermissionIds;
    }

    public void setHkPermissionIds(String hkPermissionIds) {
        this.hkPermissionIds = hkPermissionIds;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }
}
