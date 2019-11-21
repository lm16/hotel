package com.hotel.booking.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author by lanmeng
 * @Classname Demo
 * @Description TODO
 * @Date 19-11-20 下午2:12
 */
@Data
public class Demo extends BaseRowModel {

    @ExcelProperty(value = "ID", index = 0)
    private Integer id;

    @ExcelProperty(value = "类型", index = 1)
    private String type;

}
