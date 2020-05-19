package com.zhangkaigang.system.controller;

import com.zhangkaigang.base.constant.CacheFactory;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private CacheFactory cacheFactory;

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
    public LayuiPageInfo listTree(@RequestParam(value = "condition", required = false) String condition,
                                  @RequestParam(value = "levels", required = false) String levels) {
        List<AuthDTO> authDTOList = authService.listTree(condition, levels);
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
     * 获取ztree风格的权限树
     * @return
     */
    @RequestMapping("getAuthZTree")
    @ResponseBody
    @ApiOperation(value = "获取ztree风格的权限树", httpMethod = "GET")
    public List<ZTreeNode> getDeptZTree() {
        List<ZTreeNode> zTreeNodeList = authService.getAuthZTree();
        zTreeNodeList.add(ZTreeFactory.createRoot(false));
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

    /**
     * 编辑页面
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage() {
        return PRIFIX + "auth_edit";
    }

    /**
     * 根据权限id查询权限
     * @param authId
     * @return
     */
    @RequestMapping("/findByAuthId/{authId}")
    @ResponseBody
    public Result findByAuthId(@PathVariable("authId") Long authId) {
        AuthDTO authDTO = authService.findByAuthId(authId);
        authDTO.setParentName(cacheFactory.getAuthName(authDTO.getParentId()));
        return new Result(true, StatusCodeEnum.OK.getStatusCode(), authDTO);
    }

    /**
     * 编辑
     * @param authDTO
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    @ApiOperation(value = "编辑权限", httpMethod = "POST")
    public Result edit(AuthDTO authDTO) {
        authService.edit(authDTO);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }

    /**
     * 删除权限
     * @param authId
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam("authId") Long authId) {
        authService.delete(authId);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }
}
