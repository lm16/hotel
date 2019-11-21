package com.hotel.booking.server

import com.hotel.booking.mapper.BookingMapper
import com.hotel.booking.mapper.LiveinMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.scheduling.annotation.SchedulingConfigurer
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.scheduling.config.ScheduledTaskRegistrar
import org.springframework.scheduling.support.CronTrigger
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class RoomTimer : SchedulingConfigurer {

    @Autowired
    lateinit var bookingServer: BookingServer
    @Autowired
    lateinit var liveinMapper: LiveinMapper

    var scheduler = ThreadPoolTaskScheduler()

    override fun configureTasks(taskRegistrar: ScheduledTaskRegistrar) {
        scheduler.initialize()
    }

    fun start(cron: String) {
        scheduler.schedule(run, CronTrigger(cron))
    }

    val run = Runnable {

        val time = LocalDateTime.now().minusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString()

        bookingServer.timeout(time)
    }

    @Scheduled(cron="0 10 9 * * ?")
    fun leave(){
        liveinMapper.leave()
    }

}