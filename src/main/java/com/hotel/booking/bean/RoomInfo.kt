package com.hotel.booking.bean


data class RoomInfo(
        val id: Int,
        val name: String,
        val roomTypeId: Int,

        val booking_id: Int,
        val countdown: Short
)