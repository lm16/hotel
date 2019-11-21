package com.hotel.category.bean;

/**
 * @author 林晓锋
 * @date 2019/10/18
 * modified: 2019/10/18
 * 功能：订单菜品表
 */
public class OrderFormDetail {

    private Long fodId;

    private Long fodOrderformId;

    private Long fodInformationId;

    private Integer fodNumber;

    public OrderFormDetail(Long fodId, Long fodOrderformId, Long fodInformationId, Integer fodNumber) {
        this.fodId = fodId;
        this.fodOrderformId = fodOrderformId;
        this.fodInformationId = fodInformationId;
        this.fodNumber = fodNumber;
    }

    public OrderFormDetail() {
    }

    public Long getFodId() {

        return fodId;

    }

    public void setFodId(Long fodId) {

        this.fodId = fodId;

    }

    public Long getFodOrderformId() {

        return fodOrderformId;

    }

    public void setFodOrderformId(Long fodOrderformId) {

        this.fodOrderformId = fodOrderformId;

    }

    public Long getFodInformationId() {

        return fodInformationId;

    }

    public void setFodInformationId(Long fodInformationId) {

        this.fodInformationId = fodInformationId;

    }

    public Integer getFodNumber() {

        return fodNumber;

    }

    public void setFodNumber(Integer fodNumber) {

        this.fodNumber = fodNumber;

    }
}
