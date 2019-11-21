package com.hotel.booking.server

/**
 * @Classname Aa
 * @Description TODO
 * @Date 19-10-21 上午11:56
 * @Created by lanmeng
 */
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors
import java.util.stream.Stream

object Aa {

    fun days(timeStart: String)
            = days(LocalDate.parse(timeStart))

    fun days(start: LocalDate)
            = Stream.iterate(start, { localDate: LocalDate -> localDate.plusDays(1) })
            .limit(30)
//                .map { obj: LocalDate -> obj.toString() }
            .collect(Collectors.toList())

    fun deadline(timeStart: String, timeEnd: String)
            = deadline(LocalDate.parse(timeStart), LocalDate.parse(timeEnd))

    fun deadline(start: LocalDate, end: LocalDate)
            = Stream.iterate(start, { localDate: LocalDate -> localDate.plusDays(1) })
            .limit(ChronoUnit.DAYS.between(start, end))
//            .map { obj: LocalDate -> obj.toString() }
            .collect(Collectors.toList())

    fun getCron(date: LocalDateTime)
        = DateTimeFormatter.ofPattern("ss mm HH dd MM ?").format(date)

}



//ChronoUnit.DAYS.between(start, end)