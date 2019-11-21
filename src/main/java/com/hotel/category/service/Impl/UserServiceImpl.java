package com.hotel.category.service.Impl;

import com.hotel.category.bean.Users;
import com.hotel.category.mapper.UserMapper;
import com.hotel.category.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/9/29
 * modified: 2019/9/29
 * 功能：
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    //查询
    public List<Users> findAll(){

    //System.out.println("no problem");

    return userMapper.findAll();

    }

    @Override
    public boolean insertUser(Users users) {

        return userMapper.insertUser(users);

    }
}
