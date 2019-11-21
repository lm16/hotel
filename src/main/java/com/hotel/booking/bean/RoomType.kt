package com.hotel.booking.bean

import java.math.BigDecimal


data class RoomType(

    val id: Int,

    val name: String,
    val price: BigDecimal?,
    val description: String?,
    val url: String?,

    val count: Short?
)