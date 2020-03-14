package com.store.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class ProductInBasket {

    public ProductInBasket() {
    }

    public ProductInBasket(Product product, Integer count) {
        this.productId = product.getId();
        this.productName = product.getName();
        this.productPrice = product.getPrice();
        this.count = count;
        this.productTotalPrice = this.count * this.productPrice ;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    private Integer productId;

    @NotEmpty
    private String productName;

    @NotNull
    private Integer productPrice;

    @NotNull
    private Integer productTotalPrice;

    @Min(1)
    private Integer count;

    public Integer getId() {
        return id;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(Integer productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void increment() {
        if ( count < 99999) {
            this.count++;
            this.productTotalPrice += this.productPrice;
        }
    }

    public void decrement() {
        if ( count > 1) {
            this.count--;
            this.productTotalPrice -= this.productPrice;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductInBasket that = (ProductInBasket) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "ProductInOrder{ " +
                "id =" + id +
                "name = " + productName +
                ")";
    }
}
