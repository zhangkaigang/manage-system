package com.zhangkaigang.system.controller;

import com.zhangkaigang.base.pojo.node.LayuiTreeFactory;
import com.zhangkaigang.base.pojo.node.LayuiTreeNode;
import com.zhangkaigang.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/4/14
 * @Version:1.0
 */
@Controller
@RequestMapping("/sys/dept")
public class DeptController {

    private static final String PRIFIX = "system/dept/";

    @Autowired
    private DeptService deptService;

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
    public List<LayuiTreeNode> getDeptLayuiTree() {
        List<LayuiTreeNode> layuiTreeNodeList = deptService.getDeptLayuiTree();
        layuiTreeNodeList.add(LayuiTreeFactory.createRoot());
        return layuiTreeNodeList;
    }

    /**
     * 添加部门页面
     * @return
     */
    @RequestMapping("/addDeptPage")
    public String addDeptPage() {
        return PRIFIX + "dept_add";
    }

    /**
     * 部门选择页面
     * @return
     */
    @RequestMapping("deptTreePage")
    public String deptTreePage() {
        return PRIFIX + "dept_tree";
    }
}
