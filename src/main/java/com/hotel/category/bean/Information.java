package com.hotel.category.bean;

import java.util.Objects;

/**
 * @author 林晓锋
 * @date 2019/10/8
 * modified: 2019/10/8
 * 功能：菜品信息表
 */
public class Information {

    private Long fiId;

    private Long fiCategoryId;

    private String fiName;

    private Double fiPrice;

    private String fiDescription;

    private Category category;

    private Long fiEmployeeId;

    public Information(Long fiId, Long fiCategoryId, String fiName, Double fiPrice, String fiDescription, Category category, Long fiEmployeeId) {
        this.fiId = fiId;
        this.fiCategoryId = fiCategoryId;
        this.fiName = fiName;
        this.fiPrice = fiPrice;
        this.fiDescription = fiDescription;
        this.category = category;
        this.fiEmployeeId = fiEmployeeId;
    }

    public Information() {
    }

    public Long getFiEmployeeId() {
        return fiEmployeeId;
    }

    public void setFiEmployeeId(Long fiEmployeeId) {
        this.fiEmployeeId = fiEmployeeId;
    }

    public Long getFiId() {

        return fiId;

    }

    public void setFiId(Long fiId) {

        this.fiId = fiId;

    }

    public Long getFiCategoryId() {

        return fiCategoryId;

    }

    public void setFiCategoryId(Long fiCategoryId) {

        this.fiCategoryId = fiCategoryId;

    }

    public String getFiName() {

        return fiName;

    }

    public void setFiName(String fiName) {

        this.fiName = fiName;

    }

    public Double getFiPrice() {

        return fiPrice;

    }

    public void setFiPrice(Double fiPrice) {

        this.fiPrice = fiPrice;

    }

    public String getFiDescription() {

        return fiDescription;

    }

    public void setFiDescription(String fiDescription) {

        this.fiDescription = fiDescription;

    }

    public Category getCategory() {

        return category;

    }

    public void setCategory(Category category) {

        this.category = category;

    }

   @Override
    public String toString() {
        return "Information{" +
                "fiId=" + fiId +
                ", fiCategoryId=" + fiCategoryId +
                ", fiName='" + fiName + '\'' +
                ", fiPrice=" + fiPrice +
                ", fiDescription='" + fiDescription + '\'' +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Information)) return false;
        Information that = (Information) o;
        return Double.compare(that.getFiPrice(), getFiPrice()) == 0 &&
                Objects.equals(getFiId(), that.getFiId()) &&
                Objects.equals(getFiCategoryId(), that.getFiCategoryId()) &&
                Objects.equals(getFiName(), that.getFiName()) &&
                Objects.equals(getFiDescription(), that.getFiDescription()) &&
                Objects.equals(getCategory(), that.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFiId(), getFiCategoryId(), getFiName(), getFiPrice(), getFiDescription(), getCategory());
    }
}
