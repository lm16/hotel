package com.hotel.category.mapper;

import com.hotel.category.bean.Information;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/10/8
 * modified: 2019/10/21
 * 功能：菜品管理
 */
public interface InformationMapper {

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
    boolean deleteInformation(@Param(value = "fiId") Long fiId);


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
    List<HashMap> selectInformation(@Param(value = "fiName") String fiName);

    /**
     * 查询餐饮部的厨师
     * @return
     */
    List<HashMap> selectInformationChef();

}
