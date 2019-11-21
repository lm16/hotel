package com.hotel.employee.mapper;

import com.hotel.employee.bean.EmpDepartment;
import com.hotel.employee.bean.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpDepartmentMapper extends JpaRepository<EmpDepartment,Long> {



}
