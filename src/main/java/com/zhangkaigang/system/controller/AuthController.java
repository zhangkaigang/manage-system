package com.zhangkaigang.system.controller;

import com.zhangkaigang.base.enums.StatusCodeEnum;
import com.zhangkaigang.base.pojo.common.Result;
import com.zhangkaigang.base.pojo.node.ZTreeFactory;
import com.zhangkaigang.base.pojo.node.ZTreeNode;
import com.zhangkaigang.base.pojo.page.LayuiPageInfo;
import com.zhangkaigang.system.pojo.dto.AuthDTO;
import com.zhangkaigang.system.service.AuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/12
 * @Version:1.0
 */
@Controller
@RequestMapping("/sys/auth")
public class AuthController {

    private static final String PRIFIX = "system/auth/";

    @Autowired
    private AuthService authService;

    /**
     * 权限管理页面
     * @return
     */
    @RequestMapping("")
    public String index () {
        return PRIFIX + "auth";
    }

    /**
     * 权限列表，不分页
     * @return
     */
    @RequestMapping("/listTree")
    @ResponseBody
    public LayuiPageInfo listTree() {
        List<AuthDTO> authDTOList = authService.listTree();
        LayuiPageInfo layuiPageInfo = new LayuiPageInfo();
        layuiPageInfo.setData(authDTOList);
        return layuiPageInfo;
    }

    /**
     * 新增页面
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return PRIFIX + "auth_add";
    }

    /**
     * 权限树选择页面
     * @return
     */
    @RequestMapping("/authTreePage")
    public String deptTreePage() {
        return PRIFIX + "auth_tree";
    }

    /**
     * 获取ztree风格的部门树
     * @return
     */
    @RequestMapping("getAuthZTree")
    @ResponseBody
    @ApiOperation(value = "获取ztree风格的权限树", httpMethod = "GET")
    public List<ZTreeNode> getDeptZTree() {
        List<ZTreeNode> zTreeNodeList = authService.getAuthZTree();
        zTreeNodeList.add(ZTreeFactory.createRoot());
        return zTreeNodeList;
    }


    /**
     * 新增
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result add(AuthDTO authDTO) {
        authService.add(authDTO);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }
}
