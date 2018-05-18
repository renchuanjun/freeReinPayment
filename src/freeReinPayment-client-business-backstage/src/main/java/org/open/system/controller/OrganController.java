package org.open.system.controller;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.open.BaseController;
import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.open.model.JqueryTreeNode;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.model.SysOrgan;
import org.open.system.service.IOrganService;
import org.open.system.viewmodel.OrganForm;
import org.open.system.viewmodel.OrganParam;
import org.open.utils.ClientUtils;
import org.open.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2017/8/1.
 */
@Controller
@RequestMapping("/system/organ")
public class OrganController  extends BaseController {

    @Autowired
    private IOrganService organService;
    
    @Autowired
    private ClientUtils clientUtils;

    private Integer foreBackType = 0;//代表后台
    /***
     * 加载列表页面
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {

        return "system/sysorgan/organlist";
    }

    /**
     * 加载组织机构树
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loadorgantree", method = RequestMethod.POST)
    public String loadOrganTree(){
        List<SysOrgan> list = new ArrayList<>();
        List<JqueryTreeNode> listTree = new ArrayList<>();
        FQResult<List<SysOrgan>> hnaResult = this.organService.getOrganByAll(foreBackType);
        if (hnaResult.getSuccess()){
            list = hnaResult.getResult();
        }
        //获取需要刷新的节点树
        String id= super.request.getParameter("id");
        int organId = StringUtils.isEmpty(id) ? 1 : Integer.valueOf(id);
        List<SysOrgan> listRoot = clientUtils.getSubTList(list,"organId",organId);
        if (null == listRoot || listRoot.size() == 0) return "[]";
        SysOrgan root = listRoot.get(0);
        //树的根节点/当前点击的选中的节点
        JqueryTreeNode treeNode = createNode(root);
        treeNode.setComplete(true);
        treeNode.setHasChildren(true);
        treeNode.setCheckstate(0);
        if (1 == organId){
            //全新加载
            listTree.add(treeNode);
            createOrganTree(treeNode,list);
        }
        else{
            //当前点击选中向下加载
            getChildOrg(treeNode,list,listTree);
        }
        String json = JsonUtils.SerializeJsonByList(listTree);
        return json;
    }

    /**
     * 加载分页数据
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listjson", method = RequestMethod.POST)
    public String organListJson(@ModelAttribute("param") OrganParam param){
        SysOrgan organ = new SysOrgan();
        organ.setOrganName(param.getOrganName());
        organ.setForeBackType(foreBackType.byteValue());
        if (!StringUtils.isEmpty(param.getParentId())){
            organ.setParentId(Integer.valueOf(param.getParentId()));
        }
        PagerAndOrderByArgs args = clientUtils.getPagerAndOrderByArgs(super.request);
        FQParam2<SysOrgan,PagerAndOrderByArgs> fqParam = new FQParam2<>();
        fqParam.setT(organ);
        fqParam.setK(args);
        FQResult<PaginationSupport<SysOrgan>> fqResult =  this.organService.getSysOrganList(fqParam);
        String dataJson = super.SetTableDataJson(0, "[]");
        if (fqResult.getSuccess()){
            PaginationSupport<SysOrgan> paginationSupport = fqResult.getResult();
            List<SysOrgan> list = paginationSupport.getItems();
            int totalCount = paginationSupport.getTotalCount();
            String json = JsonUtils.SerializeJsonByList(list);
            dataJson = super.SetTableDataJson(totalCount,json);
        }
        return dataJson;
    }
    /***
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/create/{parentId}", method = RequestMethod.GET)
    public String organCreate(Model model,@PathVariable Integer parentId) {
        OrganForm form = new OrganForm();
        form.setParentId(parentId);
        model.addAttribute("form", form);
        return "system/sysorgan/organedit";
    }

    /**
     * 编辑
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String organEdit(Model model,@PathVariable Integer id) {
        OrganForm form=new OrganForm();
        FQResult<SysOrgan> fqResult = this.organService.getSysOrgan(id);
        if (fqResult.getSuccess()){
            SysOrgan organ = fqResult.getResult();
            form = this.modelToForm(organ);
        }
        model.addAttribute("form", form);
        return "system/sysorgan/organedit";
    }

    /**
     * 提交
     *
     * @return
     */
    @ResponseBody()
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String organSubmit(@ModelAttribute("form") @Valid OrganForm form, BindingResult result) {
//        if (result.hasErrors()) return super.formValidate(result);
        SysOrgan organ = this.formToModel(form);
        FQResult<Object> fqResult = this.organService.saveSysOrgan(organ);
        if (fqResult.getSuccess()){
            return super.outJsonStringSuccess();
        }
        else{
            return super.outJsonStringFail();
        }

    }

