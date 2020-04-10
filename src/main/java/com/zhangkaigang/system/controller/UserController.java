package com.zhangkaigang.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("")
    public String index () {
        return PRIFIX + "user";
    }
}
