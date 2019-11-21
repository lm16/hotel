package com.hotel.category.service;

import com.hotel.category.bean.Category;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/10/8
 * modified: 2019/10/21
 * 功能：菜品种类管理
 */
public interface CategoryService {



    /**
     * 查看所有菜品种类
     * @return
     */
    List<HashMap> findAll(String fcName);

    /**
     * 增加菜品种类
     * @param category
     * @return
     */
    boolean insertCategory(Category category);

    /**
     * 删除菜品种类
     */
    boolean deleteCategory(Long fcId);

    /**
     * 修改菜品种类
     * @param category
     * @return
     */
    boolean updateCategory(Category category);

    /**
     * 查询菜品种类名称
     * @return
     */
    List<HashMap> selectCategoryName();

    /**
     * 查询某菜品种类拥有的菜品
     * @param fcId
     * @return
     */
    List<HashMap> selectCategoryInformation(Long fcId);

    /**
     * 查询某菜品种类拥有的菜品id(删除图片需要）
     * @param fcId
     * @return
     */
    List<Long> selectCategoryInformationId(@Param(value = "fcId") Long fcId);


}
