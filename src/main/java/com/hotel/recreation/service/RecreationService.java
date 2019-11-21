package com.hotel.recreation.service;


import com.hotel.recreation.bean.Recreation;
import io.swagger.models.auth.In;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RecreationService {


    /**
     * 根据id查找一个娱乐项目信息
     * @param id
     * @return
     */
    Recreation findRecreationById(Long id);

    /**
     * 查询所有娱乐项目
     * @return
     */
    List<Recreation> findAll();

    /**
     * 新建娱乐项目
     */
    Recreation createRecreation(Recreation recreation);

    /**
     * 根据id删除娱乐项目信息
     * @param id
     * @return
     */
    void deleteRecreationById(Long id);

    //根据项目名称模糊 和负责人名称模糊 查询
    List<Recreation> findRecreationsByRnameandEmployeename(String rname,String ename);

    //修改list娱乐项目的图片链接
    List<Recreation> modifyImageUrl(List<Recreation> recreations, HttpServletRequest request);
}
