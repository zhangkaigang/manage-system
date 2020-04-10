package com.zhangkaigang.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/4/10
 * @Version:1.0
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index () {
        return "index";
    }
}
