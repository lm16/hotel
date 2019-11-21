package com.hotel.client.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.client.dao.Integral;
import com.hotel.client.service.IntegralService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Api(tags = "折扣接口")
@Controller
public class IntegralController {

    @Autowired
    private IntegralService integralService;

    @RequiresPermissions(value = { "*:find" }, logical = Logical.OR)
    @ApiOperation(value = "查询所有", notes = "无传参，返回list")
    @GetMapping("/Integral/findAll")
    @ResponseBody
    public Result selectAll() throws IOException {
        PageHelper.startPage(1,2);
        List<Integral> card = integralService.findAll();
        PageInfo pageInfo = new PageInfo(card);
        return  Result.build(ResultType.Success).appendData("data",pageInfo);
    }

    @RequiresPermissions(value = { "*:add" }, logical = Logical.OR)
    @ApiOperation(value = "添加折扣信息", notes = "无传参，返回list")
    @ApiImplicitParam(name = "integral", value = "折扣实体类", required = true, dataType = "Integral")
    @PostMapping("/Integral/SaveIntergral")
    @ResponseBody
    public Result SaveClient(@RequestBody(required = false) Integral integral){
       Integral clt = integralService.Integralsave(integral);
        if (clt==null){
            return Result.build(ResultType.Failed);
        }
        return  Result.build(ResultType.Success).appendData("data",clt);
    }

    @RequiresPermissions(value = { "*:modify" }, logical = Logical.OR)
    @ApiOperation(value = "修改折扣信息", notes = "无传参，返回list")
    @ApiImplicitParam(name = "integral", value = "折扣实体类", required = true, dataType = "Integral")
    @PostMapping("/Integral/updateIntergral")
    @ResponseBody
    public Result Intergralupdate(@RequestBody(required = false) Integral integral){
        Integral clt = integralService.Integralupdate(integral);
        if (clt==null){
            return Result.build(ResultType.Failed);
        }
        return  Result.build(ResultType.Success).appendData("data",clt);
    }

    @RequiresPermissions(value = { "*:delete" }, logical = Logical.OR)
    @ApiOperation(value = "删除信息", notes = "根据传入的id删除")
    @DeleteMapping("/Integral/deleteIntegral/{id}")
    @ResponseBody
    public Result Integrsldelate(@PathVariable Integer id){
        integralService.Integrsldelete(id);
        return  Result.build(ResultType.Success);
    }

}
