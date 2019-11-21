package com.hotel.booking.server

import com.hotel.booking.bean.RoomType

/**
 * @Classname RoomTypeServer
 * @Description TODO
 * @Date 19-11-1 下午6:42
 * @Created by lanmeng
 */
interface RoomTypeServer{

    fun update(roomType: RoomType): Int


    fun create(roomType: RoomType): Int

    fun delete(id: Int): Int
}