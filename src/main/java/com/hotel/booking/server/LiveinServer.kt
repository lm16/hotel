package com.hotel.booking.server

import org.springframework.transaction.annotation.Transactional

interface LiveinServer{

    //清房
    fun clear()

    @Transactional
    fun checkOut(bookingId: Int): Int
}