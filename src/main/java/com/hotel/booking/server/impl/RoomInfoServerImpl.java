package com.hotel.booking.server.impl;


import com.hotel.booking.bean.RoomInfo;
import com.hotel.booking.bean.RoomType;
import com.hotel.booking.mapper.RoomMapper;
import com.hotel.booking.mapper.RoomTypeMapper;
import com.hotel.booking.server.RoomInfoServer;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoomInfoServerImpl implements RoomInfoServer {

    @Autowired
    private RoomMapper roomMapper;



    public void get(){



    }

    @Override
    public void leave() {

    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createRoomInfo(@NotNull RoomInfo roomInfo) {

        int row = roomMapper.create(roomInfo);

        if(row == 1){
            int id = roomInfo.getId();
            roomMapper.createHistory(id);
        }

        return row;
    }
}
