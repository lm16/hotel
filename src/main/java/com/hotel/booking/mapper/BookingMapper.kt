package com.hotel.booking.mapper

import com.hotel.booking.bean.Booking
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import java.time.LocalDate
import java.time.LocalDateTime

interface BookingMapper {

    // 查询所有
    fun getAll(map: Map<String, String>): List<Booking>

    // 查询单条
    fun getOne(id: Int): Booking

    // 查询状态
    fun getStatus(id: Int): String

    // 查询数量
    fun getCount(): List<MutableMap<String, Any>>

    // 查询单条以下单时间
    fun getByBooking(time: String): Booking

    // 创建单条
    fun create(booking: Booking): Int

    // 查询符合以名字
    fun getByName(@Param("name") name: String): List<Map<String, Any>>

    // 剩余
    fun getUnBookingCount(map: Map<String, Any>): List<Map<String, Any>>

    // 已订
    fun getDateBookingCount(map: Map<String, Any>): List<Map<String, Any>>



    fun livein(id: Int): Int

    fun pay(id: Int): Int

    fun unpay(time: String): Int

    fun restore(time: String): Int
}