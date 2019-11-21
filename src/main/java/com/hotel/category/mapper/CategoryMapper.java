package com.hotel.category.mapper;

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
public interface CategoryMapper {

    /**
     * 查看菜品种类
     * @return
     */
    List<HashMap> findAll(@Param(value = "fcName") String fcName);

    /**
     * 增加菜品种类
     * @param category
     * @return
     */
    boolean insertCategory(Category category);

    /**
     * 删除菜品种类
     */
    boolean deleteCategory(@Param(value = "fcId") Long fcId);

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
     */
    List<HashMap> selectCategoryInformation(@Param(value = "fcId") Long fcId);

    /**
     * 查询某菜品种类拥有的菜品id(删除图片需要）
     * @param fcId
     * @return
     */
    List<Long> selectCategoryInformationId(@Param(value = "fcId") Long fcId);


}
