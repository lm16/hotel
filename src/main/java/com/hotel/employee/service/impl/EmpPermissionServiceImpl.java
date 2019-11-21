package com.hotel.employee.service.impl;

import com.hotel.employee.bean.EmpPermission;
import com.hotel.employee.bean.Employee;
import com.hotel.employee.mapper.EmpPermissionMapper;
import com.hotel.employee.mapper.EmployeeMapper;
import com.hotel.employee.service.EmpPermissionService;
import com.hotel.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpPermissionServiceImpl implements EmpPermissionService {

    @Autowired
    private EmpPermissionMapper empPermissionMapper;

    @Override
    public List<EmpPermission> findAll() {
        return empPermissionMapper.findAll();
    }

    @Override
    public boolean save(EmpPermission empPermission) {
        empPermissionMapper.save(empPermission);
        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        empPermissionMapper.deleteById(id);
        return true;
    }

}
