package com.hotel.booking.mapper

import com.hotel.booking.bean.RoomInfo
import com.hotel.booking.bean.livein.LiveinPart
import org.apache.ibatis.annotations.Param

interface RoomMapper {

    fun getAll(roomTypeId: Int?): List<Map<String, Any>>

    fun getAllorGroup(@Param("roomTypeId")roomTypeId: Int?, @Param("status")status: Int?): List<Map<String, Any>>

    fun delete(id: Int): Int

    fun create(roomInfo: RoomInfo): Int
    fun createHistory(id: Int): Int

    fun livein(liveinPart: LiveinPart): Int

    fun countSelect(): List<RoomInfo>

    fun countdown(): Int

    fun checkOut(bookingId: Int): Int

    fun cleanUp(roomId: Int): Int
}