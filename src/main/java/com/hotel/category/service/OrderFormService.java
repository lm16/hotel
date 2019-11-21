package com.hotel.category.service;

import com.hotel.category.bean.OrderForm;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/10/19
 * modified: 2019/10/23
 * 功能：订单管理
 */
public interface OrderFormService {

    /**
     * 增加订单
     * @param orderForm
     * @return
     */
    boolean insertOrderForm(OrderForm orderForm);

    /**
     *查询订单
     * @return
     */
    List<HashMap> selectOrderForm(Integer foStatus, Date startDate,Date endDate);

    /**
     * 取消订单
     * @return
     */
    boolean cancelOrderForm(Long foId);

    /**
     *完成订单
     * @param orderForm
     * @return
     */
    boolean finishOrderForm(OrderForm orderForm);

    /**
     * 获得唯一订单号
     * @return
     */
    String getUniqueOutTradeNo();
}
