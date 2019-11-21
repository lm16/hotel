package com.hotel.booking.server.impl

import com.hotel.booking.bean.RoomType
import com.hotel.booking.mapper.RoomTypeMapper
import com.hotel.booking.server.RoomTypeServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * @Classname RoomTypeServerImpl
 * @Description TODO
 * @Date 19-11-1 下午6:43
 * @Created by lanmeng
 */
@Service
class RoomTypeServerImpl: RoomTypeServer {

    @Autowired
    lateinit var roomTypeMapper: RoomTypeMapper

    @Transactional(rollbackFor = [Exception::class])
    override fun update(roomType: RoomType): Int {
        val id = roomType.id
        val name = roomType.name
        val map: MutableMap<String, Any> = HashMap()
        map.put("roomTypeId", id)
        map.put("oldName", roomTypeMapper.getName(id))
        map.put("newName", name)
        val result = roomTypeMapper.update(roomType)
        if (result == 1 && map["oldName"] != map["newName"]) {
            roomTypeMapper.updateHistory(map)
        }
        return result
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun create(roomType: RoomType): Int {
        val row = roomTypeMapper.create(roomType)
        if (row == 1) {
            roomTypeMapper.createHistory(roomType)
        }
        return row
    }

    @Transactional
    override fun delete(id: Int): Int {

        val name = roomTypeMapper.getName(id)

        val row = roomTypeMapper.delete(id)

        roomTypeMapper.deleteHistory(id, name)

        return row
    }
}