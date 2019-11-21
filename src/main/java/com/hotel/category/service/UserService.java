package com.hotel.category.service;

import com.hotel.category.bean.Users;

import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/9/29
 * modified: 2019/9/29
 * 功能：
 */
public interface UserService {

    //查询
    List<Users> findAll();

    boolean insertUser(Users users);

}
