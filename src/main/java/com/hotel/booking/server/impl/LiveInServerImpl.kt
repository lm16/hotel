package com.hotel.booking.server.impl

import com.hotel.booking.mapper.LiveinMapper
import com.hotel.booking.mapper.RoomMapper
import com.hotel.booking.server.LiveinServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Classname LiveInServerImpl
 * @Description TODO
 * @Date 19-10-28 上午9:51
 * @Created by lanmeng
 */
@Service
open class LiveInServerImpl: LiveinServer{

    @Autowired
    lateinit var liveinMapper: LiveinMapper
    @Autowired
    lateinit var roomMapper: RoomMapper

    override fun clear() {

        liveinMapper.leave()

    }

    @Transactional
    override fun checkOut(bookingId: Int): Int {

        roomMapper.checkOut(bookingId)

        return liveinMapper.checkOut(bookingId)
    }
}