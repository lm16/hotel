package com.hotel.employee.mapper;

import com.hotel.employee.bean.EmpPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpPositionMapper extends JpaRepository<EmpPosition,Long> {

}
