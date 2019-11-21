package com.hotel.category.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.category.bean.Category;
import com.hotel.category.bean.OrderForm;
import com.hotel.category.mapper.OrderFormMapper;
import com.hotel.category.service.CategoryService;
import com.hotel.category.service.InformationService;
import com.hotel.category.service.UploadInformationPictureService;
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

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 林晓锋
 * @date 2019/10/8
 * modified: 2019/10/21
 * 功能：菜品种类管理
 */
@Api(tags = "菜系接口")
@RequestMapping("/json/category")
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UploadInformationPictureService uploadInformationPictureService;
    @Autowired
    private InformationService informationService;

    @RequiresPermissions(value = { "*:find" }, logical = Logical.OR)
    @ApiOperation(value = "查看菜系",notes = "正在测试")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public Result findAll(
            @ApiParam(name = "fcName",value = "菜系名称",required = false)
            @RequestParam(name = "fcName",required = false) String fcName) {

        List<HashMap> categoryList = categoryService.findAll(fcName);


        return Result.build(ResultType.Success).appendData("data",categoryList);

    }

    @RequiresPermissions(value = { "*:add" }, logical = Logical.OR)
    @ApiOperation(value = "增加菜系",notes = "正在测试")
    @RequestMapping(value = "/insertCategory",method = RequestMethod.POST)
    @ResponseBody
//    跟ApiParam用法基本一样
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "fcName", value = "菜品种类名称", dataType = "String", paramType = "query", required = true),
//            @ApiImplicitParam(name = "fcDescription", value = "菜品描述", dataType = "String", paramType = "query", required = true)
//    }
//    )
    public Result insertCategory(
            @ApiParam(name = "fcName",value = "菜系名称",required = true)
            @RequestParam(name = "fcName") String fcName,
            @ApiParam(name = "fcDescription",value = "菜系描述",required = true)
            @RequestParam(name = "fcDescription") String fcDescription) {

        Category category = new Category();
        category.setFcName(fcName);
        category.setFcDescription(fcDescription);

        boolean flag = categoryService.insertCategory(category);

        return Result.build(ResultType.Success).appendData("data",flag);

    }

    @RequiresPermissions(value = { "*:delete" }, logical = Logical.OR)
    @ApiOperation(value = "删除菜系",notes = "正在测试")
    @RequestMapping(value = "/deleteCategory",method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteCategory(
            @ApiParam(name ="fcId",value = "菜系id",required = true)
            @RequestParam(value = "fcId") Long fcId) {

        //获得该种类下的所有菜品id
        List<Long> informationIdList = categoryService.selectCategoryInformationId(fcId);
        if(informationIdList.size()!=0) {

            for (Long fiId :informationIdList) {

                //删除菜品信息
                boolean flag1 = informationService.deleteInformation(fiId);
                //获得图片路径
                String filePath = uploadInformationPictureService.selectInformationImagesPath(fiId);
                //删除图片
                boolean flag2 = uploadInformationPictureService.deleteItemImages(filePath);
            }

            //批量删除图片路径
            boolean flag3 = uploadInformationPictureService.deleteInformationImageList(informationIdList);
        }
        //删除菜系
        boolean flag = categoryService.deleteCategory(fcId);

        return Result.build(ResultType.Success).appendData("data",flag);

    }

    @RequiresPermissions(value = { "*:modify" }, logical = Logical.OR)
    @ApiOperation(value = "修改菜系",notes = "正在测试")
    @RequestMapping(value = "/updateCategory",method = RequestMethod.PUT)
    @ResponseBody
    public Result updateCategory(
            @ApiParam(name = "fcId",value = "菜系id",required = true)
            @RequestParam(name = "fcId") Long fcId,
            @ApiParam(name = "fcName",value = "菜系名称",required = true)
            @RequestParam(name = "fcName") String fcName,
            @ApiParam(name = "fcDescription",value = "菜系描述",required = true)
            @RequestParam(name = "fcDescription") String fcDescription) {

        Category category = new Category();
        category.setFcId(fcId);
        category.setFcName(fcName);
        category.setFcDescription(fcDescription);

        boolean flag = categoryService.updateCategory(category);
        return Result.build(ResultType.Success).appendData("data",flag);

    }

    @ApiOperation(value = "查询所有菜系名称",notes = "正在测试")
    @RequestMapping(value = "/selectCategoryName",method = RequestMethod.GET )
    @ResponseBody
    public Result selectCategoryName() {

        List<HashMap> categoryNameList = categoryService.selectCategoryName();

        return Result.build(ResultType.Success).appendData("data",categoryNameList);

    }

    @ApiOperation("查询某菜系的菜品")
    @RequestMapping(value = "/selectCategoryInformation",method = RequestMethod.GET)
    @ResponseBody
    public Result selectCategoryInformation(HttpServletRequest request,
            @ApiParam(name = "fcId",value = "菜系id",required = true)
            @RequestParam(name = "fcId") Long fcId,
            @ApiParam(name = "fiName",value = "菜品名称",required = false)
            @RequestParam(name = "fiName",required = false) String fiName) throws UnknownHostException {
        //获得菜品信息
        List<HashMap> informationList = categoryService.selectCategoryInformation(fcId);
        //图片路径转换为url
        List<HashMap> informationLists = uploadInformationPictureService.transformUrl(request,informationList);

        List<HashMap> informationMaps  = new ArrayList<>();
        if( fiName != ""&& fiName != null ) {
            //查询该种类下的某一菜品
            String fiNames = null;
            for (HashMap informationMap:informationLists) {
                //菜品名称
                fiNames = (String)informationMap.get("fi_name");
                //输入的菜品名称
                Pattern pattern = Pattern.compile(fiName);
                //模式匹配
                Matcher matcher = pattern.matcher(fiNames);
                //模式查找
                if(matcher.find()) {
                    //如果找到
                    informationMaps.add(informationMap);
                }

            }
        }
        else {
            informationMaps.addAll(informationLists);
        }

        return Result.build(ResultType.Success).appendData("data",informationMaps);

    }

    @ApiOperation(value = "/查询某菜品种类拥有的菜品id",notes = "正在测试")
    @RequestMapping(value = "/selectCategoryInformationId",method = RequestMethod.GET)
    @ResponseBody
    public Result selectCategoryInformationId(
            @ApiParam(name = "fcId",value = "菜系id",required = true)
            @RequestParam(name = "fcId") Long fcId) {

       List<Long> informationIdList = categoryService.selectCategoryInformationId(fcId);

       return Result.build(ResultType.Success).appendData("data",informationIdList);

    }

}
