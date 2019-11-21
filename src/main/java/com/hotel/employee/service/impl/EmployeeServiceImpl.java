package com.hotel.employee.service.impl;

import com.hotel.employee.bean.Employee;
import com.hotel.employee.mapper.EmployeeMapper;
import com.hotel.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public Employee findById(Long id) {
        return employeeMapper.findById(id).get();
    }

    @Override
    public boolean save(Employee employee) {
        employeeMapper.save(employee);
        return true;
    }

    @Override
    public List<Employee> findAll() {
        return employeeMapper.findAll();
    }

    @Override
    public Employee findEmployeeByUsername(String username) {
        return employeeMapper.findEmployeeByUsername(username);
    }

    @Override
    public List<Employee> findEmployeebyNameandDid(String name,String number, Long Did) {
        return employeeMapper.findEmployeebyNameandDid(name,number, Did);
    }

    @Override
    public boolean deleteEmployeeById(Long id) {
        employeeMapper.deleteById(id);
        return true;
    }

}
