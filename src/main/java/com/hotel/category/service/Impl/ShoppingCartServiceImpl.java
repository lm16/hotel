package com.hotel.category.service.Impl;

import com.hotel.category.bean.Information;
import com.hotel.category.mapper.ShoppingCartMapper;
import com.hotel.category.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author 林晓锋
 * @date 2019/10/12
 * modified: 2019/10/12
 * 功能：
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    /**
     * 加入购物车
     * @param fiId
     * @return
     */
    @Override
    public HashMap selectInformationById(Long fiId) {

        return shoppingCartMapper.selectInformationById(fiId);

    }
}
