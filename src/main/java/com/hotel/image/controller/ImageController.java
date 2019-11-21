package com.hotel.image.controller;

import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.image.bean.Image;
import com.hotel.image.mapper.ImageMapper;
import com.hotel.image.service.ImageService;
import com.hotel.utils.UploadUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Api(tags = "图片接口")
@Controller
public class ImageController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageMapper imageMapper;

    @ApiOperation(value = "上传图片",notes = "传项目id或客房id")
    @RequestMapping(value = "/uploadPicture",method = RequestMethod.POST)
    @ResponseBody
    public Result uploadPicture(@ApiParam(value = "图片",required = true) MultipartFile file,
                                @ApiParam(name = "roId", value = "房间id",required = false)
                                @RequestParam(value = "roId",required = false) Long roId,
                                @ApiParam(name = "reId", value = "项目id",required = false)
                                @RequestParam(value = "reId",required = false) Long reId) {
        //图片路径保存到数据库
        Image image = new Image();
        //保存id
        if(roId!=null)
            image.setRoomId(roId);
        if(reId!=null)
            image.setRecreationId(reId);

        //上传图片,获得数据库保存图片路径
        String filePath = imageService.uploadPicture(file);
        //保存路径
        image.setUrl(filePath);

        //添加图片路径到数据表
        boolean flag = imageService.insertImage(image);

        return Result.build(ResultType.Success).appendData("data","上传成功，保存路径成功");

    }


    @ApiOperation(value = "娱乐项目图片上传", notes = "传图片以及娱乐项目id")
    @ApiImplicitParam(name = "rid", value = "娱乐项目id", dataType = "Long", required = true,paramType ="path")
    @PostMapping("Image/uploadImageRecreation/{rid}")
    @ResponseBody
    public Result uploadImageRecreation( @RequestParam("file")MultipartFile file, @PathVariable Long rid){
        if (file==null || rid==null){
            return Result.build(ResultType.ParamError,"参数错误");
        }
        String fileName = file.getOriginalFilename();
        //获取后缀名
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID()+fileName;
        System.out.println("fileName===="+fileName);

        //获取娱乐项目的本地路径
        File dest = UploadUtils.getRecreationImagePathFile();
        System.out.println("dest="+dest);
        try {
            //构建文件的真实路径,项目的相对路径读取为硬盘下的绝对路径
            File newfile = new File(dest.getAbsolutePath() + File.separator +fileName);
            //System.out.println("文件绝对路径 ="+dest.getAbsolutePath() + File.separator +fileName);
            //保存文件
            file.transferTo(newfile);
        }catch (IOException e) {
            e.printStackTrace();
        }
        //数据库图片路径
        String filePath = UploadUtils.IMG_PATH_PREFIXRe + fileName;
        Image image = new Image();
        image.setUrl(filePath);
        image.setRecreationId(rid);
        imageMapper.save(image);
        return Result.build(ResultType.Success);
    }

    @ApiOperation(value = "删除娱乐项目图片",notes = "传入图片信息")
    @ApiImplicitParam(name = "image", value = "图片信息", dataType = "Image", required = false)
    @PutMapping("Image/deleteImageRecreation")
    @ResponseBody
    public Result deletePicture(@RequestBody Image image) {

        System.out.println(image);
        String url =image.getUrl();
        //去掉图片链接前面的http://10.20.5.26:8080/ 地址
        int index = url.indexOf("/static");
        //截取获得一半绝对路劲
        url = url.substring(index);
        System.out.println(url);
        //拼接获得绝对路径
        url = UploadUtils.IMG_PATH_SUFFIX+url;
        File file =  new File(url);

        //文件绝对路径
//        System.out.println(file.getAbsolutePath());
        if(file.exists()&&file.isFile()) {
            //删除图片
            file.delete();
            //删除数据库里面的图片
            imageMapper.deleteById(image.getId());
        }
        return Result.build(ResultType.Success).appendData("data","删除成功");
    }




}
