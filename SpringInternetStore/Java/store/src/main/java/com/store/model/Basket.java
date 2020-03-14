package com.store.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Basket {

    public Basket() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "basket")
    private User user;

     @OneToMany(fetch = FetchType.EAGER, mappedBy = "basket")
     private Set<ProductInBasket> products = new HashSet<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addProduct(ProductInBasket products) {
        this.products.add(products);
    }

    public Integer getId() {
        return id;
    }

    public Set<ProductInBasket> getProducts() {
        return products;
    }

    public void deleteProductInBasket(ProductInBasket productInBasket) {
        this.products.remove(productInBasket);
    }
}
