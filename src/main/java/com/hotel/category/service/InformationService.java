package com.hotel.category.service;

import com.hotel.category.bean.Information;

import java.util.HashMap;
import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/10/8
 * modified: 2019/10/21
 * 功能：
 */
public interface InformationService {

    /**
     * 增加菜品信息
     * @param information
     * @return
     */
    boolean insertInformation(Information information);

    /**
     * 删除菜品信息
     * @param fiId
     * @return
     */
    boolean deleteInformation(Long fiId);

    /**
     *修改菜品信息
     * @param information
     * @return
     */
    boolean updateInformation(Information information);

    /**
     * 查询菜品名称
     * @param fiName
     * @return
     */
    List<HashMap> selectInformation(String fiName);

    /**
     * 查询餐饮部的厨师
     * @return
     */
    List<HashMap> selectInformationChef();

}
