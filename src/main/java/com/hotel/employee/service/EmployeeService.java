package com.hotel.employee.service;

import com.hotel.employee.bean.EmpPermission;
import com.hotel.employee.bean.Employee;

import java.util.List;

public interface EmployeeService {

    //根据id查员工
    Employee findById(Long id);

    //新增和修改员工
    boolean save(Employee employee);

    //查询所有员工
    List<Employee> findAll();

    //根据用户名查员工
    Employee findEmployeeByUsername(String username);

    //根据姓名模糊  + 部门id + 职位id 搜索所有员工
    List<Employee> findEmployeebyNameandDid(String name,String number, Long Did);

    //根据id删除员工
    boolean deleteEmployeeById(Long id);

}
