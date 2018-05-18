package org.open.system.model;

import java.util.Date;

public class SysRole implements java.io.Serializable{
        private String roleId;

        private String organId;

        private String groupId;

        private String roleName;

        private String organFullId;

        private String comments;

        private String roleUserMd5;

        private String roleDatapermissionMd5;

        private Byte foreBackType;

        private Byte isEnable;

        private Byte isDeleted;

        private String createBy;

        private Date createOn;

        private String createByName;

        private String updateBy;

        private Date updateOn;

        private String updateByName;

        private String platformId;

        private String dataPermissionId;

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getOrganId() {
            return organId;
        }

        public void setOrganId(String organId) {
            this.organId = organId;
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

        public String getOrganFullId() {
            return organFullId;
        }

        public void setOrganFullId(String organFullId) {
            this.organFullId = organFullId;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getRoleUserMd5() {
            return roleUserMd5;
        }

        public void setRoleUserMd5(String roleUserMd5) {
            this.roleUserMd5 = roleUserMd5;
        }

        public String getRoleDatapermissionMd5() {
            return roleDatapermissionMd5;
        }

        public void setRoleDatapermissionMd5(String roleDatapermissionMd5) {
            this.roleDatapermissionMd5 = roleDatapermissionMd5;
        }

        public Byte getForeBackType() {
            return foreBackType;
        }

        public void setForeBackType(Byte foreBackType) {
            this.foreBackType = foreBackType;
        }

        public Byte getIsEnable() {
            return isEnable;
        }

        public void setIsEnable(Byte isEnable) {
            this.isEnable = isEnable;
        }

        public Byte getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(Byte isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public Date getCreateOn() {
            return createOn;
        }

        public void setCreateOn(Date createOn) {
            this.createOn = createOn;
        }

        public String getCreateByName() {
            return createByName;
        }

        public void setCreateByName(String createByName) {
            this.createByName = createByName;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public Date getUpdateOn() {
            return updateOn;
        }

        public void setUpdateOn(Date updateOn) {
            this.updateOn = updateOn;
        }

        public String getUpdateByName() {
            return updateByName;
        }

        public void setUpdateByName(String updateByName) {
            this.updateByName = updateByName;
        }

        public String getPlatformId() {
            return platformId;
        }

        public void setPlatformId(String platformId) {
            this.platformId = platformId;
        }

        public String getDataPermissionId() {
            return dataPermissionId;
        }

        public void setDataPermissionId(String dataPermissionId) {
            this.dataPermissionId = dataPermissionId;
        }
    }