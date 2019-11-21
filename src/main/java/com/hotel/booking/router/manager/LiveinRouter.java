package com.hotel.booking.router.manager;

import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.booking.bean.Livein;
import com.hotel.booking.bean.livein.LiveinPart;
import com.hotel.booking.router.BookingObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @Classname BookingForm
 * @Description TODO
 * @Date 19-10-29 上午9:33
 * @Created by lanmeng
 */
@Api(tags = "管理入住接口")
@RestController
@RequestMapping("/manage/livein")
public class LiveinRouter extends BookingObject {

    @ApiOperation("房间状态")
    @GetMapping
    public Result get(@RequestParam(value = "roomTypeId", required = false) Integer roomTypeId,
                      @RequestParam(value = "status", required = false) Integer status){

        return Result.build(ResultType.Success).appendData("data", roomMapper.getAllorGroup(roomTypeId, status));
    }

//    @ApiOperation("当天房间")
//    @GetMapping
//    public Result get(@RequestParam(value = "roomTypeId", required = false) Integer roomTypeId){
//
//        return Result.build(ResultType.Success).appendData("data", roomMapper.getAll(roomTypeId));
//    }

//    @PostMapping
//    public Result post(Livein livein){
//        return Result.build()
//    }



    @ApiOperation("确定信息")
    @PostMapping
    public Result post(@RequestBody LiveinPart liveinPart){

        if(roomMapper.livein(liveinPart)!=1){
            return Result.build(ResultType.Failed);
        }
        if(bookingMapper.livein(liveinPart.getBookingId())!=1){
            return Result.build(ResultType.Failed);
        }

        int result = liveinMapper.create(liveinPart);

        Map<String, Object> map = new HashMap<>();
        map.put("liveinId", liveinPart.getId());
        for(String item: liveinPart.getLivewith()){
            map.put("identity", item);
            liveinMapper.livewith(map);
        }


        return Result.build(ResultType.Success).appendData("data", result);
    }

    @ApiOperation("入住历史")
    public Result get(){



        return Result.build(ResultType.Success);
    }

    @ApiOperation("手动清理过往房")
    @GetMapping("/clear")
    public Result clear(){
        return Result.build(ResultType.Success).appendData("data", liveinMapper.leave());
    }


    @ApiOperation("手动更新当日退房")
    @GetMapping("/end")
    public Result end(){
            return Result.build(ResultType.Success).appendData("data", liveinMapper.end());
    }

    @ApiOperation("员工操作当日离房")
    @GetMapping("/checkOut")
    public Result checkOut(@RequestParam("bookingId") Integer bookingId){
        return Result.build(ResultType.Success).appendData("data", liveinServer.checkOut(bookingId));
    }

    @ApiOperation("员工操作清洁完毕")
    @GetMapping("/cleanUp")
    public Result cleanUp(@RequestParam("roomId") Integer roomId){
        return Result.build(ResultType.Success).appendData("data", roomMapper.cleanUp(roomId));
    }
//    @ApiOperation("更新入住剩余天数")
//    @GetMapping("/countdown")
//    public Result countdown(){
//
//        roomMapper.countdownSelect();
//
//        return Result.build(ResultType.Success).appendData("data", roomMapper.countdownUpdate());
//    }
}
