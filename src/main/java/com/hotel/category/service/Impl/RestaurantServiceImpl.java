package com.hotel.category.service.Impl;

import com.hotel.category.bean.Restaurant;
import com.hotel.category.mapper.RestaurantMapper;
import com.hotel.category.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/10/17
 * modified: 2019/10/18
 * 功能：
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantMapper restaurantMapper;

    /**
     * 增加餐厅
     * @param restaurant
     * @return
     */
    @Override
    public boolean insertRestaurant(Restaurant restaurant) {

        return restaurantMapper.insertRestaurant(restaurant);

    }

    /**
     * 查看餐厅
     * @return
     */
    @Override
    public List<Restaurant> selectRestaurant() {

        return restaurantMapper.selectRestaurant();

    }

    /**
     * 删除餐厅
     * @param frId
     * @return
     */
    @Override
    public boolean deleteRestaurant(Long frId) {

        return restaurantMapper.deleteRestaurant(frId);
    }

    /**
     * 修改餐厅
     * @param restaurant
     * @return
     */
    @Override
    public boolean updateRestaurant(Restaurant restaurant) {

        return restaurantMapper.updateRestaurant(restaurant);

    }

    /**
     * 查看某餐厅的餐桌
     * @param frId
     * @return
     */
    @Override
    public List<HashMap> selectRestaurantDesk(Long frId) {

        return restaurantMapper.selectRestaurantDesk(frId);

    }

    /**
     * 查看所有餐厅名称
     * @return
     */
    @Override
    public List<HashMap> selectRestaurantName() {

        return restaurantMapper.selectRestaurantName();

    }
}
