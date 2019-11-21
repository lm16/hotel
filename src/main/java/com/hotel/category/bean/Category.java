package com.hotel.category.bean;

import java.util.List;
import java.util.Objects;

/**
 * @author 林晓锋
 * @date 2019/10/8
 * modified: 2019/10/8
 * 功能：菜品种类表
 */
public class Category {

    private Long fcId;

    private String fcName;

    private String fcDescription;

    private List<Information> information;

    public List<Information> getInformation() {

        return information;

    }

    public void setInformation(List<Information> information) {

        this.information = information;

    }

    public Category(Long fcId, String fcName, String fcDescription) {
        this.fcId = fcId;
        this.fcName = fcName;
        this.fcDescription = fcDescription;
    }

    public Category() {

    }

    public Long getFcId() {

        return fcId;

    }

    public void setFcId(Long fcId) {

        this.fcId = fcId;

    }

    public String getFcName() {

        return fcName;

    }

    public void setFcName(String fcName) {

        this.fcName = fcName;
    }

    public String getFcDescription() {

        return fcDescription;

    }

    public void setFcDescription(String fcDescription) {

        this.fcDescription = fcDescription;

    }

    @Override
    public String toString() {

        return "Category{" +
                "fcId=" + fcId +
                ", fcName='" + fcName + '\'' +
                ", fcDescription='" + fcDescription + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return getFcId().equals(category.getFcId()) &&
                getFcName().equals(category.getFcName()) &&
                getFcDescription().equals(category.getFcDescription()) &&
                getInformation().equals(category.getInformation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFcId(), getFcName(), getFcDescription(), getInformation());
    }
}
