package com.hotel.category.service.Impl;

import com.hotel.category.bean.OrderForm;
import com.hotel.category.mapper.OrderFormMapper;
import com.hotel.category.service.OrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author 林晓锋
 * @date 2019/10/19
 * modified: 2019/10/23
 * 功能：订单管理
 */
@Service
public class OrderFormServiceImpl implements OrderFormService {

    @Autowired
    private OrderFormMapper orderFormMapper;

    /**
     * 增加订单
     * @param orderForm
     * @return
     */
    @Override
    public boolean insertOrderForm(OrderForm orderForm) {

        return orderFormMapper.insertOrderForm(orderForm);

    }

    /**
     * 查询订单
     * @param foStatus
     * @return
     */
    @Override
    public List<HashMap> selectOrderForm(Integer foStatus,Date startDate,Date endDate) {

        List<HashMap> orderFormList = orderFormMapper.selectOrderForm(foStatus,startDate,endDate);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String dataStr = null;
        for( HashMap hashMap : orderFormList) {

            dataStr = format.format((Date)hashMap.get("fo_start_time"));
            hashMap.put("fo_start_time",dataStr);

            if (hashMap.get("fo_end_time")!=null) {
                dataStr = format.format((Date)hashMap.get("fo_end_time"));
                hashMap.put("fo_end_time",dataStr);
            }

        }

        return orderFormList ;

    }

    /**
     * 取消订单
     * @return
     */
    @Override
    public boolean cancelOrderForm(Long foId) {

        return orderFormMapper.cancelOrderForm(foId);

    }

    /**
     * 完成订单
     * @param orderForm
     * @return
     */
    @Override
    public boolean finishOrderForm(OrderForm orderForm) {

        return orderFormMapper.finishOrderForm(orderForm);

    }

    /**
     * 获得唯一订单号
     * @return
     */
    @Override
    public String getUniqueOutTradeNo() {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String format2 = format.format(new Date());
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {
            //有可能是负数
            hashCodeV = - hashCodeV;
        }
        return format2+String.format("%012d", hashCodeV);

    }

}
