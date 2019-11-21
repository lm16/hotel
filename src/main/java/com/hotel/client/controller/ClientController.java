package com.hotel.client.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.client.dao.Client;
import com.hotel.client.excel.ExcelUtil;
import com.hotel.client.service.ClientService;
import com.hotel.recreation.bean.Recreation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import kotlin.jvm.JvmSuppressWildcards;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Api(tags = "客户接口")
@Controller
public class ClientController {

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
      @Autowired
      private ClientService clientService;

    @RequiresPermissions(value = { "*:find" }, logical = Logical.OR)
    @ApiOperation(value = "查询所有客户", notes = "无传参，返回list")
    @GetMapping("/Client/findAll")
    @ResponseBody
    public Result selectAll() throws IOException {
        PageHelper.startPage(1,2);
        List<Client> card = clientService.findAll();
        PageInfo pageInfo = new PageInfo(card);
        return  Result.build(ResultType.Success).appendData("data",pageInfo);
    }

    @ApiOperation(value = "查询客户", notes = "传id参")
    @ApiImplicitParam(name = "id", value = "客户", required = true, dataType = "int", paramType ="path" )
    @GetMapping("/Client/findclient/{id}")
    @ResponseBody
    public Result findclient(@PathVariable Long id){
      Client clt= clientService.findClientById(id);
        if (clt==null){
            return Result.build(ResultType.Failed);
        }
        return  Result.build(ResultType.Success).appendData("data",clt);
    }

    @RequiresPermissions(value = { "*:add" }, logical = Logical.OR)
    @ApiOperation(value = "添加客户信息", notes = "无传参，返回list")
    @ApiImplicitParam(name = "client", value = "客户实体类", required = true, dataType = "Client")
    @PostMapping("/Client/SaveClient")
    @ResponseBody
    public Result SaveClient(@RequestBody(required = false) Client client){
        Client clt = clientService.ClientSave(client);
        if (clt==null){
            return Result.build(ResultType.Failed);
        }
        return  Result.build(ResultType.Success).appendData("data",clt);
    }

    @RequiresPermissions(value = { "*:modify" }, logical = Logical.OR)
    @ApiOperation(value = "更新客户信息", notes = "无传参，返回list")
    @ApiImplicitParam(name = "client", value = "客户实体类", required = true, dataType = "Client")
    @PostMapping("/Client/ClientUpdate")
    @ResponseBody
    public Result ClientUpdate(@RequestBody(required = false) Client client){
        Client clt = clientService.ClientUpdate(client);
        if (clt==null){
            return Result.build(ResultType.Failed);
        }
        return  Result.build(ResultType.Success).appendData("data",clt);
    }

    @RequiresPermissions(value = { "*:delete" }, logical = Logical.OR)
    @ApiOperation(value = "删除客户信息", notes = "根据传入的id删除")
    @ApiImplicitParam(name = "id", value = "客户", required = true, dataType = "int", paramType ="path" )
    @PutMapping("/Client/Clientdelate/{id}")
    @ResponseBody
    public Result Clientdelate(@PathVariable Long id){
       clientService.deleteByIds(id);
        return  Result.build(ResultType.Success);
    }

    @RequiresPermissions(value = { "*:find" }, logical = Logical.OR)
    @ApiOperation(value = "根据条件查询客户信息", notes = "根据传入的条件{cardnum}{phone}{valid}查询，传实体")
    @ApiImplicitParam(name = "client", value = "客户实体类", required = true, dataType = "Client")
    @PostMapping("/Client/Clientsearch")
    @ResponseBody
    public Result Clientsearch(@RequestBody(required = false) Client client){
        PageHelper.startPage(1,2);
        List<Client> card = clientService.findbysome(client);
        PageInfo pageInfo = new PageInfo(card);
        return  Result.build(ResultType.Success).appendData("data",pageInfo);
    }

    @ApiOperation(value = "导出" ,notes = "")
    @GetMapping("/Client/Clientexcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        try {
            String name = "演示导出模板.xlsx";
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat sdf_ymd = new SimpleDateFormat("yyyyMMddHHmmss");
            String formatDate_ymd = sdf_ymd.format(date);
            // 设置文件名
            String fileName = formatDate_ymd + name;
            String sheetName = "数据展示";

            // 按条件筛选records
            List<Client> list = clientService.findAll();
            // easyexcel工具类实现Excel文件导出
            ExcelUtil.writeExcel(response, list, fileName, sheetName, new Client());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    }
