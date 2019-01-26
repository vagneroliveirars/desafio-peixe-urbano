package br.com.peixeurbano.deals.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class BuyOptionResponseDTO {

    private String id;

    private String title;

    private BigDecimal normalPrice;

    private BigDecimal salePrice;

    private BigDecimal percentageDiscount;

    private Long quantityCupom;

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
