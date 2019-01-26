package br.com.peixeurbano.deals.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.PositiveOrZero;
import java.time.ZonedDateTime;
import java.util.List;

@Document(collection = "deals")
public class Deal {

    @Id
    private String id;

    private String title;

    private String text;

    private ZonedDateTime createDate;

    private ZonedDateTime publishDate;

    private ZonedDateTime endDate;

    @Indexed(unique = true)
    private String url;

    @PositiveOrZero
    private Long totalSold;

    private DealType type;

    @DBRef
    private List<BuyOption> buyOptions;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public List<BuyOption> getBuyOptions() {
        return buyOptions;
    }

    public void setBuyOptions(List<BuyOption> buyOptions) {
        this.buyOptions = buyOptions;
    }

    public void updateTotalSold(Long quantity) {
        this.totalSold = totalSold + quantity;
    }

}
