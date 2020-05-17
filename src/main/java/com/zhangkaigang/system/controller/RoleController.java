package com.zhangkaigang.system.controller;

import com.github.pagehelper.PageInfo;
import com.zhangkaigang.base.enums.StatusCodeEnum;
import com.zhangkaigang.base.pojo.common.Result;
import com.zhangkaigang.base.pojo.page.LayuiPageFactory;
import com.zhangkaigang.base.pojo.page.LayuiPageInfo;
import com.zhangkaigang.system.pojo.dto.RoleDTO;
import com.zhangkaigang.system.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/17
 * @Version:1.0
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController {

    private static final String PRIFIX = "system/role/";

    @Autowired
    private RoleService roleService;

    /**
     * 角色管理页面
     * @return
     */
    @RequestMapping("")
    public String index () {
        return PRIFIX + "role";
    }

    /**
     * 角色列表
     * @param condition
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public LayuiPageInfo list(@RequestParam(value = "condition", required = false) String condition) {
        PageInfo<RoleDTO> pageInfo = roleService.list(condition);
        LayuiPageInfo layuiPageInfo = LayuiPageFactory.createPageInfo(pageInfo.getTotal(), pageInfo.getList());
        return layuiPageInfo;
    }

    /**
     * 添加角色页面
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return PRIFIX + "role_add";
    }

    /**
     * 添加角色
     * @param roleDTO
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加角色", httpMethod = "POST")
    public Result add(RoleDTO roleDTO) {
        // 查询角色编码是否存在
        RoleDTO existRoleDTO = roleService.findByRoleCode(roleDTO.getRoleCode());
        if(existRoleDTO != null) {
            return new Result(false, StatusCodeEnum.ERROR.getStatusCode(), "该角色编码已存在");
        }
        roleService.add(roleDTO);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }

    /**
     * 删除
     * @param roleIds
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam(value = "roleIds") String roleIds){
        roleService.delete(roleIds);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }

    /**
     * 编辑页面
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage() {
        return PRIFIX + "role_edit";
    }

    /**
     * 编辑
     * @param roleDTO
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    @ApiOperation(value = "编辑角色", httpMethod = "POST")
    public Result edit(RoleDTO roleDTO) {
        roleService.edit(roleDTO);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }

    /**
     * 根据角色id查询角色
     * @param roleId
     * @return
     */
    @RequestMapping("/findByRoleId/{roleId}")
    @ResponseBody
    public Result findByRoleId(@PathVariable("roleId") Long roleId) {
        RoleDTO roleDTO = roleService.findByRoleId(roleId);
        return new Result(true, StatusCodeEnum.OK.getStatusCode(), roleDTO);
    }

}
