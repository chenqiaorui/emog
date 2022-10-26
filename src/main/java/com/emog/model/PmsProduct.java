package com.emog.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class PmsProduct implements Serializable {
    private Long id;

    /**
     * 商品名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 图片
     *
     * @mbggenerated
     */
    private String pic;

    /**
     * 删除状态：0->未删除；1->已删除
     *
     * @mbggenerated
     */
    private Integer deleteStatus;

    /**
     * 销量
     *
     * @mbggenerated
     */
    private Integer sale;

    /**
     * 商品价格
     *
     * @mbggenerated
     */
    private BigDecimal price;

    /**
     * 商品库存
     *
     * @mbggenerated
     */
    private Integer stock;

    /**
     * 商品描述
     *
     * @mbggenerated
     */
    private String description;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", pic=").append(pic);
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", sale=").append(sale);
        sb.append(", price=").append(price);
        sb.append(", stock=").append(stock);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}