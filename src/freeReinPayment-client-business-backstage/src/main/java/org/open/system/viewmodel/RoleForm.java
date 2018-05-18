package org.open.system.viewmodel;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by lenovo on 2017/8/11.
 */
public class RoleForm {

    private String roleId;

    private String groupId;

    @NotBlank(message="不能为空")
    private String roleName;

    private String comments;

    private String operateGroupData;

    private String dataPermissionData;

    private String userData;

    public String getOperateGroupData() {
        return operateGroupData;
    }

    public void setOperateGroupData(String operateGroupData) {
        this.operateGroupData = operateGroupData;
    }

    public String getDataPermissionData() {
        return dataPermissionData;
    }

    public void setDataPermissionData(String dataPermissionData) {
        this.dataPermissionData = dataPermissionData;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
