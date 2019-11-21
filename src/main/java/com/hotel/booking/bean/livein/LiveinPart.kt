package com.hotel.booking.bean.livein

data class LiveinPart(

        var id: Int,

        var bookingId: Int,

        var roomId: Int,

        var identity: String,

        var livewith: Array<String>
)