package com.store.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Product {

    public Product() {

    }

    public Product(String name, String price, String count,
                   String description, String type,
                   String season, String gender) {
        this.name = name;
        this.price = Integer.parseInt(price);
        this.count = Integer.parseInt(count);
        this.description = description;
        this.season = season;
        this.type = type;
        this.gender = gender;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Size(min = 3, max = 20)
    @NotBlank
    @Getter
    @Setter
    private String name;

    @Size(max = 200)
    @NotBlank
    @Getter
    @Setter
    private String description;

    @Max(999999999)
    @Min(0)
    @NotNull
    @Getter
    @Setter
    private Integer price;

    @Max(999999999)
    @Min(0)
    @NotNull
    @Getter
    @Setter
    private Integer count;

    @NotBlank
    @Getter
    @Setter
    private String gender;

    @NotBlank
    @Getter
    @Setter
    private String season;

    @NotBlank
    @Getter
    @Setter
    private String type;

    @ManyToMany(mappedBy = "products")
    private Set<ListDesires> listDesires = new HashSet<>();

    public Set<ListDesires> getListDesires() {
        return listDesires;
    }

    public void addListDesires(ListDesires listDesires) {
        this.listDesires.add(listDesires);
    }

    public void removeListDesire(ListDesires listDesires) {
        this.listDesires.remove(listDesires);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product that = (Product) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name);
    }

    @Override
    public String toString() {
        return "ProductInOrder{ " +
                "id =" + id +
                "name = " + name +
                ")";
    }
}
