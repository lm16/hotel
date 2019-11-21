package com.hotel.category.controller;

import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.category.bean.Desk;
import com.hotel.category.service.DeskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/10/17
 * modified: 2019/10/21
 * 功能：餐桌管理
 */
@Api(tags = "餐桌管理接口")
@RequestMapping(value = "json/desk")
@Controller
public class DeskController {

    @Autowired
    private DeskService deskService;

    @RequiresPermissions(value = { "*:add" }, logical = Logical.OR)
    @ApiOperation(value = "增加餐桌",notes = "已测试")
    @RequestMapping(value = "insertDesk",method = RequestMethod.POST)
    @ResponseBody
    public Result insertDesk(
            @ApiParam(name = "fdName",value = "桌号",required = true)
            @RequestParam(name = "fdName",required = true) String fdName,
            @ApiParam(name = "fdType",value = "桌子类型",required = true)
            @RequestParam(name = "fdType",required = true) String fdType,
            @ApiParam(name = "fr_id",value = "餐厅id",required = true)
            @RequestParam(name = "fr_id",required = true) Long fdRestaurantId,
            @ApiParam(name = "fdStatus",value = "桌子状态",required = true)
            @RequestParam(name = "fdStatus",required = true) Integer fdStatus) {

        Desk desk = new Desk();
        desk.setFdName(fdName);
        desk.setFdRestaurantId(fdRestaurantId);
        desk.setFdType(fdType);
        desk.setFdStatus(fdStatus);

        boolean flag = deskService.insertDesk(desk);

        return Result.build(ResultType.Success).appendData("data",flag);

    }

    @RequiresPermissions(value = { "*:find" }, logical = Logical.OR)
    @ApiOperation(value = "查看餐桌",notes = "已测试")
    @RequestMapping(value = "/selectDesk",method = RequestMethod.GET)
    @ResponseBody
    public Result selectDesk() {

        List<HashMap> deskList = deskService.selectDesk();

        return Result.build(ResultType.Success).appendData("data",deskList);

    }

    @RequiresPermissions(value = { "*:modify" }, logical = Logical.OR)
    @ApiOperation(value = "修改餐桌",notes = "已测试")
    @RequestMapping(value = "/updateDesk",method = RequestMethod.PUT)
    @ResponseBody
    public Result updateDesk(
            @ApiParam(name = "fdId",value = "餐桌id",required = true)
            @RequestParam(name = "fdId",required = true) Long fdId,
            @ApiParam(name = "fdName",value = "桌号",required = true)
            @RequestParam(name = "fdName",required = true) String fdName,
            @ApiParam(name = "fdType",value = "桌子类型",required = true)
            @RequestParam(name = "fdType",required = true) String fdType,
            @ApiParam(name = "fr_id",value = "餐厅id",required = true)
            @RequestParam(name = "fr_id",required = true) Long fdRestaurantId,
            @ApiParam(name = "fdStatus",value = "桌子状态",required = true)
            @RequestParam(name = "fdStatus",required = true) Integer fdStatus) {

        Desk desk = new Desk();
        desk.setFdId(fdId);
        desk.setFdName(fdName);
        desk.setFdRestaurantId(fdRestaurantId);
        desk.setFdType(fdType);
        desk.setFdStatus(fdStatus);


        boolean flag = deskService.updateDesk(desk);

        return Result.build(ResultType.Success).appendData("data",flag);

    }

    @RequiresPermissions(value = { "*:delete" }, logical = Logical.OR)
    @ApiOperation(value = "删除餐桌",notes = "已测试")
    @RequestMapping(value = "/deleteDesk",method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteDesk(
            @ApiParam(name = "fdId",value = "餐桌id",required = true)
            @RequestParam(name = "fdId") Long fdId) {

        boolean flag = deskService.deleteDesk(fdId);

        return Result.build(ResultType.Success).appendData("data",flag);

    }

    @ApiOperation(value = "查询所有的餐桌名称",notes = "已测试")
    @RequestMapping(value = "/selectDeskName",method = RequestMethod.GET)
    @ResponseBody
    public Result selectDeskName() {

        List<HashMap> deskNameList = deskService.selectDeskName();

        return Result.build(ResultType.Success).appendData("data",deskNameList);

    }

}
