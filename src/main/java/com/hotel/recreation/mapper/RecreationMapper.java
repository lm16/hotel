package com.hotel.recreation.mapper;


import com.hotel.recreation.bean.Recreation;
import org.dom4j.io.SAXReader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RecreationMapper extends JpaRepository<Recreation,Long>, JpaSpecificationExecutor<Recreation> {

    List<Recreation> findAllByNameLikeAndEmployeeName(String rname, String name);

    @Query(value = " select * from recreation where (re_name like concat('%',:rn,'%') or :rn = 'null') and  ( :en ='null' or (re_employee_id in (select e_id from employee where e_name like concat('%',:en,'%') ) ) )  ", nativeQuery = true)
    List<Recreation> findRecreationsByRnameandEmployeename(@Param("rn") String rn, @Param("en")String en);

    @Modifying
    @Transactional
    @Query(value = "update recreation set re_employee_id= :#{#re.employeeId},re_name=:#{#re.name},re_phone=:#{#re.phone},re_address=:#{#re.address},re_remark=:#{#re.remark}," +
            " re_description=:#{#re.description},re_price=:#{#re.price} where re_id = :#{#re.id}", nativeQuery = true)
    int updateRecreation(@Param("re") Recreation re);

    @Modifying
    @Transactional
    @Query(value = "insert  into recreation  (re_employee_id,re_name,re_phone,re_address,re_remark,re_description,re_price,datetime,by_who,deleted) " +
            " values (:#{#re.employeeId},:#{#re.name},:#{#re.phone},:#{#re.address},:#{#re.remark},:#{#re.description},:#{#re.price},now date(),:#{#re.byWho},:#{#re.deleted})",
             nativeQuery = true)
    Recreation addRecreation(@Param("re") Recreation re);


}
