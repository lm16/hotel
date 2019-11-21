package com.hotel.category.mapper;

import com.hotel.category.bean.OrderFormDetail;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/10/18
 * modified: 2019/10/18
 * 功能：订单菜品管理
 */
public interface OrderFormDetailMapper {

    /**
     * 增加订单菜品
     * @param orderFormDetail
     * @return
     */
    boolean insertOrderFormInformation(OrderFormDetail orderFormDetail);


    /**
     * 查询订单菜品
     * @param foId
     * @return
     */
    List<HashMap> selectOrderFormDetail(@Param(value = "foId") Long foId);
}
