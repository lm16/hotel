package com.hotel.booking.server

import com.hotel.booking.bean.RoomInfo
import com.hotel.booking.bean.RoomType
import org.springframework.transaction.annotation.Transactional

interface RoomInfoServer{

    fun leave()




    fun createRoomInfo(roomInfo: RoomInfo): Int
}