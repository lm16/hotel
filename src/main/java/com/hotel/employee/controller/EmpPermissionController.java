package com.hotel.employee.controller;

import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.employee.bean.EmpPermission;
import com.hotel.employee.bean.EmpRolePermission;
import com.hotel.employee.mapper.EmpRolePermissionMapper;
import com.hotel.employee.mapper.EmpPermissionMapper;
import com.hotel.employee.mapper.EmployeeMapper;
import com.hotel.employee.service.EmpPermissionService;
import com.hotel.employee.service.EmployeeService;
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

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "员工权限接口")
@Controller
@RequiresAuthentication
public class EmpPermissionController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmpPermissionService empPermissionService;
    @Autowired
    private EmpPermissionMapper empPermissionMapper;
    @Autowired
    private EmpRolePermissionMapper rolePermissionMapper;


    //--------------员工权限管理---------------//

    @RequiresPermissions("*:find")
    @ApiOperation(value = "查询某部门某职位下的所有权限", notes = "根据部门id+职位id 查询所有权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Did", value = "部门编号", required = true, dataType = "Long", defaultValue = "0"),
            @ApiImplicitParam(name = "Pid", value = "职位编号", required = true, dataType = "Long", defaultValue = "0")
    })
    @GetMapping("EmpPermission/findDPPermission/{Did}/{Pid}")
    public void findEmployeePermission(@PathVariable Long Did, @PathVariable Long Pid, HttpServletResponse response) throws Exception{
        response.setContentType("application/json;charset=utf-8");
        List<Long> Pids = rolePermissionMapper.findAllByDepartmentIdandPositionId(Did,Pid);
        List<EmpPermission> empPermissions = empPermissionMapper.findAllByIdIn(Pids);
        if (empPermissions!=null){
            String json = Result.build(ResultType.Success).appendData("data",empPermissions).convertIntoJSON();
            response.getWriter().write(json);
        }
    }

//    @ApiOperation(value = "测试查询所有权限", notes = "根据部门id+职位id 查询所有权限")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Did", value = "部门编号", required = true, dataType = "Long", defaultValue = "0"),
//            @ApiImplicitParam(name = "Pid", value = "职位编号", required = true, dataType = "Long", defaultValue = "0")
//    })
//    @GetMapping("EmpPermission/findAllByDepartmentIdAndPositionId/{Did}/{Pid}")
//    public void findAllByDepartmentIdAndPositionId(@PathVariable Long Did, @PathVariable Long Pid, HttpServletResponse response) throws Exception{
//        response.setContentType("application/json;charset=utf-8");
//        List<EmpRolePermission> empRolePermissions = rolePermissionMapper.findAllByDepartmentIdAndPositionId(Did,Pid);
//        System.out.println("++++"+empRolePermissions);
//        if (empRolePermissions!=null){
//            String json = Result.build(ResultType.Success).appendData("data",empRolePermissions).convertIntoJSON();
//            response.getWriter().write(json);
//        }
//    }



    @RequiresPermissions("*:find")
    @ApiOperation(value = "查询所有权限", notes = "无条件查询所有权限")
    @GetMapping("EmpPermission/findAllEmpPermission")
    public void findAllEmpPermission(HttpServletResponse response) throws Exception{
        response.setContentType("application/json;charset=utf-8");
        List<EmpPermission> empPermissions = empPermissionService.findAll();
        if (empPermissions!=null){
            String json = Result.build(ResultType.Success).appendData("data",empPermissions).convertIntoJSON();
            response.getWriter().write(json);
        }
    }

    @RequiresPermissions(value = { "*:add", "*:modify" }, logical = Logical.OR)
    @ApiOperation(value = "新增或修改权限", notes = "新增或修改权限")
    @ApiImplicitParam(name = "empPermission", value = "权限实体类", required = true, dataType = "EmpPermission")
    @PostMapping("EmpPermission/saveEmpPermission")
    @ResponseBody
    public Result saveEmpPermission(@RequestBody EmpPermission empPermission){
        boolean result = empPermissionService.save(empPermission);
        if (result){
            return Result.build(ResultType.Success);
        }
        return Result.build(ResultType.Failed);
    }


    @RequiresPermissions(value = { "*:add", "*:modify" }, logical = Logical.OR)
    @ApiOperation(value = "新增或修改部门对应职位的权限", notes = "新增或修改部门对应职位的权限")
    @ApiImplicitParam(name = "rolePermission", value = "权限中间表实体类", required = true, dataType = "EmpRolePermission")
    @PostMapping("EmpPermission/saveEmpRolePermission")
    @ResponseBody
    public Result saveEmpRolePermission(@RequestBody EmpRolePermission rolePermission){
        rolePermissionMapper.save(rolePermission);
        return Result.build(ResultType.Success);
    }

    @RequiresPermissions(value ="*:add" , logical = Logical.OR)
    @ApiOperation(value = "新增职位权限", notes = "新增职位权限")
    @PostMapping("EmpPermission/addEmpRolePermission")
    @ResponseBody
    public Result addEmpRolePermission(@RequestBody List<EmpRolePermission> empRolePermission){
        System.out.println("======================================");
        for (EmpRolePermission i:empRolePermission ) {
            System.out.println(i);
            rolePermissionMapper.save(i);
        }
        return Result.build(ResultType.Success);
    }

    @RequiresPermissions("*:delete")
    @ApiOperation(value = "删除权限", notes = "根据id删除权限")
    @ApiImplicitParam(name = "id", value = "权限id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("EmpPermission/deleteEmpPermission/{id}")
    @ResponseBody
    public Result deleteEmpPermission(@PathVariable Long id){
        boolean result = empPermissionService.deleteById(id);
        if (result){
            return Result.build(ResultType.Success);
        }
        return Result.build(ResultType.Failed);
    }

    @RequiresPermissions("*:delete")
    @ApiOperation(value = "删除中间表数据", notes = "根据部门id职位id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Did", value = "部门编号", required = true, dataType = "Long", defaultValue = "0",paramType = "path"),
            @ApiImplicitParam(name = "Pid", value = "职位编号", required = true, dataType = "Long", defaultValue = "0",paramType = "path")
    })
    @DeleteMapping("EmpPermission/deleteAllByDepartmentIdAndPositionId/{Did}/{Pid}")
    @ResponseBody
    public Result deleteAllByDepartmentIdAndPositionId(@PathVariable Long Did, @PathVariable Long Pid){
//        for ( EmpPermission e:empPermissions ) {
//            System.out.println(e);
//        }
        System.out.println(Did);
        System.out.println(Pid);
        rolePermissionMapper.deleteAllByDepartmentIdAndPositionId(Did,Pid);
        return Result.build(ResultType.Success);
    }





}
