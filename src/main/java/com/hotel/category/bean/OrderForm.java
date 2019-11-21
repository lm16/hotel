package com.hotel.category.bean;

import java.time.DateTimeException;
import java.util.Date;

/**
 * @author 林晓锋
 * @date 2019/10/18
 * modified: 2019/10/21
 * 功能：订单表
 */
public class OrderForm {

    private Long foId;

    private String foOutTradeNo;

    private String foOrderName;

    private Long foDeskId;

    private String foCardNumber;

    private Long foEmployeeId;

    private Double foMonetary;

    private Date foStartTime;

    private Date foEndTime;

    private String foDescription;

    private Integer foStatus;

    public OrderForm(Long foId, String foOutTradeNo, String foOrderName, Long foDeskId, String foCardNumber, Long foEmployeeId, Double foMonetary, Date foStartTime, Date foEndTime, String foDescription, Integer foStatus) {
        this.foId = foId;
        this.foOutTradeNo = foOutTradeNo;
        this.foOrderName = foOrderName;
        this.foDeskId = foDeskId;
        this.foCardNumber = foCardNumber;
        this.foEmployeeId = foEmployeeId;
        this.foMonetary = foMonetary;
        this.foStartTime = foStartTime;
        this.foEndTime = foEndTime;
        this.foDescription = foDescription;
        this.foStatus = foStatus;
    }

    public Long getFoEmployeeId() {
        return foEmployeeId;
    }

    public OrderForm() {
    }

    public String getFoOutTradeNo() {
        return foOutTradeNo;
    }

    public void setFoOutTradeNo(String foOutTradeNo) {
        this.foOutTradeNo = foOutTradeNo;
    }

    public String getFoOrderName() {
        return foOrderName;
    }

    public void setFoOrderName(String foOrderName) {
        this.foOrderName = foOrderName;
    }

    public void setFoEmployeeId(Long foEmployeeId) {

        this.foEmployeeId = foEmployeeId;

    }

    public Long getFoId() {

        return foId;

    }

    public void setFoId(Long foId) {

        this.foId = foId;

    }

    public Long getFoDeskId() {

        return foDeskId;

    }

    public void setFoDeskId(Long foDeskId) {

        this.foDeskId = foDeskId;

    }

    public String getFoCardNumber() {

        return foCardNumber;

    }

    public void setFoCardNumber(String foCardNumber) {

        this.foCardNumber = foCardNumber;

    }

    public Double getFoMonetary() {

        return foMonetary;

    }

    public void setFoMonetary(Double foMonetary) {

        this.foMonetary = foMonetary;

    }

    public Date getFoStartTime() {

        return foStartTime;

    }

    public void setFoStartTime(Date foStartTime) {

        this.foStartTime = foStartTime;

    }

    public Date getFoEndTime() {

        return foEndTime;

    }

    public void setFoEndTime(Date foEndTime) {

        this.foEndTime = foEndTime;

    }

    public String getFoDescription() {

        return foDescription;

    }

    public void setFoDescription(String foDescription) {

        this.foDescription = foDescription;

    }

    public Integer getFoStatus() {

        return foStatus;

    }

    public void setFoStatus(Integer foStatus) {

        this.foStatus = foStatus;

    }


    @Override
    public String toString() {
        return "OrderForm{" +
                "foId=" + foId +
                ", foDeskId=" + foDeskId +
                ", foCardNumber='" + foCardNumber + '\'' +
                ", foMonetary=" + foMonetary +
                ", foStartTime=" + foStartTime +
                ", foEndTime=" + foEndTime +
                ", foDescription='" + foDescription + '\'' +
                ", foStatus=" + foStatus +
                '}';
    }
}
