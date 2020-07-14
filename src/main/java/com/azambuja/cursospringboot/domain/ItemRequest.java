package com.azambuja.cursospringboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class ItemRequest implements Serializable {

    private static  final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private ItemRequestPK id = new ItemRequestPK();

    private Double discount;
    private Integer quantity;
    private Double price;

    public ItemRequest() {
    }

    public ItemRequest(BuyRequest buyRequest, Product product, Double discount, Integer quantity, Double price) {
        super();
        id.setBuyRequest(buyRequest);
        id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public ItemRequestPK getId() {
        return id;
    }

    public void setId(ItemRequestPK id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @JsonIgnore
    public BuyRequest getBuyRequest() {
        return this.id.getBuyRequest();
    }

    public Product getProduct() {
        return this.id.getProduct();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemRequest that = (ItemRequest) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
