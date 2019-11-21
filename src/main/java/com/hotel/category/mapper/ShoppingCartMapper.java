package com.hotel.category.mapper;

import com.hotel.category.bean.Information;

import java.util.HashMap;


/**
 * @author 林晓锋
 * @date 2019/10/12
 * modified: 2019/10/12
 * 功能：
 */
public interface ShoppingCartMapper {

    /**
     * 根据id查询菜品信息
     * @param fiId
     * @return
     */
    HashMap selectInformationById(Long fiId);

}
