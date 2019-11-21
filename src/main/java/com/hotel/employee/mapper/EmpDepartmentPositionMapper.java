package com.hotel.employee.mapper;

import com.hotel.employee.bean.EmpDepartmentPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface EmpDepartmentPositionMapper extends JpaRepository<EmpDepartmentPosition,Long> {

    @Modifying
    @Transactional
    int deleteByDepartmentIdAndPositionId(Long Did,Long Pid);

}