    /***
     * 删除(假删除)
     * @param ids
     * @return
     */
    @ResponseBody()
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String organDelete(String ids) {
        List<SysOrgan> list = new ArrayList<>();
        String[] items = ids.split("\\_");
        for (String item:items) {
            SysOrgan organ = new SysOrgan();
            organ.setOrganId(Integer.valueOf(item));
            organ.setIsDeleted(Byte.valueOf("1"));
            String createBy = clientUtils.getCurrentUserId(request);
            String createByName = clientUtils.getCurrentName(request);
            Date curDate = Calendar.getInstance().getTime();
            organ.setUpdateBy(createBy);
            organ.setUpdateOn(curDate);
            organ.setUpdateByName(createByName);
            list.add(organ);
        }
        FQResult<Object> fqResult = this.organService.deleteSysOrgan(list);
        if (fqResult.getSuccess()){
            return super.outJsonStringSuccess();
        }else{
            if (StringUtils.isEmpty(fqResult.getDetailInfo())){
                return super.outJsonStringFail();
            }
            else{
                return super.outJsonStringFail(fqResult.getDetailInfo());
            }
        }
    }

    /***
     * viewmodel转model
     * @param form
     * @return
     */
    private SysOrgan formToModel(OrganForm form){
        // 创建对象
        SysOrgan organ = new SysOrgan();
        organ.setOrganId(form.getOrganId());
        organ.setOrganCode(form.getOrganCode());
        organ.setOrganLevel(form.getOrganLevel());
        organ.setOrderBy(form.getOrderBy());
        organ.setOrganName(form.getOrganName());
        organ.setForeBackType(foreBackType.byteValue());
        organ.setParentId(form.getParentId());
        /******必写代码********/
        String createBy = clientUtils.getCurrentUserId(request);
        String createByName = clientUtils.getCurrentName(request);
        Date curDate = Calendar.getInstance().getTime();
        //(主键为空则需要createon)
        if (null == organ.getOrganId()) {
            organ.setCreateBy(createBy);
            organ.setCreateOn(curDate);
            organ.setCreateByName(createByName);
        }
        organ.setUpdateBy(createBy);
        organ.setUpdateOn(curDate);
        organ.setUpdateByName(createByName);

        return organ;
    }

    /***
     * model转viewmodel
     * @param organ
     * @return
     */
    private OrganForm modelToForm(SysOrgan organ){
        OrganForm form = new OrganForm();
        form.setOrganId(organ.getOrganId());
        form.setOrganName(organ.getOrganName());
        form.setOrderBy(organ.getOrderBy());
        form.setOrganLevel(organ.getOrganLevel());
        form.setOrganCode(organ.getOrganCode());
        return form;
    }

    /***
     * 创建树节点对象
     * @param sysOrgan
     * @return
     */
    private JqueryTreeNode createNode(SysOrgan sysOrgan){
        JqueryTreeNode treeNode = new JqueryTreeNode();
        treeNode.setId(sysOrgan.getOrganId().toString());
        treeNode.setText(sysOrgan.getOrganName());
        treeNode.setValue(sysOrgan.getOrganCode());
        if (-1 == sysOrgan.getParentId()){
            treeNode.setIsexpand(true);
        }
        else{
            treeNode.setIsexpand(false);
        }
        treeNode.setShowcheck(false);
        return treeNode;
    }

    /***
     * 创建全量的组织机构树
     * @param rootNode
     * @param list
     */
    private void createOrganTree(JqueryTreeNode rootNode,List<SysOrgan> list){

        if (null == rootNode || null == list || list.size() == 0) return;

        List<SysOrgan> childs = clientUtils.getSubTList(list,"parentId",Integer.valueOf(rootNode.getId()));

        if (null == childs || childs.size() == 0) return;

        rootNode.setHasChildren(true);
        rootNode.setComplete(true);

        for (SysOrgan sysOrgan: childs) {
            JqueryTreeNode chlid = createNode(sysOrgan);
            chlid.setCheckstate(0);
            if (null == rootNode.getChildNodes())
                rootNode.setChildNodes(new ArrayList<>());
            rootNode.getChildNodes().add(chlid);
            createOrganTree(chlid,list);
        }
    }

    /***
     * 根据点击选中的节点加载组织机构树
     * @param rootNode
     * @param list
     * @param jlist
     */
    private void getChildOrg(JqueryTreeNode rootNode,List<SysOrgan> list,List<JqueryTreeNode> jlist){
        if (null == rootNode || null == list || null == jlist) return;
        List<SysOrgan> childs = clientUtils.getSubTList(list,"parentId",Integer.valueOf(rootNode.getId()));
        if (null == childs || childs.size() == 0) return;
        for (SysOrgan sysOrgan: childs) {
            JqueryTreeNode chlid = createNode(sysOrgan);
            chlid.setCheckstate(0);
            jlist.add(chlid);
            List<SysOrgan> childss = clientUtils.getSubTList(list,"parentId",Integer.valueOf(chlid.getId()));
            if (null != childss && childss.size() > 0){
                createOrganTree(chlid,list);
            }
        }
    }
}
