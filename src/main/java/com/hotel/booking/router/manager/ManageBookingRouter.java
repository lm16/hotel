package com.hotel.booking.router.manager;

import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.booking.router.BookingObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname BookingForm
 * @Description TODO
 * @Date 19-10-29 上午9:33
 * @Created by lanmeng
 */
@RestController
@Api(tags="管理订房接口")
@RequestMapping("manage/booking")
public class ManageBookingRouter extends BookingObject {

//    @ApiOperation("当天转入住")
//    @GetMapping("/byName")
//    public Result getFromBooking(@RequestParam("name") String name){
//        return Result.build(ResultType.Success).appendData("data", bookingMapper.getByName(name));
//    }

    @RequiresPermissions(value = { "*:find" }, logical = Logical.OR)
    @ApiOperation("查看所有预订")
    @GetMapping
    public Result get(@RequestParam(value = "status", required = false) String status,
                      @RequestParam(value = "name", required = false) String name,
                      @RequestParam(value = "phone", required = false) String phone,
                      @RequestParam(value = "handle", required = false) Integer handle){

        Map<String, String> map = new HashMap<>(3);
        map.put("status", status);
        map.put("name", name);
        map.put("phone", phone);
        if(handle!=null){
            map.put("handle", handle.toString());
        }

        return Result.build(ResultType.Success).appendData("data", bookingMapper.getAll(map));
    }

    @ApiOperation("单条预订")
    @GetMapping("/{id}")
    public Result getId(@PathVariable("id") int id){
        return Result.build(ResultType.Success).appendData("data", bookingMapper.getOne(id));
    }
//    mfarvtltxlqqcacj

}
