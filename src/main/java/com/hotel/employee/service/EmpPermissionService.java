package com.hotel.employee.service;

import com.hotel.employee.bean.EmpPermission;
import com.hotel.employee.bean.Employee;

import java.util.List;

public interface EmpPermissionService {


    //查询所有权限
    List<EmpPermission> findAll();

    //新增或修改权限
    boolean save(EmpPermission empPermission);

    //根据id 删除一个或多个权限
    boolean deleteById(Long id);

}
