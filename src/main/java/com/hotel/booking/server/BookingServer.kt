package com.hotel.booking.server

import com.hotel.booking.bean.Booking
import com.hotel.bean.Result

/**
 * @Classname BookingServer
 * @Description TODO
 * @Date 19-10-21 下午2:47
 * @Created by lanmeng
 */
interface BookingServer{

    //提交
    @Throws(Exception::class)
    fun create(booking: Booking): Booking

    //支付
    @Throws(Exception::class)
    fun pay(id: Int): Result

    fun timeout(time: String)

    fun money(id: Int): String
}