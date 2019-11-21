package com.hotel.employee.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.employee.bean.EmpDepartment;
import com.hotel.employee.bean.Employee;
import com.hotel.employee.mapper.EmpDepartmentMapper;
import com.hotel.employee.mapper.EmployeeMapper;
import com.hotel.employee.service.EmployeeService;
import com.hotel.recreation.bean.Recreation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


//@RequiresAuthentication
@Api(tags = "员工接口")
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmpDepartmentMapper empDepartmentMapper;

    @RequestMapping("/403")
    @ResponseBody
    public Result noAuth(){
        return Result.build(ResultType.Unauthorized,"employee账户未授权");
    }


    @ResponseBody
    @RequestMapping("/toLogin")
    public Result toLogin(){
        Subject subject = SecurityUtils.getSubject();
        System.out.println("employee=========="+subject);
        return Result.build(ResultType.Continue,"employee权限认证失败,请登录账户");
    }

    //------------------------员工登录注销-------------------------//

    @ApiOperation(value = "员工登录", notes = "传入用户名和密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "用户编号", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "path")
    })
    @GetMapping("/login/{number}/{password}" )
    @ResponseBody
    public Result login(@PathVariable String number, @PathVariable String password, HttpServletRequest request){
        if(!StringUtils.isEmpty(number) && !StringUtils.isEmpty(password)) {
            HttpSession session = request.getSession();
            /**
             * 使用Shiro编写认证操作
             */
            //1.获取Subject
            Subject subject = SecurityUtils.getSubject();
            //2.封装用户数据
            UsernamePasswordToken token = new UsernamePasswordToken(number, password);
            //3.执行登录方法
            Employee employee = null;
            try {
                subject.login(token);
                //登录成功
                employee = employeeMapper.findEmployeeByNumber(token.getUsername());
//                System.out.println("==============" + employee.toString());
//                System.out.println("=========登录成功");
//                //用户信息存入session
                session.setAttribute("employee", employee);
//                Subject subject1 = SecurityUtils.getSubject();
//                System.out.println("======132==="+subject1.getPrincipal());
                return Result.build(ResultType.Success, "用户登录成功").appendData("data", employee);
            } catch (UnknownAccountException e) {
                //登录失败：用户不存在
                System.out.println("=========登录失败，用户名不存在");
                return Result.build(ResultType.Failed, "用户名不存在");
            } catch (IncorrectCredentialsException e) {
                //登录失败：密码错误
                System.out.println("=========登录失败，用户密码不正确");
                return Result.build(ResultType.Failed, "密码不正确");
            } catch (LockedAccountException e) {
                return Result.build(ResultType.Failed, "账号已被锁定");
            } catch (AuthenticationException ae) {
                ae.printStackTrace();
                return Result.build(ResultType.Failed, ae.getMessage());
            }
        }
        return Result.build(ResultType.ParamError, "用户名和密码不能为空");

    }

    @ApiOperation(value = "员工注销", notes = "退出登录")
    @GetMapping("/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request){
//        HttpSession session = request.getSession();
//        session.removeAttribute("employee");
        Subject subject = SecurityUtils.getSubject();
        if(subject != null){
            subject.logout();
        }
        return Result.build(ResultType.Success, "用户退出登录成功");
    }

    //------------------------员工信息管理-------------------------//

    @RequiresPermissions("*:find")
    @ApiOperation(value = "查询员工", notes = "根据姓名模糊+ 员工编号  + 部门id + 职位id 搜索所有员工")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "员工姓名", required = false, dataType = "String", paramType = "path",defaultValue = "null"),
            @ApiImplicitParam(name = "number", value = "员工编号", required = false, dataType = "String", paramType = "path",defaultValue = "null"),
            @ApiImplicitParam(name = "Did", value = "部门id", required = false, dataType = "Long", paramType = "path", defaultValue = "0"),
    })
    @GetMapping("Employee/findEmployeeByNameLike/{name}/{number}/{Did}")
    @ResponseBody
    public Result findEmployeeByNameLike(
            @PathVariable String name,
            @PathVariable String number,
            @PathVariable Long Did
            )throws Exception {
        System.out.println("======name="+name+"====number="+number+"========Did="+Did);
        List<Employee> employees = employeeService.findEmployeebyNameandDid(name,number,Did);
        if (employees!=null) {
            return Result.build(ResultType.Success).appendData("data", employees);
        }
        return Result.build(ResultType.Failed);
    }

