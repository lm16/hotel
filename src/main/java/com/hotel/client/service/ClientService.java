package com.hotel.client.service;
import com.hotel.client.dao.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientService {


    /**
     * 根据id查找一个客户信息
     * @param id
     * @return
     */

    Client findClientById(Long id);

    /**
     * 查询所有客户
     * @return
     */
    List<Client> findAll();


    /**
     * 增加客户
     * @return
     */
    Client ClientSave(Client client);


    /**
     * 删除客户
     * @return
     */

   void deleteByIds(Long id);

    /**
     * 更新客户
     * @return
     */
    Client ClientUpdate(Client client);

    /**
     * 动态查询
     */
    List<Client> findbysome(Client client);



}