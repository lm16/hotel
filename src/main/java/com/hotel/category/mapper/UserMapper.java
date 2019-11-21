package com.hotel.category.mapper;

import com.hotel.category.bean.Users;

import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/9/27
 * modified: 2019/9/27
 * 功能：
 */
public interface UserMapper {

    //查询
    List<Users> findAll();

    boolean insertUser(Users users);
}
