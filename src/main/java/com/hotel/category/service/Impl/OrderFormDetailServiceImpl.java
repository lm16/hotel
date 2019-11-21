package com.hotel.category.service.Impl;

import com.hotel.category.bean.OrderForm;
import com.hotel.category.bean.OrderFormDetail;
import com.hotel.category.mapper.OrderFormDetailMapper;
import com.hotel.category.service.OrderFormDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/10/18
 * modified: 2019/10/18
 * 功能：订单菜品管理
 */
@Service
public class OrderFormDetailServiceImpl implements OrderFormDetailService {

    @Autowired
    private OrderFormDetailMapper orderFormDetailMapper;

    /**
     * 增加订单菜品
     * @param orderFormDetail
     * @return
     */
    @Override
    public boolean insertOrderFormInformation(OrderFormDetail orderFormDetail) {

        return orderFormDetailMapper.insertOrderFormInformation(orderFormDetail);

    }

    /**
     * 查询订单菜品
     * @param foId
     * @return
     */
    @Override
    public List<HashMap> selectOrderFormDetail(Long foId) {

        return orderFormDetailMapper.selectOrderFormDetail(foId);

    }


}
