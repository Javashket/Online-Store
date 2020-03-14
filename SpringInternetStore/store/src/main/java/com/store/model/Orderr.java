package com.store.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orderr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany( mappedBy = "orderr")
    private List<ProductInOrder> products = new ArrayList<>();

    private Integer summ;

    public Integer getSumm() {
        return summ;
    }

    public void setSumm(Integer summ) {
        this.summ = summ;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List <ProductInOrder> getProducts() {
        return products;
    }

    public void addProduct(ProductInOrder product) {
        this.products.add(product);
    }
}
