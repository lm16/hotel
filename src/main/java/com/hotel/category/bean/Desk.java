package com.hotel.category.bean;

/**
 * @author 林晓锋
 * @date 2019/10/17
 * modified: 2019/10/17
 * 功能：餐桌表
 */
public class Desk {

    private Long fdId;

    private String fdName;

    private String fdType;

    private Long fdRestaurantId;

    private Integer fdStatus;

    public Desk(Long fdId, String fdName, String fdType, Long fdRestaurantId, Integer fdStatus) {
        this.fdId = fdId;
        this.fdName = fdName;
        this.fdType = fdType;
        this.fdRestaurantId = fdRestaurantId;
        this.fdStatus = fdStatus;
    }

    public Desk() {
    }

    public Long getFdId() {

        return fdId;

    }

    public void setFdId(Long fdId) {

        this.fdId = fdId;

    }

    public String getFdName() {

        return fdName;

    }

    public void setFdName(String fdName) {

        this.fdName = fdName;

    }

    public String getFdType() {

        return fdType;

    }

    public void setFdType(String fdType) {

        this.fdType = fdType;

    }

    public Long getFdRestaurantId() {

        return fdRestaurantId;

    }

    public void setFdRestaurantId(Long fdRestaurantId) {

        this.fdRestaurantId = fdRestaurantId;

    }

    public Integer getFdStatus() {

        return fdStatus;

    }

    public void setFdStatus(Integer fdStatus) {

        this.fdStatus = fdStatus;

    }
}
