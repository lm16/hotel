package com.hotel.booking.router.manager;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.booking.bean.RoomType;
import com.hotel.booking.bean.RoomTypeImg;
import com.hotel.booking.mapper.RoomTypeMapper;
import com.hotel.booking.router.BookingObject;
import com.hotel.image.bean.Image;
import com.hotel.image.mapper.ImageMapper;
import com.hotel.image.service.ImageService;
import com.hotel.recreation.bean.Recreation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;



/**
 * @Classname BookingForm
 * @Description TODO
 * @Date 19-10-29 上午9:33
 * @Created by lanmeng
 */
@Api(tags = "管理房型接口")
@RestController
@RequestMapping("/manage/roomType")
public class RoomTypeRouter extends BookingObject {

//    @ApiOperation("显示房型")
//    @GetMapping
//    public Result get(){

//        PageHelper.startPage(1, 10);

//        List<RoomType> list = roomTypeMapper.getAll();

//        PageInfo pageInfo = new PageInfo(list);

//        return Result.build(ResultType.Success).appendData("data", pageInfo);
//        return Result.build(ResultType.Success).appendData("data", list);
//    }

    @RequiresPermissions(value = { "*:add" }, logical = Logical.OR)
    @ApiOperation("新增房型")
    @PostMapping
    public Result post(@RequestBody RoomType roomType){
        return Result.build(ResultType.Success).appendData("data", roomTypeServer.create(roomType));
    }

    @RequiresPermissions(value = { "*:modify" }, logical = Logical.OR)
    @ApiOperation("修改房型")
    @PutMapping
    public Result put(@RequestBody RoomType roomType){
        return Result.build(ResultType.Success).appendData("data", roomTypeServer.update(roomType));
    }

    @ApiOperation("删除房型")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") int id){
        return Result.build(ResultType.Success).appendData("data", roomTypeServer.delete(id));
    }



    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageMapper imageMapper;

    @ApiOperation(value = "图片")
    @PostMapping("/img")
    public Result img(MultipartFile file) {

        System.out.println("jjjjjjjjjjjjjjj");


        return Result.build(ResultType.Success).appendData("data", imageService.uploadPicture(file));

    }


}
//roomTypeMapper.img(url)