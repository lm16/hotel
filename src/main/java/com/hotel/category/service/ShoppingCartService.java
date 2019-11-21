package com.hotel.category.service;

import com.hotel.category.bean.Information;

import java.util.HashMap;

/**
 * @author 林晓锋
 * @date 2019/10/12
 * modified: 2019/10/12
 * 功能：购物车模块
 */
public interface ShoppingCartService {

    /**
     * 加入购物车
     * @param fiId
     * @return
     */
    HashMap selectInformationById(Long fiId);

}
