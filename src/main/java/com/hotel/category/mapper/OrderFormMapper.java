package com.hotel.category.mapper;

import com.hotel.category.bean.OrderForm;
import org.springframework.data.repository.query.Param;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/10/19
 * modified: 2019/10/19
 * 功能：订单管理
 */
public interface OrderFormMapper {

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
    List<HashMap> selectOrderForm(@Param(value = "foStatus") Integer foStatus,
                                  @Param(value = "startDate") Date startDate,
                                  @Param(value = "endDate") Date endDate);

    /**
     * 取消订单
     * @return
     */
    boolean cancelOrderForm(@Param(value = "foId") Long foId);

    /**
     *
     * @param orderForm
     * @return
     */
    boolean finishOrderForm(OrderForm orderForm);

}
