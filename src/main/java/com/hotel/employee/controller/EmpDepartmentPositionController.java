package com.hotel.employee.controller;

import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.employee.bean.EmpDepartment;
import com.hotel.employee.bean.EmpDepartmentPosition;
import com.hotel.employee.bean.EmpPosition;
import com.hotel.employee.bean.Employee;
import com.hotel.employee.mapper.EmpDepartmentMapper;
import com.hotel.employee.mapper.EmpDepartmentPositionMapper;
import com.hotel.employee.mapper.EmpPositionMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "部门职位接口")
@Controller
@RequiresAuthentication
public class EmpDepartmentPositionController {

    @Autowired
    private EmpDepartmentMapper empDepartmentMapper;
    @Autowired
    private EmpPositionMapper empPositionMapper;
    @Autowired
    private EmpDepartmentPositionMapper empDepartmentPositionMapper;

    @RequiresPermissions("*:find")
    @ApiOperation(value = "查询所有部门",notes = "查所有部门，加上对应的职位")
    @GetMapping("EmpDepartmentPosition/findAllEmpDepartmentPosition")
    @ResponseBody
    public Result findAllEmpDepartmentPosition(){
        List<EmpDepartment> empDepartments = empDepartmentMapper.findAll();
        if (empDepartments!=null){
            return Result.build(ResultType.Success).appendData("data",empDepartments);
        }
        return Result.build(ResultType.Failed);
    }

    @RequiresPermissions("*:find")
    @ApiOperation(value = "查询所有职位",notes = "查所有部门，加上对应的职位")
    @GetMapping("EmpDepartmentPosition/findAllEmpPosition")
    @ResponseBody
    public Result findAllEmpPosition(){
        List<EmpPosition> empPositions = empPositionMapper.findAll();
        if (empPositions!=null){
            return Result.build(ResultType.Success).appendData("data",empPositions);
        }
        return Result.build(ResultType.Failed);
    }

//    @RequiresPermissions("*:find")
    @ApiOperation(value = "查询一个部门",notes = "根据部门id查询，加上对应的职位")
    @ApiImplicitParam(name = "Did",value = "部门id",dataType = "Long",paramType = "path")
    @GetMapping("EmpDepartmentPosition/findByIdEmpDepartmentPosition/{Did}")
    @ResponseBody
    public Result findAllEmpDepartmentPosition(@PathVariable Long Did){
        EmpDepartment empDepartment = empDepartmentMapper.findById(Did).get();
        if (empDepartment!=null){
            return Result.build(ResultType.Success).appendData("data",empDepartment);
        }
        return Result.build(ResultType.Failed);
    }

    @RequiresPermissions(value = { "*:add", "*:modify" }, logical = Logical.OR)
    @ApiOperation(value = "新增和修改部门和对应职位",notes = "新增和修改部门和对应职位")
    @PostMapping("EmpDepartmentPosition/saveEmpDepartmentPosition")
    @ResponseBody
    public Result saveEmpDepartmentPosition(@RequestBody EmpDepartment empDepartment){
        empDepartmentMapper.save(empDepartment);
        return Result.build(ResultType.Success,"操作成功");
    }

    @RequiresPermissions(value = { "*:add", "*:modify" }, logical = Logical.OR)
    @ApiOperation(value = "新增和修改职位",notes = "新增和修改职位")
    @ApiImplicitParam(name = "empPosition",value = "部门实体",dataType = "EmpPosition")
    @PostMapping("EmpDepartmentPosition/saveEmpPosition")
    @ResponseBody
    public Result saveEmpPosition(@RequestBody EmpPosition empPosition){
        empPositionMapper.save(empPosition);
        return Result.build(ResultType.Success,"操作成功");
    }

    @RequiresPermissions("*:delete")
    @ApiOperation(value = "删除部门",notes = "根据部门id删除部门")
    @ApiImplicitParam(name = "Did",value = "部门id",dataType = "Long", paramType = "path")
    @DeleteMapping("EmpDepartmentPosition/deleteEmpDepartment/{Did}")
    @ResponseBody
    public Result deleteEmpDepartment(@PathVariable Long Did){
        empDepartmentMapper.deleteById(Did);
        return Result.build(ResultType.Success);
    }

    @RequiresPermissions("*:delete")
    @ApiOperation(value = "删除职位",notes = "根据部门id删除职位")
    @ApiImplicitParam(name = "Pid",value = "职位id",dataType = "Long", paramType = "path")
    @DeleteMapping("EmpDepartmentPosition/deleteEmpPosition/{Pid}")
    @ResponseBody
    public Result deleteEmpPosition(@PathVariable Long Pid){
        empPositionMapper.deleteById(Pid);
        return Result.build(ResultType.Success);
    }

    @RequiresPermissions("*:delete")
    @ApiOperation(value = "删除部门下的职位",notes = "根据部门id删除部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Did",value = "部门id",dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "Pid",value = "职位id",dataType = "Long", paramType = "path")
    })
    @DeleteMapping("EmpDepartmentPosition/deletePositionForDepartment/{Did}/{Pid}")
    @ResponseBody
    public Result deletePositionFromDepartment(@PathVariable Long Did,@PathVariable Long Pid){
        int result = empDepartmentPositionMapper.deleteByDepartmentIdAndPositionId(Did,Pid);
        System.out.println(result);
        if (result>0){
            return Result.build(ResultType.Success,"操作成功");
        }
        return Result.build(ResultType.Failed,"操作失败");
    }




}
