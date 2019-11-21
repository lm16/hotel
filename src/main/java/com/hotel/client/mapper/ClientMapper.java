package com.hotel.client.mapper;


import com.hotel.client.dao.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientMapper extends JpaRepository<Client,Long> , JpaSpecificationExecutor<Client> {
    /**
     * 删除客户
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update client set delmark=1  where c_id=?1 ", nativeQuery = true)
    void deleteByIds(Long id);

    /*@Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update client set c_name=2?,c_phone=3?,c_email=4?,c_sex=5?,c_zj_type=6?,c_zj_no=7?,c_valid=8?,c_remark=9?,c_gerat_time=10?,c_delmark=11?;l  where c_id=?1 ", nativeQuery = true)
    void updateById(Long id,String c_name,String c_phone,String c_email,String c_sex,String c_zj_type,String c_zj_no,String c_valid,String c_remark,String c_gerat_time,String c_delmark);
*/

    /**
     * 查询所有
     * @return
     */
    @Query(value="select * from client where delmark!=1",nativeQuery = true)
    List<Client> getAll();




}