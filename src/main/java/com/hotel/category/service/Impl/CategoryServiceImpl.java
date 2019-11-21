package com.hotel.category.service.Impl;

import com.hotel.category.bean.Category;
import com.hotel.category.bean.OrderForm;
import com.hotel.category.mapper.CategoryMapper;
import com.hotel.category.mapper.OrderFormMapper;
import com.hotel.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/10/8
 * modified: 2019/10/8
 * 功能：菜品种类管理
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private OrderFormMapper orderFormMapper;

    /**
     * 查看菜品种类
     * @return Category
     */
    @Override
    public List<HashMap> findAll(String fcName) {

       List<HashMap> maps= categoryMapper.findAll(fcName);

        return categoryMapper.findAll(fcName);

    }

    /**
     * 增加菜品种类
     * @param category
     * @return
     */
    @Override
    public boolean insertCategory(Category category) {

        return categoryMapper.insertCategory(category);

    }

    /**
     * 删除菜品种类
     * @param fcId
     * @return
     */
    @Override
    public boolean deleteCategory(Long fcId) {

        return categoryMapper.deleteCategory(fcId);

    }

    /**
     * 修改菜品种类
     * @param category
     * @return
     */
    @Override
    public boolean updateCategory(Category category) {

        return categoryMapper.updateCategory(category);

    }

    /**
     * 查询菜品种类名称
     * @return
     */
    @Override
    public List<HashMap> selectCategoryName() {

        return  categoryMapper.selectCategoryName();

    }

    /**
     * 查看某菜品种类拥有的菜品
     * @param fcId
     * @return
     */
    @Override
    public List<HashMap> selectCategoryInformation(Long fcId) {

        return categoryMapper.selectCategoryInformation(fcId);
    }

    /**
     *
     * @param fcId
     * @return
     */
    @Override
    public List<Long> selectCategoryInformationId(Long fcId) {

        return categoryMapper.selectCategoryInformationId(fcId);
    }

}
