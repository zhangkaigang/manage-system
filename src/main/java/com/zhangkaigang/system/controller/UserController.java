package com.zhangkaigang.system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.zhangkaigang.base.constant.CacheFactory;
import com.zhangkaigang.base.constant.CommonConstants;
import com.zhangkaigang.base.enums.StatusCodeEnum;
import com.zhangkaigang.base.pojo.common.Result;
import com.zhangkaigang.base.pojo.page.LayuiPageFactory;
import com.zhangkaigang.base.pojo.page.LayuiPageInfo;
import com.zhangkaigang.system.pojo.dto.UserDTO;
import com.zhangkaigang.system.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/4/10
 * @Version:1.0
 */
@Controller
@RequestMapping("/sys/user")
public class UserController {

    private static final String PRIFIX = "system/user/";

    @Autowired
    private UserService userService;

    @Autowired
    private CacheFactory cacheFactory;

    /**
     * 用户管理页面
     * @return
     */
    @RequestMapping("")
    public String index () {
        return PRIFIX + "user";
    }

    /**
     * 用户列表
     * @param condition
     * @param deptId
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    @ApiOperation(value = "用户列表", httpMethod = "GET")
    public LayuiPageInfo list(@RequestParam(value = "condition", required = false) String condition,
                              @RequestParam(value = "deptId", required = false) Long deptId) {
        PageInfo<UserDTO> pageInfo = userService.list(condition, deptId);
        List<UserDTO> userDTOList = pageInfo.getList();
        if(CollectionUtils.isNotEmpty(userDTOList)) {
            for (UserDTO userDTO : userDTOList) {
                userDTO.setDeptName(cacheFactory.getDeptName(userDTO.getDeptId()));
                userDTO.setPositionNames(cacheFactory.getPositionNames(userDTO.getUserId()));
            }
        }
        LayuiPageInfo layuiPageInfo = LayuiPageFactory.createPageInfo(pageInfo.getTotal(), userDTOList);
        return layuiPageInfo;
    }

    /**
     * 添加用户页面
     * @return
     */
    @RequestMapping("/addPage")
    @RequiresPermissions("user:add")
    public String addPage() {
        return PRIFIX + "user_add";
    }

    /**
     * 添加用户
     * @param userDTO
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加用户", httpMethod = "POST")
    public Result add(UserDTO userDTO) {
        // 判断登录名是否存在
        UserDTO existUserDTO = userService.findByLoginName(userDTO.getLoginName());
        if(existUserDTO != null) {
            return new Result(false,  StatusCodeEnum.ERROR.getStatusCode(), "登录名已存在");
        }
        userService.add(userDTO);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }

    /**
     * 删除用户
     * @param userIds
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("user:delete")
    public Result delete(@RequestParam("userIds") String userIds) {
        userService.delete(userIds);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }

    /**
     * 修改用户页面
     * @return
     */
    @RequestMapping("/editPage")
    @RequiresPermissions("user:edit")
    public String editPage() {
        return PRIFIX + "user_edit";
    }

    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     */
    @RequestMapping("/findByUserId/{userId}")
    @ResponseBody
    public Result findByUserId(@PathVariable("userId") Long userId) {
        UserDTO userDTO = userService.findByUserId(userId);
        userDTO.setDeptName(cacheFactory.getDeptName(userDTO.getDeptId()));
        return new Result(true, StatusCodeEnum.OK.getStatusCode(), userDTO);
    }

    /**
     * 编辑
     * @param userDTO
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    @ApiOperation(value = "编辑用户", httpMethod = "POST")
    public Result edit(UserDTO userDTO) {
        userService.edit(userDTO);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }

    /**
     * 改变状态
     * @param userId
     * @param status
     * @return
     */
    @RequestMapping("/changeStatus/{userId}/{status}")
    @ResponseBody
    @RequiresPermissions("user:changeStatus")
    public Result changeStatus(@PathVariable("userId") Long userId,
                               @PathVariable("status") String status
                            ) {
        UserDTO userDTO = userService.findByUserId(userId);
        userDTO.setStatus(status);
        userService.edit(userDTO);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }

    /**
     * 重置密码
     * @param userId
     * @return
     */
    @RequestMapping("/resetPwd/{userId}")
    @ResponseBody
    @RequiresPermissions("user:resetPwd")
    public Result resetPwd(@PathVariable("userId") Long userId) {
        UserDTO userDTO = userService.findByUserId(userId);
        String encodePwd = BCrypt.hashpw(CommonConstants.DEFAULT_PWD, BCrypt.gensalt());
        userDTO.setPassword(encodePwd);
        userService.edit(userDTO);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }

    /**
     * 分配角色页面
     * @return
     */
    @RequestMapping("/assignRolePage")
    @RequiresPermissions("user:assignRole")
    public String assignRolePage() {
        return PRIFIX + "user_assign_role";
    }

    /**
     * 获取分配角色穿梭框数据
     * @param userId
     * @return
     */
    @RequestMapping("/getAssignRoleData/{userId}")
    @ResponseBody
    public Result getAssignRoleData(@PathVariable("userId") Long userId) {
        Map<String, Object> resultMap = userService.getAssignRoleData(userId);
        return new Result(true, StatusCodeEnum.OK.getStatusCode(), resultMap);
    }

    /**
     * 分配角色
     * @param roleIds
     * @param userId
     * @return
     */
    @RequestMapping("/assignRole")
    @ResponseBody
    public Result assignRole(@RequestParam(value = "roleIds", required = false) String roleIds,
                             @RequestParam(value = "userId") Long userId) {
        userService.assignRole(roleIds, userId);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }

    @RequestMapping("/login")
    @ResponseBody
    public Object login() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "{\"code\":20000,\"data\":{\"token\":\"admin-token\"}}";
        Map map = mapper.readValue(jsonStr, Map.class);
        return map;
    }

    @RequestMapping("/info")
    @ResponseBody
    public Object info() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "{\"code\":20000,\"data\":{\"roles\":[\"admin\"],\"introduction\":\"I am a super administrator\",\"avatar\":\"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif\",\"name\":\"Super Admin\"}}";
        Map map = mapper.readValue(jsonStr, Map.class);
        return map;
    }

    @RequestMapping("/getClientList")
    @ResponseBody
    public Object getClientList() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "{\"code\":20000,\"data\":[{\"clientCode\":\"0001\", \"clientName\":\"测试客户\"}]}";
        Map map = mapper.readValue(jsonStr, Map.class);
        return map;
    }
}
