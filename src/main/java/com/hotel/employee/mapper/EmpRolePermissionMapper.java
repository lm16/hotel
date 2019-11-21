package com.hotel.employee.mapper;

import com.hotel.employee.bean.EmpRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmpRolePermissionMapper extends JpaRepository<EmpRolePermission,Long> {

    @Query(value = "select erp_per_id from emp_role_permission where erp_d_id =?1 and erp_p_id = ?2 ", nativeQuery = true)
    List<Long> findAllByDepartmentIdandPositionId(Long Did, Long Pid);

    List<EmpRolePermission> findAllByDepartmentIdAndPositionId(Long Did, Long Pid);

    @Transactional
    void deleteAllByDepartmentIdAndPositionId(Long Did, Long Pid);


}
