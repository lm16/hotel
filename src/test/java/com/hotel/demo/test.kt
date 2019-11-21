package com.hotel.demo

import com.hotel.booking.bean.Booking
import com.hotel.booking.bean.BookingFinal
import com.hotel.booking.server.Aa
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * @Classname test
 * @Description TODO
 * @Date 19-10-21 上午11:19
 * @Created by lanmeng
 */

class test: DemoApplicationTests(){

    @Test
    fun aa(){
        bookingFinalMapper.getByDate(mapOf("start" to "20191021", "end" to "20191022"))
    }

    @Test
    fun bb(){
        cardService.findcount("2019102101");
    }

    @Test
    fun cc(){

        val dates = Aa.days(LocalDate.of(2019, 11, 2))

        val roomType = roomTypeMapper.getAll()

        for(it in dates){
            for(item in roomType){
                val bookingFinal = BookingFinal(null, it, item.id, item.count)
                bookingFinalMapper.create(bookingFinal)
            }
        }
    }

//    @Test
//    fun dd(){
//        bookingMapper.getAll()
//    }

    @Test
    fun ee(){
        roomMapper.getAll(null);
    }

    @Test
    fun ff(){
        val booking = Booking()
        booking.start = LocalDate.of(2019, 10, 22)
        booking.end = LocalDate.of(2019, 10, 31)

        val dates = Aa.deadline(booking.start, booking.end)
        dates.forEach(::println)
    }

    @Test
    fun gg(){
        val date = LocalDateTime.now().plusMinutes(15L)

        println(Aa.getCron(date))
    }


    @Test
    fun ab(){
        liveinMapper.getT()
    }
}