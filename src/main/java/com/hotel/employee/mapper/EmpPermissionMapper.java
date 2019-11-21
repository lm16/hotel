package com.hotel.employee.mapper;

import com.hotel.employee.bean.EmpPermission;
import com.hotel.employee.bean.EmpRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface EmpPermissionMapper extends JpaRepository<EmpPermission,Long> {

    @Query(value = " SELECT * FROM emp_permission where " +
            " per_id in( select erp_per_id from emp_role_permission where if(:Did !=0,erp_d_id = :Did,1=1) and if(:Pid!=0, erp_p_id = :Pid, 1=1 ) " +
            " and (per_name like concat('%',:pname,'%') or :pname ='null') ", nativeQuery = true)
    List<EmpPermission> findPermissionsAll(@Param("pname") String name, @Param("Did") Long Did, @Param("Pid") Long Pid);

    @Query(value = "select * from emp_permission where 1=1 and (per_name like concat('%',:pname,'%') or :pname ='null') ", nativeQuery = true)
    List<EmpPermission> findEmpPermissionsByNameLikeAll(@Param("pname") String name);

    List<EmpPermission> findAllByIdIn(List<Long> ids);

}
