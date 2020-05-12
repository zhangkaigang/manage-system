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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * 职位管理页面
     * @return
     */
    @RequestMapping("")
    public String index () {
        return PRIFIX + "position";
    }

    /**
     * 职位列表
     * @param name
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public LayuiPageInfo list(@RequestParam(value = "name", required = false) String name) {
        PageInfo<PositionDTO> pageInfo = positionService.list(name);
        LayuiPageInfo layuiPageInfo = LayuiPageFactory.createPageInfo(pageInfo.getTotal(), pageInfo.getList());
        return layuiPageInfo;
    }

    /**
     * 添加职位页面
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return PRIFIX + "position_add";
    }

    /**
     * 添加职位
     * @param positionDTO
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加职位", httpMethod = "POST")
    public Result add(PositionDTO positionDTO) {
        positionService.add(positionDTO);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }

    /**
     * 删除
     * @param positionIds
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam(value = "positionIds", required = false) String positionIds){
        positionService.delete(positionIds);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }

    /**
     * 编辑页面
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage() {
        return PRIFIX + "position_edit";
    }

    /**
     * 编辑
     * @param positionDTO
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    @ApiOperation(value = "编辑职位", httpMethod = "POST")
    public Result edit(PositionDTO positionDTO) {
        positionService.edit(positionDTO);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }

    /**
     * 根据部门id查询职位
     * @param positionId
     * @return
     */
    @RequestMapping("/findByPositionId/{positionId}")
    @ResponseBody
    public Result findByPositionId(@PathVariable("positionId") Long positionId) {
        PositionDTO positionDTO = positionService.findByPositionId(positionId);
        return new Result(true, StatusCodeEnum.OK.getStatusCode(), positionDTO);
    }

    /**
     * 改变状态
     * @param positionId
     * @param status
     * @return
     */
    @RequestMapping("/changeStatus/{positionId}/{status}")
    @ResponseBody
    public Result changeStatus(@PathVariable("positionId") Long positionId,
                               @PathVariable("status") String status
                               ) {
        PositionDTO positionDTO = positionService.findByPositionId(positionId);
        positionDTO.setStatus(status);
        positionService.edit(positionDTO);
        return new Result(true, StatusCodeEnum.OK.getStatusCode());
    }

}
