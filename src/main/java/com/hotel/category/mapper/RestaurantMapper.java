package com.hotel.category.mapper;

import com.hotel.category.bean.Restaurant;
import org.springframework.data.repository.query.Param;

import javax.naming.Name;
import java.util.HashMap;
import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/10/17
 * modified: 2019/10/17
 * 功能：餐厅管理
 */
public interface RestaurantMapper {

    /**
     * 增加餐厅
     * @param restaurant
     * @return
     */
    boolean insertRestaurant(Restaurant restaurant);

    /**
     * 查看餐厅
     * @return
     */
    List<Restaurant> selectRestaurant();

    /**
     * 删除餐厅
     * @param frId
     * @return
     */
    boolean deleteRestaurant(@Param(value = "frId") Long frId);

    /**
     * 修改餐厅
     * @param restaurant
     * @return
     */
    boolean updateRestaurant(Restaurant restaurant);

    /**
     * 查看某餐厅下的餐桌
     * @param frId
     * @return
     */
    List<HashMap> selectRestaurantDesk(@Param(value = "frId") Long frId);

    /**
     * 查询所有餐厅名称
     * @return
     */
    List<HashMap> selectRestaurantName();

}
