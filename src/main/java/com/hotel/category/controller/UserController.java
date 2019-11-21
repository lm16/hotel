package com.hotel.category.controller;

/**
 * @author 林晓锋
 * @date 2019/9/29
 * modified: 2019/9/29
 * 功能：
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.category.bean.Users;
import com.hotel.category.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ApiOperation：用在请求的方法上，说明方法的用途、作用 value="说明方法的用途、作用"
 * notes="方法的备注说明"
 * @ApiImplicitParams：用在请求的方法上，表示一组参数说明
 * @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面 name：参数名
 * value：参数的汉字说明、解释
 * required：参数是否必须传
 * paramType：参数放在哪个地方
 * · header --> 请求参数的获取：@RequestHeader
 * · query --> 请求参数的获取：@RequestParam
 * · path（用于restful接口）--> 请求参数的获取：@PathVariable
 * · body（不常用）
 * · form（不常用）
 * dataType：参数类型，默认String，其它值dataType="Integer"
 * defaultValue：参数的默认值
 * @ApiResponses：用在请求的方法上，表示一组响应
 * @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息 code：数字，例如400
 * message：信息，例如"请求参数没填好"
 * response：抛出异常的类
 * @ApiModel：用于响应类上，表示一个返回响应数据的信息 （这种一般用在post创建的时候，使用@RequestBody这样的场景，
 * 请求参数无法使用@ApiImplicitParam注解进行描述的时候）
 * @ApiModelProperty：用在属性上，描述响应类的属性
 */
@Api(tags = "Swagger测试")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value ="查询用户信息接口", notes = "查询学生信息")
    @GetMapping("/findAll")
    @ResponseBody
    public Result findAll() {

        //System.out.println("no problem");
        //开始分页
        PageHelper.startPage(1,2);

        List<Users> usersList = userService.findAll();
        //得到分页数据
        PageInfo pageInfo = new PageInfo(usersList);

        return Result.build(ResultType.Success).appendData("data",pageInfo);

    }
}
