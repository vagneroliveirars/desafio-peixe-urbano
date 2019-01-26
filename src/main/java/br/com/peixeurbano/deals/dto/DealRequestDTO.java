package br.com.peixeurbano.deals.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.ZonedDateTime;
import java.util.List;

public class DealRequestDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    @NotNull
    private ZonedDateTime createDate;

    @NotNull
    private ZonedDateTime publishDate;

    @NotNull
    private ZonedDateTime endDate;

    @NotNull
    @PositiveOrZero
    private Long totalSold;

    @NotNull
    private DealType type;

    @Valid
    private List<BuyOptionRequestDTO> buyOptions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public ZonedDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(ZonedDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(Long totalSold) {
        this.totalSold = totalSold;
    }

    public DealType getType() {
        return type;
    }

    public void setType(DealType type) {
        this.type = type;
    }

    public List<BuyOptionRequestDTO> getBuyOptions() {
        return buyOptions;
    }

    public void setBuyOptions(List<BuyOptionRequestDTO> buyOptions) {
        this.buyOptions = buyOptions;
    }

}
