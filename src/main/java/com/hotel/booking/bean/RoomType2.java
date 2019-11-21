package com.hotel.booking.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.val;

import java.math.BigDecimal;

/**
 * @author by lanmeng
 * @Classname RoomType2
 * @Description TODO
 * @Date 19-11-20 下午3:26
 */
@Data
public class RoomType2 extends BaseRowModel {

    @ExcelProperty(value = "id", index = 0)
    Integer id;

    @ExcelProperty(value = "name", index = 1)
    String name;

    @ExcelProperty(value = "price", index = 2)
    BigDecimal price;

    @ExcelProperty(value = "description", index = 3)
    String description;

//    @ExcelProperty(value = "url", index = 4)
//    String url;

    @ExcelProperty(value = "count", index = 5)
    Short count;
}
