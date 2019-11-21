package com.hotel.employee.mapper;

import com.hotel.employee.bean.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper extends JpaRepository<Employee,Long>, JpaSpecificationExecutor<Employee> {

    Employee findEmployeeByUsername(String username);

//    /**
//     * 三个参数模糊查询
//     */
//    List<Employee> findEmployeeByNameLikeAndDepartmentIdAndPositionId(String name, Long Did, Long Pid);
//    List<Employee> findEmployeeByNameLikeAndDepartmentId(String name, Long Did);
//    List<Employee> findEmployeeByNameLikeAndPositionId(String name, Long Pid);
//    List<Employee> findEmployeeByNameLike(String name);

//    if(?2 != 0, e_department_id = ?2, 1=1) if( :nam !=null ,e_name like :nam ,1=1)
    @Query(value = " select * from employee where (e_name like  CONCAT('%',:nam,'%') or :nam = 'null') and (e_number like  CONCAT('%',:number,'%') or :number = 'null') and if(:Did !=0,e_department_id = :Did,1=1) ", nativeQuery = true)
    List<Employee> findEmployeebyNameandDid(@Param("nam") String name,@Param("number") String number, @Param("Did") Long Did);

//    @Query(value = "insert into employee",nativeQuery = true)
//    boolean insert(Employee employee);

    Employee findEmployeeByNumber(String number);

    List<Employee> findAllByDepartmentIdAndPositionId(Long Did, Long Pid);


}
