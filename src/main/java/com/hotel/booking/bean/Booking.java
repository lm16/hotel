package com.hotel.booking.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Classname Booking
 * @Description TODO
 * @Date 19-10-21 下午4:15
 * @Created by lanmeng
 */
//@Data
public class Booking {

    private Integer id;
    private LocalDate start;
    private LocalDate end;
    private  Integer roomTypeId;

    private String name;
    private String phone;
    private String sex;
    private String remark;
    private String roomTypeName;

    private BigDecimal deposit;
    private String memberCardnum;
    private BigDecimal memberDeposit;

    private String status;

    private LocalDateTime booking;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getMemberCardnum() {
        return memberCardnum;
    }

    public void setMemberCardnum(String memberCardnum) {
        this.memberCardnum = memberCardnum;
    }

    public BigDecimal getMemberDeposit() {
        return memberDeposit;
    }

    public void setMemberDeposit(BigDecimal memberDeposit) {
        this.memberDeposit = memberDeposit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getBooking() {
        return booking;
    }

    public void setBooking(LocalDateTime booking) {
        this.booking = booking;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }
}
