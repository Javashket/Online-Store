package com.store.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode
public class ListDesires {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @OneToOne(mappedBy = "listDesires")
    @Getter
    @Setter
    private User user;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "list_desires_products")
    @JoinColumn(name = "list_desires_id", referencedColumnName = "id")
    @Getter
    private Set<Product> products = new HashSet<>();

    public void addProducts(Product product) {
        this.products.add(product);
    }

    public void deleteProduct(Product product) {
        this.products.remove(product);
    }
}
