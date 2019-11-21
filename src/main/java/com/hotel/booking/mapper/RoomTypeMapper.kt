package com.hotel.booking.mapper

import com.hotel.booking.bean.RoomType
import com.hotel.booking.bean.RoomType2
import org.apache.ibatis.annotations.Param
import java.math.BigDecimal

interface RoomTypeMapper {

    fun getAll(): List<RoomType2>

    fun getName(@Param("id") id: Int): String

    fun select(): List<Map<String, Any>>

    fun getCost(roomTypeId: Int): BigDecimal

    fun img(@Param("roomTypeId")roomTypeId: Int, @Param("url")url: String): Int




    fun create(roomType: RoomType): Int

    fun createHistory(roomType: RoomType): Int

    fun update(roomType: RoomType): Int

    fun updateHistory(map: MutableMap<String, Any>): Int

    fun delete(id: Int): Int

    fun deleteHistory(@Param("id")id: Int, @Param("name") name: String): Int
}