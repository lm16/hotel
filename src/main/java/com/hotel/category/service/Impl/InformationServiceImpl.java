package com.hotel.category.service.Impl;

import com.hotel.category.bean.Information;
import com.hotel.category.mapper.InformationMapper;
import com.hotel.category.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/10/8
 * modified: 2019/10/21
 * 功能：
 */
@Service
public class InformationServiceImpl implements InformationService {

    @Autowired
    private InformationMapper informationMapper;

    /**
     * 增加菜品信息
     * @param information
     * @return
     */
    @Override
    public boolean insertInformation(Information information) {

        return informationMapper.insertInformation(information);

    }

    /**
     * 删除菜品信息
     * @param fiId
     * @return
     */
    @Override
    public boolean deleteInformation(Long fiId) {

        return informationMapper.deleteInformation(fiId);

    }

    /**
     * 修改菜品信息
     * @param information
     * @return
     */
    @Override
    public boolean updateInformation(Information information) {

        return informationMapper.updateInformation(information);

    }

    /**
     * 查询菜品信息
     * @param fiName
     * @return
     */
    @Override
    public List<HashMap> selectInformation(String fiName) {

        return informationMapper.selectInformation(fiName);

    }

    /**
     * 查询餐饮部的厨师
     * @return
     */
    @Override
    public List<HashMap> selectInformationChef() {

        return informationMapper.selectInformationChef();

    }


}
