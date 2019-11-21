package com.hotel.booking.router.manager;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.booking.bean.RoomInfo;
import com.hotel.booking.bean.RoomType;
import com.hotel.booking.mapper.RoomMapper;
import com.hotel.booking.mapper.RoomTypeMapper;
import com.hotel.booking.router.BookingObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "管理房间接口")
@RestController
@RequestMapping("/manage/room")
public class RoomInfoRouter extends BookingObject {



    @ApiOperation("显示房间")
    @GetMapping
    public Result get(@RequestParam(value = "roomTypeId", required = false) Integer roomTypeId){

        PageHelper.startPage(1, 10);

        List<Map<String, Object>> list = roomMapper.getAll(roomTypeId);

        PageInfo pageInfo = new PageInfo(list);

        return Result.build(ResultType.Success).appendData("data", list);
    }

    @RequiresPermissions(value = { "*:add" }, logical = Logical.OR)
    @ApiOperation("新增房间")
    @PostMapping
    public Result post(@RequestBody RoomInfo roomInfo){
        return Result.build(ResultType.Success).appendData("data", roomServer.createRoomInfo(roomInfo));
    }

    @ApiOperation("删除房间")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") int id){
        return Result.build(ResultType.Success).appendData("data", roomMapper.delete(id));
    }

    @ApiOperation("修改房型")
    @PutMapping
    public Result put(@RequestBody RoomType roomType){
        return Result.build(ResultType.Success).appendData("data", roomTypeMapper.update(roomType));
    }
}