package com.zhangkaigang.system.controller;

import com.github.pagehelper.PageInfo;
import com.zhangkaigang.base.pojo.page.LayuiPageFactory;
import com.zhangkaigang.base.pojo.page.LayuiPageInfo;
import com.zhangkaigang.system.pojo.dto.UserDTO;
import com.zhangkaigang.system.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        LayuiPageInfo layuiPageInfo = LayuiPageFactory.createPageInfo(pageInfo.getTotal(), pageInfo.getList());
        return layuiPageInfo;
    }

    /**
     * 添加用户页面
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return PRIFIX + "user_add";
    }


}
