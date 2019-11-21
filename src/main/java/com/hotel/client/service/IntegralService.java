package com.hotel.client.service;

import com.hotel.client.dao.Integral;

import java.util.List;

public interface IntegralService {

    /*
    查询折扣
     */
    List<Integral> findAll();

    /*
    增加折扣
     */
    Integral Integralsave(Integral integral);
    /*
    修改折扣
     */
    Integral Integralupdate(Integral integral);
    /*
    删除折扣
     */
    void Integrsldelete(Integer id);


}
