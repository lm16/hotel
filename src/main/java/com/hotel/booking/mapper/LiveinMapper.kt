package com.hotel.booking.mapper

import com.hotel.booking.bean.Livein
import com.hotel.booking.bean.RoomInfo
import com.hotel.booking.bean.livein.LiveinPart
import org.apache.ibatis.annotations.Param

interface LiveinMapper {

    fun getT(): List<Livein>

    fun getStatus(): Map<String, Any>

    fun create(liveinPart: LiveinPart): Int

    fun leave(): Int

    fun livewith(map: Map<String, Any>): Int

    fun checkOut(bookingId: Int): Int

    fun end(): Int


}