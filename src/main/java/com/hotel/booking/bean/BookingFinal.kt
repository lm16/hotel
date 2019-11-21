package com.hotel.booking.bean

import java.time.LocalDate


/**
 * @Classname BookingFinal
 * @Description TODO
 * @Date 19-10-21 下午4:58
 * @Created by lanmeng
 */
data class BookingFinal(

        val id: Int?,
        val date: LocalDate,
        val roomTypeId: Int?,
        val count: Short?

)