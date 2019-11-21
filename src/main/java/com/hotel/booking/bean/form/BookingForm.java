package com.hotel.booking.bean.form;

import lombok.Data;

import java.time.LocalDate;

/**
 * @Classname BookingForm
 * @Description TODO
 * @Date 19-10-29 上午9:33
 * @Created by lanmeng
 */
@Data
public class BookingForm {

    private LocalDate start;
    private LocalDate end;

    private Integer roomTypeId;
    private String name;
    private String phone;
    private String sex;
    private String remark;

    private String memberCardnum;

}
