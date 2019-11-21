package com.hotel.booking.bean

import java.time.LocalDateTime

/**
 *
 * @author lm16
 * @
 *
 */
data class Livein(

        var id: Int,
        var BookingId: Int,
        var RoomId: Int,

        var identity: String,
        var EmployeeId: String?,

        var CheckIn: LocalDateTime,
        var CheckOut: LocalDateTime?

)