//    @RequiresPermissions(value = { "*:find" }, logical = Logical.OR)
    @ApiOperation(value = "查询员工", notes = "根据 部门id + 职位id 搜索所有员工")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Did", value = "部门id", required = false, dataType = "Long", paramType = "path", defaultValue = "0"),
            @ApiImplicitParam(name = "Pid", value = "职位id", required = false, dataType = "Long", paramType = "path", defaultValue = "0")
    })
    @GetMapping("Employee/findAllByDepartmentIdAndPositionId/{Did}/{Pid}")
    @ResponseBody
    public Result findAllByDepartmentIdAndPositionId(
            @PathVariable Long Did,
            @PathVariable Long Pid
    )throws Exception {
        List<Employee> employees = employeeMapper.findAllByDepartmentIdAndPositionId(Did,Pid);
        if (employees!=null) {
            return Result.build(ResultType.Success).appendData("data", employees);
        }
        return Result.build(ResultType.Failed);
    }


    @RequiresPermissions(value = { "*:find" }, logical = Logical.OR)
    @ApiOperation(value = "查询所有员工", notes = "无条件查询所有员工")
    @GetMapping("Employee/findAllEmployee")
    @ResponseBody
    public Result findAllEmployee(HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=utf-8");
//        Subject subject = SecurityUtils.getSubject();
//        System.out.println("========="+subject.getPrincipal());
        List<Employee> employees = employeeService.findAll();
        if (employees!=null) {
//            String json =  Result.build(ResultType.Success).appendData("data", employees).convertIntoJSON();
//            response.getWriter().write(json);
            return Result.build(ResultType.Success).appendData("data",employees);
        }
        return Result.build(ResultType.Failed);
    }

    @RequiresPermissions("*:delete")
    @ApiOperation(value = "删除员工", notes = "根据员工id删除员工")
    @ApiImplicitParam(name = "id", value = "员工id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("Employee/deleteEmployee/{id}")
    @ResponseBody
    public Result deleteEmployee(@PathVariable Long id){
        boolean result = employeeService.deleteEmployeeById(id);
        if (result){
            return Result.build(ResultType.Success);
        }
        return Result.build(ResultType.Failed);
    }

    @RequiresPermissions(value = { "*:add", "*:modify" }, logical = Logical.OR)
    @ApiOperation(value = "新建或修改员工", notes = "输入所有员工信息进行创建或者修改")
    @ApiImplicitParam(name = "employee", value = "员工实体类", required = true, dataType = "Employee")
    @PostMapping("Employee/saveEmployee")
    @ResponseBody
    public Result saveEmployee(@RequestBody Employee employee){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(employee.getBirthday());
        calendar.add(calendar.DATE,1); //把日期往后增加一天,整数  往后推,负数往前移动
        employee.setBirthday(calendar.getTime());
        boolean result = employeeService.save(employee);
        if (result){
            return Result.build(ResultType.Success,"操作成功");
        }
        return Result.build(ResultType.Failed,"操作失败");
    }

//    @ApiOperation(value = "查询一个一员工", notes = "根据id")
//    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long",paramType = "path")
//    @GetMapping("Employee/findOneEmployeeById/{id}")
//    @ResponseBody
//    public Result findOneEmployeeById(HttpServletResponse response,@PathVariable Long id) throws Exception {
//        response.setContentType("application/json;charset=utf-8");
//        Employee employee = employeeMapper.findById(id).get();
//        System.out.println("==========="+employee.getName());
//        if (employee!=null) {
//            return Result.build(ResultType.Success).appendData("data",employee);
//        }
//        return Result.build(ResultType.Failed);
//    }


//
//    @ApiOperation(value = "查询所有员工by部门", notes = "查询所有员工by部门")
//    @ApiImplicitParam(name = "Did", value = "部门id", required = true, dataType = "Long", paramType = "path")
//    @GetMapping("Employee/findAllByDid/{Did}")
//    @ResponseBody
//    public void findAllByDid(HttpServletResponse response,@PathVariable Long Did) throws Exception {
//        response.setContentType("application/json;charset=utf-8");
//        EmpDepartment empDepartments = empDepartmentMapper.findById(Did).get();
//        if (empDepartments!=null) {
//            System.out.println(empDepartments);
//            String json = Result.build(ResultType.Success).appendData("data",empDepartments).convertIntoJSON();
//            response.getWriter().write(json);
//        }else {
//            response.getWriter().write(Result.build(ResultType.Failed).convertIntoJSON());
//        }
//    }


    //------------------------员工权限查询-------------------------//







}
