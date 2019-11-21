package com.hotel.category.controller;

import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.category.bean.Users;
import com.hotel.category.service.UploadInformationPictureService;
import com.hotel.image.bean.Image;
import com.hotel.utils.UploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @author 林晓锋
 * @date 2019/10/14
 * modified: 2019/10/31
 * 功能：菜品图片接口
 */
@Api(tags = "菜品图片接口")
@RequestMapping("json/uploadInformationPictureController")
@Controller
public class UploadInformationPictureController {

    @Autowired
    private UploadInformationPictureService uploadInformationPictureService;

    @ApiOperation(value = "菜品图片上传",notes = "已测试")
    @RequestMapping(value = "/uploadPicture",method = RequestMethod.POST)
    @ResponseBody
    public Result uploadPicture(@ApiParam(value = "菜品图片",required = true) MultipartFile file,
                                @ApiParam(name = "fiId", value = "菜品id",required = true)
                                @RequestParam(value = "fiId") Long fiId) {

        //上传图片,获得数据库保存图片路径
        String filePath = uploadInformationPictureService.uploadPicture(file);
//        System.out.println("filePath=" + filePath);
        //图片路径保存到数据库
        Image image = new Image();
        //保存路径
        image.setUrl(filePath);
        //保存菜品id
        image.setFoodId(fiId);
        //添加图片路径到数据表
        boolean flag = uploadInformationPictureService.insertInformationImages(image);
//        System.out.println("flag="+flag);

        return Result.build(ResultType.Success).appendData("data","上传成功，保存路径成功");

    }

    @ApiOperation(value = "菜品图片修改",notes = "正在测试")
    @RequestMapping(value = "/updatePicture",method = RequestMethod.POST)
    @ResponseBody
    public Result updatePicture(HttpServletRequest request,
            @ApiParam(value = "菜品图片",required = true) MultipartFile file,
            @ApiParam(name = "fiId", value = "菜品id",required = true)
            @RequestParam(value = "fiId") Long fiId) throws UnknownHostException {

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

        //获取ip
        InetAddress ia = InetAddress.getLocalHost();
        String ip = ia.getHostAddress();
        //获取端口
        int host = request.getLocalPort();

        String url = "http://"+ ip + ":" + host + newPath;

        if (flag1 == flag2 == flag3 ) {

            return Result.build(ResultType.Success).appendData("data",url);

        }else {

            return Result.build(ResultType.Failed).appendData("data","修改失败");

        }

    }
}
