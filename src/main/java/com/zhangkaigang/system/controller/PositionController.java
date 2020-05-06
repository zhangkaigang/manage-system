package com.zhangkaigang.system.controller;

import com.github.pagehelper.PageInfo;
import com.zhangkaigang.base.enums.StatusCodeEnum;
import com.zhangkaigang.base.pojo.common.Result;
import com.zhangkaigang.base.pojo.page.LayuiPageFactory;
import com.zhangkaigang.base.pojo.page.LayuiPageInfo;
import com.zhangkaigang.system.pojo.dto.PositionDTO;
import com.zhangkaigang.system.service.PositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/6
 * @Version:1.0
 */
@Controller
@RequestMapping("/sys/position")
public class PositionController {
    private static final String PRIFIX = "system/position/";

    @Autowired
    private PositionService positionService;

    /**
     * 部门管理页面
     * @return
     */
    @RequestMapping("")
    public String index () {
        return PRIFIX + "position";
    }

    /**
     * 部门列表
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public LayuiPageInfo list() {
        PageInfo<PositionDTO> pageInfo = positionService.list();
        LayuiPageInfo layuiPageInfo = LayuiPageFactory.createPageInfo(pageInfo.getTotal(), pageInfo.getList());
        return layuiPageInfo;
    }

    /**
     * 添加职位页面
     * @return
     */
    @RequestMapping("/addPositionPage")
    public String addPositionPage() {
        return PRIFIX + "position_add";
    }

    /**
     * 添加职位
     * @param positionDTO
     * @return
     */
    @RequestMapping("/addPosition")
    @ResponseBody
    @ApiOperation(value = "添加职位", httpMethod = "POST")
    public Result addPosition(PositionDTO positionDTO) {
        positionService.addPosition(positionDTO);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }


}
