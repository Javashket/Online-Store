package com.store.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ListDesires {

    public ListDesires() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne(mappedBy = "listDesires")
    private User user;

    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinTable(name = "list_desires_products")
    @JoinColumn(name = "list_desires_id",referencedColumnName = "id")
    private Set<Product> products = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void addProducts(Product product) {
        this.products.add(product);
    }

    public void deleteProduct(Product product) {
        this.products.remove(product);
    }

    public User getUser() {
        return user;
    }

    public Set<Product> getProducts() {
        return products;
    }
}
