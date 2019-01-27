package br.com.peixeurbano.deals.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class BuyOptionRequestDTO {

    @NotBlank
    private String title;

    @NotNull
    private BigDecimal normalPrice;

    @NotNull
    private BigDecimal salePrice;

    private BigDecimal percentageDiscount;

    @NotNull
    @Positive
    private Long quantityCupom;

    @NotNull
    private ZonedDateTime startDate;

    @NotNull
    private ZonedDateTime endDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(BigDecimal normalPrice) {
        this.normalPrice = normalPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(BigDecimal percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    public Long getQuantityCupom() {
        return quantityCupom;
    }

    public void setQuantityCupom(Long quantityCupom) {
        this.quantityCupom = quantityCupom;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

}
