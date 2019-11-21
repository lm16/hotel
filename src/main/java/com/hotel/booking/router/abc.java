package com.hotel.booking.router;


import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname abc
 * @Description TODO
 * @Date 19-11-1 下午3:40
 * @Created by lanmeng
 */
@Api("abc")
@RestController
//@RequestMapping("/")
public class abc {

    @ApiOperation("fa")
    @GetMapping("/abc")
    public Result abc(){
        return Result.build(ResultType.Success).appendData("data", "abc");
    }
}
