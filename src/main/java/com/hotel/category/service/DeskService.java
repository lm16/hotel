package com.hotel.category.service;

import com.hotel.category.bean.Desk;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/10/17
 * modified: 2019/10/17
 * 功能：桌号管理
 */
public interface DeskService {

    /**
     * 增加餐桌
     * @param desk
     * @return
     */
    boolean insertDesk(Desk desk);

    /**
     * 查看餐桌
     * @return
     */
    List<HashMap> selectDesk();

    /**
     * 修改餐桌
     * @param desk
     * @return
     */
    boolean updateDesk(Desk desk);

    /**
     * 删除餐桌
     * @param fdId
     * @return
     */
    boolean deleteDesk(@Param(value = "fdId") Long fdId);

    /**
     * 查询所有餐桌名称
     * @return
     */
    List<HashMap> selectDeskName();

}
