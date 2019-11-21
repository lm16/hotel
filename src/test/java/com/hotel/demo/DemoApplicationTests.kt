package com.hotel.demo

import com.hotel.booking.mapper.*
import com.hotel.category.bean.Users
import com.hotel.category.service.UserService
import com.hotel.client.service.ClientServiceImpl.CardServiceImpl
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
open class DemoApplicationTests {

    @Autowired
    lateinit var bookingFinalMapper: BookingFinalMapper

    @Autowired
    lateinit var cardService: CardServiceImpl

    @Autowired
    lateinit var roomTypeMapper: RoomTypeMapper

    @Autowired
    lateinit var bookingMapper: BookingMapper

    @Autowired
    lateinit var roomMapper: RoomMapper

    @Autowired
    lateinit var liveinMapper: LiveinMapper
}