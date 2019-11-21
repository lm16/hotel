package com.hotel.category.controller;

import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.category.bean.Restaurant;
import com.hotel.category.service.RestaurantService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 林晓锋
 * @date 2019/10/17
 * modified: 2019/10/18
 * 功能：餐厅管理
 */
@Api(tags = "餐厅信息接口")
@RequestMapping("/json/restaurant")
@Controller
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @RequiresPermissions(value = { "*:add" }, logical = Logical.OR)
    @ApiOperation(value = "增加餐厅",notes = "已测试")
    @RequestMapping(value = "/insertRestaurant",method = RequestMethod.POST)
    @ResponseBody
    public Result insertRestaurant(
            @ApiParam(name = "frName",value = "餐厅名称",required = true)
            @RequestParam(name = "frName") String frName,
            @ApiParam(name = "frAddress",value = "餐厅位置",required = true)
            @RequestParam(name ="frAddress") String frAddress,
            @ApiParam(name = "frHours",value = "营业时间",required = true)
            @RequestParam(name = "frHours") String frHours,
            @ApiParam(name = "frStatus",value = "状态",required = true)
            @RequestParam(name = "frStatus") Integer frStatus) {

        Restaurant restaurant = new Restaurant();
        restaurant.setFrName(frName);
        restaurant.setFrAddress(frAddress);
        restaurant.setFrHours(frHours);
        restaurant.setFrStatus(frStatus);

        boolean flag  = restaurantService.insertRestaurant(restaurant);

        return Result.build(ResultType.Success).appendData("data","增加餐厅成功");

    }

    @RequiresPermissions(value = { "*:find" }, logical = Logical.OR)
    @ApiOperation(value = "查看餐厅",notes = "已测试")
    @RequestMapping(value = "/selectRestaurant",method = RequestMethod.GET)
    @ResponseBody
    public Result selectRestaurant() {

        List<Restaurant> restaurantList = restaurantService.selectRestaurant();

        return Result.build(ResultType.Success).appendData("data",restaurantList);

    }

    @RequiresPermissions(value = { "*:delete" }, logical = Logical.OR)
    @RequestMapping(value = "/deleteRestaurant",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除餐厅",notes = "已测试")
    @ResponseBody
    public Result deleteRestaurant(
            @ApiParam(name = "frId",value = "餐厅id",required = true)
            @RequestParam(name = "frId") Long frId) {

        boolean flag = restaurantService.deleteRestaurant(frId);

        return Result.build(ResultType.Success).appendData("data",flag);

    }

    @RequiresPermissions(value = { "*:modify" }, logical = Logical.OR)
    @ApiOperation(value = "修改餐厅",notes = "已测试")
    @RequestMapping(value = "/updateRestaurant",method = RequestMethod.PUT)
    @ResponseBody
    public Result updateRestaurant(
            @ApiParam(name = "frId",value = "餐厅id",required = true)
            @RequestParam(name = "frId") Long frId,
            @ApiParam(name = "frName",value = "餐厅名称",required = true)
            @RequestParam(name = "frName") String frName,
            @ApiParam(name = "frAddress",value = "餐厅位置",required = true)
            @RequestParam(name = "frAddress") String frAddress,
            @ApiParam(name = "frHours",value = "营业时间",required = true)
            @RequestParam(name = "frHours") String frHours,
            @ApiParam(name ="frStatus",value = "状态",required = true)
            @RequestParam(name = "frStatus") Integer frStatus) {

        Restaurant restaurant = new Restaurant();
        restaurant.setFrId(frId);
        restaurant.setFrName(frName);
        restaurant.setFrAddress(frAddress);
        restaurant.setFrHours(frHours);
        restaurant.setFrStatus(frStatus);
        boolean flag = restaurantService.updateRestaurant(restaurant);

        return Result.build(ResultType.Success).appendData("data",flag);

    }

//    @RequiresPermissions(value = { "*:find" }, logical = Logical.OR)
    @ApiOperation(value = "查看某餐厅的餐桌",notes = "已测试")
    @RequestMapping(value = "/selectRestaurantDesk",method = RequestMethod.GET)
    @ResponseBody
    public Result selectRestaurantDesk(
            @ApiParam(name = "frId",value = "餐厅id",required = true)
            @RequestParam(value = "frId") Long frId,
            @ApiParam(name = "fdName",value = "餐桌名称",required = false)
            @RequestParam(value = "fdName",required = false) String fdName){

        List<HashMap> deskLists = restaurantService.selectRestaurantDesk(frId);

        //查询餐桌名称
        List<HashMap> deskList = new ArrayList<>();
        if(fdName!=null&&fdName!="") {

            Pattern pattern = Pattern.compile(fdName);
            for ( HashMap hashMap:deskLists ) {

                Matcher matcher = pattern.matcher((String)hashMap.get("fd_name"));
                if (matcher.find()) {

                    deskList.add(hashMap);
                }
            }
        }else {
            deskList.addAll(deskLists);
        }


        return Result.build(ResultType.Success).appendData("data",deskList);

    }


    @ApiOperation(value = "查看所有餐厅名称",notes = "正在测试")
    @RequestMapping(value = "/selectRestaurantName",method = RequestMethod.GET)
    @ResponseBody
    public Result selectRestaurantName() {

        List<HashMap> restaurantNameList = restaurantService.selectRestaurantName();

        return Result.build(ResultType.Success).appendData("data",restaurantNameList);

    }

}
