package com.store.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class ProductInOrder {

    public ProductInOrder() {
    }

    public ProductInOrder(Orderr orderr, @NotEmpty String productName, @NotNull Integer productTotalPrice, @Min(1) Integer count) {
        this.orderr = orderr;
        this.productName = productName;
        this.productTotalPrice = productTotalPrice;
        this.count = count;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "orderr_id")
    private Orderr orderr;

    @NotEmpty
    private String productName;

    @NotNull
    private Integer productTotalPrice;

    @Min(1)
    private Integer count;

    public Integer getId() {
        return id;
    }

    public Orderr getOrderr() {
        return orderr;
    }

    public void setOrderr(Orderr orderr) {
        this.orderr = orderr;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductInOrder that = (ProductInOrder) o;
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
