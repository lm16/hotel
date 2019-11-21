package com.hotel.category.bean;

/**
 * @author 林晓锋
 * @date 2019/10/17
 * modified: 2019/10/17
 * 功能：餐厅表
 */
public class Restaurant {

    private Long frId;

    private String frName;

    private String frAddress;

    private String frHours;

    private Integer frStatus;

    public Restaurant(Long frId, String frName, String frAddress, String frHours, Integer fdStatus) {
        this.frId = frId;
        this.frName = frName;
        this.frAddress = frAddress;
        this.frHours = frHours;
        this.frStatus = fdStatus;
    }

    public Restaurant() {
    }

    public Long getFrId() {

        return frId;
    }

    public void setFrId(Long frId) {

        this.frId = frId;
    }

    public String getFrName() {

        return frName;
    }

    public void setFrName(String frName) {

        this.frName = frName;

    }

    public String getFrAddress() {

        return frAddress;

    }

    public void setFrAddress(String frAddress) {

        this.frAddress = frAddress;

    }

    public String getFrHours() {

        return frHours;

    }

    public void setFrHours(String frHours) {

        this.frHours = frHours;

    }

    public Integer getFrStatus() {

        return frStatus;

    }

    public void setFrStatus(Integer frStatus) {

        this.frStatus = frStatus;

    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "frId=" + frId +
                ", frName='" + frName + '\'' +
                ", frAddress='" + frAddress + '\'' +
                ", frHours='" + frHours + '\'' +
                ", fdStatus='" + frStatus + '\'' +
                '}';
    }

}
