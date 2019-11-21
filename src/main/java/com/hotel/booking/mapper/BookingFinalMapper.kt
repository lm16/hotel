package com.hotel.booking.mapper

import com.hotel.booking.bean.Booking
import com.hotel.booking.bean.BookingFinal

interface BookingFinalMapper {

    fun create(bookingFinal: BookingFinal): Int

    fun getByDate(map: Map<String, Any>): List<Map<String, Any>>

    fun getCount(booking: Booking): Short
    fun pay(booking: Booking): Int

    fun restore(booking: Booking): Int
}