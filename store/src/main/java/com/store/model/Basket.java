package com.store.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @OneToOne(mappedBy = "basket")
    @Getter
    @Setter
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "basket")
    @Getter
    private Set<ProductInBasket> products = new HashSet<>();

    public void addProduct(ProductInBasket products) {
        this.products.add(products);
    }

    public void deleteProductInBasket(ProductInBasket productInBasket) {
        this.products.remove(productInBasket);
    }
}
