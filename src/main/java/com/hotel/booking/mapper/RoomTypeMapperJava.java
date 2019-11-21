package com.hotel.booking.mapper;

import com.hotel.booking.bean.RoomType;
import com.hotel.booking.bean.RoomTypeJava;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Classname RoomTypeMapperJava
 * @Description TODO
 * @Date 19-10-31 下午4:14
 * @Created by lanmeng
 */
public interface RoomTypeMapperJava {

    @Select("select id, name, price, description from room_type")
    @Results({
            @Result(column="id", property = "id"),
            @Result(column="id", property="url", many=@Many(select = "com.hotel.booking.mapper.RoomTypeMapperJava.getImg"))
    })
    List<RoomTypeJava> getAll();

    @Select("select url from room_type_img where room_type_id = #{roomTypeId}")
    List<String> getImg(int roomTypeId);
}
