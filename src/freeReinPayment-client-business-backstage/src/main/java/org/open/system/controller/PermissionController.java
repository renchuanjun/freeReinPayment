package org.open.system.controller;

import org.open.BaseController;
import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.open.model.JqueryTreeNode;
import org.open.model.SubFQResult;
import org.open.system.model.SysPermission;
import org.open.system.model.SysPermissionOperategroup;
import org.open.system.service.IPermissionService;
import org.open.utils.ClientUtils;
import org.open.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/8/10.
 */
@Controller
@RequestMapping("/system/permission")
public class PermissionController extends BaseController {

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private ClientUtils clientUtils;

    private Integer foreBackType = 0;//代表后台

    /**
     * 加载组织机构树
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loadpermissiontree", method = RequestMethod.POST)
    public String loadPermissionTree(){
        List<SysPermission> list = new ArrayList<>();
        List<SysPermissionOperategroup> list1 = new ArrayList<>();
        List<JqueryTreeNode> listTree = new ArrayList<>();
        String groupId = request.getParameter("groupId");
        FQParam2<String,Integer> fqParam2 = new FQParam2<>();
        fqParam2.setT(groupId);
        fqParam2.setK(foreBackType);
        FQResult<SubFQResult<List<SysPermission>,List<SysPermissionOperategroup>>> hnaResult = this.permissionService.getPermissionByAll(fqParam2);
        if (hnaResult.getSuccess()){
        	SubFQResult<List<SysPermission>,List<SysPermissionOperategroup>> subHNAResult = hnaResult.getResult();
            if (subHNAResult.getSuccess()){
                list = subHNAResult.getResult();
                list1 = subHNAResult.getUserData();
            }
        }
        if (null == list || list.size() == 0) return "[]";
        List<SysPermission> listRoot = clientUtils.getSubTList(list,"permissionParentId","-1");
        if (null == listRoot || listRoot.size() == 0) return "[]";
        SysPermission root = listRoot.get(0);
        //树的根节点/当前点击的选中的节点
        JqueryTreeNode treeNode = createNode(root);
        treeNode.setComplete(true);
        treeNode.setHasChildren(true);
        if (null == list1 || list1.size() == 0){
            treeNode.setCheckstate(0);
        }else{
            List<SysPermissionOperategroup> childs1 = clientUtils.getSubTList(list1,"rowId",treeNode.getValue());
            if (null != childs1 && childs1.size() > 0){
                SysPermissionOperategroup temp = childs1.get(0);
                treeNode.setCheckstate(temp.getCheckStatus());
            }
            else{
                treeNode.setCheckstate(0);
            }
        }
        listTree.add(treeNode);
        if (StringUtils.isEmpty(groupId)){
            //全新加载
            createPermissionTree(treeNode,list);
        }
        else{
            //当前点击选中向下加载
            editPermissionTree(treeNode,list,list1);
        }
        String json = JsonUtils.SerializeJsonByList(listTree);
        //System.out.println(json);
        return json;
    }
    /***
     * 创建树节点对象
     * @param permission
     * @return
     */
    private JqueryTreeNode createNode(SysPermission permission){
        JqueryTreeNode treeNode = new JqueryTreeNode();
        treeNode.setId(permission.getPermissionId());
        treeNode.setText(permission.getPermissionName());
        treeNode.setValue(permission.getRowId());
        treeNode.setIsexpand(true);
        treeNode.setShowcheck(true);
        return treeNode;
    }

    /**
     * 创建树
     * @param rootNode
     * @param list
     */
    private void createPermissionTree(JqueryTreeNode rootNode,List<SysPermission> list){

        if (null == rootNode || null == list || list.size() == 0) return;

        List<SysPermission> childs = clientUtils.getSubTList(list,"permissionParentId",rootNode.getId());

        if (null == childs || childs.size() == 0) return;

        rootNode.setHasChildren(true);
        rootNode.setComplete(true);

        for (SysPermission permission: childs) {
            JqueryTreeNode chlid = createNode(permission);
            chlid.setCheckstate(0);
            if (null == rootNode.getChildNodes())
                rootNode.setChildNodes(new ArrayList<>());

            rootNode.getChildNodes().add(chlid);
            createPermissionTree(chlid,list);
        }
    }

    private void editPermissionTree(JqueryTreeNode rootNode,List<SysPermission> list,List<SysPermissionOperategroup> list1){

        if (null == rootNode || null == list || list.size() == 0) return;

        List<SysPermission> childs = clientUtils.getSubTList(list,"permissionParentId",rootNode.getId());

        if (null == childs || childs.size() == 0) return;

        rootNode.setHasChildren(true);
        rootNode.setComplete(true);

        for (SysPermission permission: childs) {
            JqueryTreeNode chlid = createNode(permission);
            if (null != list1 || list1.size() > 0) {
                List<SysPermissionOperategroup> childs1 = clientUtils.getSubTList(list1, "rowId", permission.getRowId());
                if (null != childs1 && childs1.size() > 0) {
                    SysPermissionOperategroup temp = childs1.get(0);
                    chlid.setCheckstate(temp.getCheckStatus());
                } else {
                    chlid.setCheckstate(0);
                }
            }
            else{
                chlid.setCheckstate(0);
            }
            if (null == rootNode.getChildNodes())
                rootNode.setChildNodes(new ArrayList<>());

            rootNode.getChildNodes().add(chlid);
            editPermissionTree(chlid,list,list1);
        }
    }
}
