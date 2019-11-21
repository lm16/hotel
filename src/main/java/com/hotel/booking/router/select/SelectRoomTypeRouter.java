package com.hotel.booking.router.select;

import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.booking.router.BookingObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "条件选择-房型")
@RestController
@RequestMapping("/select/roomType")
public class SelectRoomTypeRouter extends BookingObject {


    @ApiOperation("显示房型，返回ID")
    @GetMapping
    public Result get(){
        return Result.build(ResultType.Success).appendData("data", roomTypeMapper.select());
    }

}
