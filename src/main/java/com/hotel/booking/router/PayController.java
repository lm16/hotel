package com.hotel.booking.router;

import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "支付")
@RestController
@RequestMapping("/pay")
public class PayController {

    @ApiOperation("returnURL")
    @GetMapping
    public Result get(
            @RequestParam("seller_id") String sellerId
    ){
        System.out.println("有人来过");

        return Result.build(ResultType.Success).appendData("data", sellerId);
    }

    @ApiOperation("notifyURL")
    @PostMapping
    public Result post(@RequestBody Map map){

        System.out.println("有人来过233");
        System.out.println(map.size());

        return Result.build(ResultType.Success);
    }
}
