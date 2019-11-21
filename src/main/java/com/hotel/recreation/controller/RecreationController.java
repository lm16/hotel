package com.hotel.recreation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.category.service.UploadInformationPictureService;
import com.hotel.image.bean.Image;
import com.hotel.recreation.bean.Recreation;
import com.hotel.recreation.mapper.RecreationMapper;
import com.hotel.recreation.service.RecreationService;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Api(tags = "娱乐项目接口")
@Controller
//@RequiresAuthentication
public class RecreationController {

    @Autowired
    private RecreationService recreationService;
    @Autowired
    private RecreationMapper recreationMapper;

    @Autowired
    private UploadInformationPictureService uploadInformationPictureService;

    /** @ApiOperation：用在请求的方法上，说明方法的用途、作用 value="说明方法的用途、作用"
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

//    @RequestMapping("/hello")
//    public String Test1(){
//        Recreation recreation = recreationService.findRecreationById(1);
//        System.out.println(recreation.toString());
//        return "success";
//    }


//    @RequiresPermissions(value = { "*:find" }, logical = Logical.OR)
    @ApiOperation(value = "查询所有娱乐项目", notes = "无传参，返回list")
    @GetMapping("/Recreation/find/findAll")
    @ResponseBody
    public Result selectAll(HttpServletRequest request) throws IOException {
        List<Recreation> recreations = recreationService.findAll();
        //修改image url
        List<Recreation> list = recreationService.modifyImageUrl(recreations,request);
        return  Result.build(ResultType.Success).appendData("data",list);
    }

    @ApiOperation(value = "查询一个娱乐项目", notes = "根据娱乐项目id查询一个")
    @ApiImplicitParam(name = "reid", value = "娱乐项目id", dataType = "Long", paramType = "path")
    @GetMapping("/Recreation/find/findOneRecreation/{reid}")
    @ResponseBody
    public Result findOneRecreation(@PathVariable Long reid,HttpServletRequest request) throws IOException {
        Recreation recreation = recreationMapper.findById(reid).get();
        if(recreation==null){
            return Result.build(ResultType.Failed);
        }
        List<Recreation> recreations = new  ArrayList<Recreation>();
        recreations.add(recreation);
        List<Recreation> list = recreationService.modifyImageUrl(recreations,request);
        return  Result.build(ResultType.Success).appendData("data",list.get(0));
    }

    @RequiresPermissions(value = { "*:add" }, logical = Logical.OR)
    @ApiOperation(value = "添加娱乐项目", notes = "添加娱乐项目")
    @ApiImplicitParam(name = "recreation", value = "项目实体类", required = true, dataType = "Recreation")
    @PostMapping("/Recreation/add/createRecreation")
    @ResponseBody
    public Result createRecreation(@RequestBody(required = false) Recreation recreation){
        Recreation re = recreationService.createRecreation(recreation);
        if (re==null){
            return Result.build(ResultType.Failed);
        }
        return  Result.build(ResultType.Success).appendData("data",re);
    }


    @RequiresPermissions(value = { "*:delete" }, logical = Logical.OR)
    @ApiOperation(value = "删除娱乐项目", notes = "根据传入的娱乐项目id删除项目")
    @ApiImplicitParam(name = "4id", value = "项目", required = true, dataType = "Long", paramType ="path" )
    @DeleteMapping("/Recreation/delete/deleteRecreationById/{id}")
    @ResponseBody
    public Result deleteRecreation(@PathVariable Long id){
        recreationService.deleteRecreationById(id);
        return  Result.build(ResultType.Success);
    }

    @RequiresPermissions(value = { "*:modify" }, logical = Logical.OR)
    @ApiOperation(value = "更新娱乐项目", notes = "根据传入的娱乐项目信息更新")
    @ApiImplicitParam(name = "recreation", value = "娱乐项目实体类", required = true, dataType = "Recreation" )
    @PutMapping("/Recreation/modify/updateRecreation")
    @ResponseBody
    public Result updateRecreation(@RequestBody Recreation recreation){
        System.out.println(recreation);
        int re = recreationMapper.updateRecreation (recreation);
        if (re<0){
            return Result.build(ResultType.Failed);
        }
        return  Result.build(ResultType.Success).appendData("data",re);
    }

    @RequiresPermissions(value = { "*:find" }, logical = Logical.OR)
    @ApiOperation(value = "查询所有娱乐项目", notes = "根据项目名称模糊 和负责人名称模糊 查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rname", value = "项目名称", dataType = "String", required = true, defaultValue = "null", paramType = "path"),
            @ApiImplicitParam(name = "ename", value = "负责人名称", dataType = "String", required = true, defaultValue = "null", paramType = "path")
    })
    @GetMapping("/Recreation/find/findRecreationsByRnameandEmployeename/{rname}/{ename}")
    @ResponseBody
    public Result findRecreationsByRnameandEmployeename(@PathVariable String rname, @PathVariable String ename,HttpServletRequest request) throws IOException {
        System.out.println("rname==="+rname+"===ename===="+ename);
        if (rname==null || rname==""){
            rname ="null";
        }
        if (ename==null || ename.equals("")){
            ename = "null";
        }
//        System.out.println("rname==="+rname+"===ename===="+ename);
        List<Recreation> recreations = recreationService.findRecreationsByRnameandEmployeename(rname,ename);
        List<Recreation> list = recreationService.modifyImageUrl(recreations,request);
//        PageInfo pageInfo = new PageInfo(recreations);
        return  Result.build(ResultType.Success).appendData("data",list);
    }


}
