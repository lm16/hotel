package com.hotel.category.controller;

import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.category.bean.Information;
import com.hotel.category.service.InformationService;
import com.hotel.category.service.UploadInformationPictureService;
import com.hotel.image.bean.Image;
import com.hotel.utils.UploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/10/8
 * modified: 2019/10/31
 * 功能：菜品信息管理
 */
@Api(tags = "菜品信息接口")
@RequestMapping("/json/information")
@Controller
public class InformationController {

    @Autowired
    private InformationService informationService;
    @Autowired
    private UploadInformationPictureService uploadInformationPictureService;

    @RequiresPermissions(value = { "*:add" }, logical = Logical.OR)
    @ApiOperation(value = "增加菜品信息",notes = "正在测试")
    @RequestMapping(value = "/insertInformation",method = RequestMethod.POST)
    @ResponseBody
    public Result insertInformation(
            @ApiParam(value = "菜品图片",required = true) MultipartFile file,
            @ApiParam(name = "fc_id",value = "菜品种类id",required = true)
            @RequestParam(name = "fc_id") Long fiCategoryId,
            @ApiParam(name = "e_id",value = "厨师id",required = true)
            @RequestParam(name = "e_id") Long fiEmployeeId,
            @ApiParam(name = "fiName",value = "菜品名称",required = true)
            @RequestParam(name = "fiName") String fiName,
            @ApiParam(name = "fiPrice",value = "菜品价格",required = true)
            @RequestParam(name = "fiPrice") double fiPrice,
            @ApiParam(name = "fiDescription",value = "菜品描述",required = true)
            @RequestParam(name = "fiDescription") String fiDescription) {

        Information information  = new Information();
        information.setFiCategoryId(fiCategoryId);
        information.setFiEmployeeId(fiEmployeeId);
        information.setFiName(fiName);
        information.setFiPrice(fiPrice);
        information.setFiDescription(fiDescription);

        boolean flag1 = informationService.insertInformation(information);

        //上传图片,获得数据库保存图片路径
        String filePath = uploadInformationPictureService.uploadPicture(file);
        System.out.println("filePath=" + filePath);
        //图片路径保存到数据库
        Image image = new Image();
        //保存路径
        image.setUrl(filePath);
        //保存菜品id
        //获得菜品的自增id
        Long fiId = information.getFiId();
        image.setFoodId(fiId);
        //添加图片路径到数据表
        boolean flag = uploadInformationPictureService.insertInformationImages(image);
       // System.out.println("flag="+flag);

        return Result.build(ResultType.Success).appendData("data","增加成功，上传图片成功，保存路径成功");

    }

    /**
     * 删除菜品信息
     * @param fiId
     * @return
     */
    @RequiresPermissions(value = { "*:delete" }, logical = Logical.OR)
    @ApiOperation(value = "删除菜品信息",notes = "正在测试")
    @RequestMapping(value = "deleteInformation",method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteInformation(
            @ApiParam(name = "fiId",value = "菜品id",required = true)
            @RequestParam(name ="fiId") Long fiId) {

        //删除菜品信息
        boolean flag1 = informationService.deleteInformation(fiId);
        //获得图片路径
        String filePath = uploadInformationPictureService.selectInformationImagesPath(fiId);
        //删除图片
        boolean flag2 = uploadInformationPictureService.deleteItemImages(filePath);
        //删除图片路径
        boolean flag3= uploadInformationPictureService.deleteInformationImages(fiId);
        boolean flag =false;
        if( flag1 == flag2 == flag3) {
            flag =true;
        }

        return Result.build(ResultType.Success).appendData("data",flag);

    }

    @RequiresPermissions(value = { "*:modify" }, logical = Logical.OR)
    @ApiOperation(value = "修改菜品信息",notes = "正在测试")
    @RequestMapping(value = "/updateInformation",method = RequestMethod.PUT)
    @ResponseBody
    public Result updateInformation(
            @ApiParam(value = "菜品图片",required = false) MultipartFile file,
            @ApiParam(name = "fiId",value = "菜品id",required = true)
            @RequestParam(name = "fiId") Long fiId,
            @ApiParam(name = "fc_id",value = "菜品种类id",required = true)
            @RequestParam(name = "fc_id") Long fiCategoryId,
            @ApiParam(name = "e_id",value = "厨师id",required = true)
            @RequestParam(name = "e_id") Long fiEmployeeId,
            @ApiParam(name = "fiName",value = "菜品名称",required = true)
            @RequestParam(name = "fiName") String fiName,
            @ApiParam(name = "fiPrice",value = "菜品价格",required = true)
            @RequestParam(name = "fiPrice") double fiPrice,
            @ApiParam(name = "fiDescription",value = "菜品描述",required = true)
            @RequestParam(name = "fiDescription") String fiDescription) {

        Information information = new Information();
        information.setFiId(fiId);
        information.setFiCategoryId(fiCategoryId);
        information.setFiEmployeeId(fiEmployeeId);
        information.setFiName(fiName);
        information.setFiPrice(fiPrice);
        information.setFiDescription(fiDescription);

        String fileName = file.getOriginalFilename();

        if(!file.isEmpty()) {
            //获得原图片路径
            String filePath = uploadInformationPictureService.selectInformationImagesPath(fiId);

            //删除原图片
            boolean flag1 = uploadInformationPictureService.deleteItemImages(filePath);
            //删除原图片路径
            boolean flag2 = uploadInformationPictureService.deleteInformationImages(fiId);

            //上传图片,获得数据库保存新图片路径
            String newPath = uploadInformationPictureService.uploadPicture(file);
            //新图片路径保存到数据库
            Image image = new Image();
            //保存路径
            image.setUrl(newPath);
            image.setFoodId(fiId);
            //添加图片路径到数据表
            boolean flag3 = uploadInformationPictureService.insertInformationImages(image);
            if(flag1 == flag2 == flag3 ) {
                System.out.println("修改图片成功");
            }
        }

        boolean flag = informationService.updateInformation(information);

        return Result.build(ResultType.Success).appendData("data",flag);

    }

    @RequiresPermissions(value = { "*:find" }, logical = Logical.OR)
    @ApiOperation(value = "查看菜品信息",notes = "正在测试")
    @RequestMapping(value = "/selectInformation",method = RequestMethod.GET)
    @ResponseBody
    public Result selectInformation(HttpServletRequest request,
            @ApiParam(name = "fiName",value = "菜品名称",required = false)
            @RequestParam(name = "fiName",required = false) String fiName) throws UnknownHostException {

        //获得菜品信息以及图片路径
        List<HashMap> informationList = informationService.selectInformation(fiName);
        //图片路径转换为url
        List<HashMap> informationLists = uploadInformationPictureService.transformUrl(request,informationList);

        return Result.build(ResultType.Success).appendData("data",informationLists);

    }


    @ApiOperation(value = "查询餐饮部的厨师")
    @RequestMapping(value = "/selectInformationChef",method = RequestMethod.GET)
    @ResponseBody
    public Result selectInformationChef() {

        List<HashMap> informationChef = informationService.selectInformationChef();

        return Result.build(ResultType.Success).appendData("data",informationChef);

    }

}
