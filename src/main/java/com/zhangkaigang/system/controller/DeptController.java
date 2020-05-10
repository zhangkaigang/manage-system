package com.zhangkaigang.system.controller;

import com.github.pagehelper.PageInfo;
import com.zhangkaigang.base.constant.CacheFactory;
import com.zhangkaigang.base.enums.StatusCodeEnum;
import com.zhangkaigang.base.pojo.common.Result;
import com.zhangkaigang.base.pojo.node.LayuiTreeFactory;
import com.zhangkaigang.base.pojo.node.LayuiTreeNode;
import com.zhangkaigang.base.pojo.node.ZTreeFactory;
import com.zhangkaigang.base.pojo.node.ZTreeNode;
import com.zhangkaigang.base.pojo.page.LayuiPageFactory;
import com.zhangkaigang.base.pojo.page.LayuiPageInfo;
import com.zhangkaigang.system.pojo.dto.DeptDTO;
import com.zhangkaigang.system.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/4/14
 * @Version:1.0
 */
@Controller
@RequestMapping("/sys/dept")
@Api(tags = "部门接口")
public class DeptController {

    private static final String PRIFIX = "system/dept/";

    @Autowired
    private DeptService deptService;

    @Autowired
    private CacheFactory cacheFactory;

    /**
     * 部门管理页面
     * @return
     */
    @RequestMapping("")
    public String index () {
        return PRIFIX + "dept";
    }

    /**
     * 获取layui风格部门树
     * @return
     */
    @RequestMapping("/getDeptLayuiTree")
    @ResponseBody
    @ApiOperation(value = "获取layui风格部门树", httpMethod = "GET")
    public List<LayuiTreeNode> getDeptLayuiTree() {
        List<LayuiTreeNode> layuiTreeNodeList = deptService.getDeptLayuiTree();
        layuiTreeNodeList.add(LayuiTreeFactory.createRoot());
        return LayuiTreeFactory.getLayuiTree(layuiTreeNodeList);
    }

    /**
     * 部门列表
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    @ApiOperation(value = "部门列表", httpMethod = "GET")
    public LayuiPageInfo list(@RequestParam(value = "deptName", required = false) String deptName,
                              @RequestParam(value = "deptId", required = false) Long deptId) {
        PageInfo<DeptDTO> pageInfo = deptService.list(deptName, deptId);
        LayuiPageInfo layuiPageInfo = LayuiPageFactory.createPageInfo(pageInfo.getTotal(), pageInfo.getList());
        return layuiPageInfo;
    }


    /**
     * 添加部门页面
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return PRIFIX + "dept_add";
    }

    /**
     * 部门选择页面
     * @return
     */
    @RequestMapping("/deptTreePage")
    public String deptTreePage() {
        return PRIFIX + "dept_tree";
    }

    /**
     * 获取ztree风格的部门树
     * @return
     */
    @RequestMapping("getDeptZTree")
    @ResponseBody
    @ApiOperation(value = "获取ztree风格的部门树", httpMethod = "GET")
    public List<ZTreeNode> getDeptZTree() {
        List<ZTreeNode> zTreeNodeList = deptService.getDeptZTree();
        zTreeNodeList.add(ZTreeFactory.createRoot());
        return zTreeNodeList;
    }

    /**
     * 添加部门
     * @param deptDTO
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加部门", httpMethod = "POST")
    public Result add(DeptDTO deptDTO) {
        deptService.add(deptDTO);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }

    /**
     * 删除部门
     * @param deptId
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam("deptId") Long deptId) {
        deptService.delete(deptId);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }

    /**
     * 修改部门页面
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage() {
        return PRIFIX + "dept_edit";
    }

    /**
     * 根据部门id查询部门
     * @param deptId
     * @return
     */
    @RequestMapping("/findByDeptId/{deptId}")
    @ResponseBody
    public Result findByDeptId(@PathVariable("deptId") Long deptId) {
        DeptDTO deptDTO = deptService.findByDeptId(deptId);
        deptDTO.setpName(cacheFactory.getDeptName(deptDTO.getpId()));
        return new Result(true, StatusCodeEnum.OK.getStatusCode(), deptDTO);
    }

    /**
     * 编辑
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(DeptDTO deptDTO) {
        deptService.edit(deptDTO);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }
}
