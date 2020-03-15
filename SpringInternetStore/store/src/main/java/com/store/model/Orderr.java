package com.store.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode
public class Orderr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Setter
    @Getter
    private User user;

    @OneToMany(mappedBy = "orderr")
    @Getter
    private List<ProductInOrder> products = new ArrayList<>();

    @Getter
    @Setter
    private Integer summ;

    public void addProduct(ProductInOrder product) {
        this.products.add(product);
    }
}
