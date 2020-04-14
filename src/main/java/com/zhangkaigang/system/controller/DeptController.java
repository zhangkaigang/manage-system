package com.zhangkaigang.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("")
    public String index () {
        return PRIFIX + "dept";
    }
}
