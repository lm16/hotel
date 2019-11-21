package com.hotel.category.service.Impl;

import com.hotel.category.bean.Desk;
import com.hotel.category.mapper.DeskMapper;
import com.hotel.category.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/10/17
 * modified: 2019/10/17
 * 功能：桌号管理
 */
@Service
public class DeskServiceImpl implements DeskService {

    @Autowired
    private DeskMapper deskMapper;
    /**
     * 增加餐桌
     * @param desk
     * @return
     */
    @Override
    public boolean insertDesk(Desk desk) {

        return deskMapper.insertDesk(desk);

    }

    /**
     * 查看餐桌
     * @return
     */
    @Override
    public List<HashMap> selectDesk() {

        return deskMapper.selectDesk();

    }

    /**
     * 修改餐桌
     * @param desk
     * @return
     */
    @Override
    public boolean updateDesk(Desk desk) {

        return deskMapper.updateDesk(desk);

    }

    /**
     * 删除餐桌
     * @param fdId
     * @return
     */
    @Override
    public boolean deleteDesk(Long fdId) {

        return deskMapper.deleteDesk(fdId);

    }

    /**
     * 返回所有餐桌名称
     * @return
     */
    @Override
    public List<HashMap> selectDeskName() {

        return deskMapper.selectDeskName();

    }

}
