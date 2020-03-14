package com.store.model;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Product {

    public Product(){

    }

    public Product(String name, String price, String count,
                   String description, String type,
                   String season, String gender){
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
    private Integer id;

    @Size( min = 3, max = 20)
    @NotBlank
    private String name;

    @Size(max = 200)
    @NotBlank
    private String description;

    @Max(999999999)
    @Min(0)
    @NotNull
    private Integer price;

    @Max(999999999)
    @Min(0)
    @NotNull
    private Integer count;

    @NotBlank
    private String gender;

    @NotBlank
    private String season;

    @NotBlank
    private String type;

    @ManyToMany(mappedBy = "products")
    private Set<ListDesires> listDesires = new HashSet<>();

    public Set<ListDesires> getListDesires() {
        return listDesires;
    }

    public void addListDesires(ListDesires listDesires) {
        this.listDesires.add(listDesires);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getCount() {
        return count;
    }

    public String getGender() {
        return gender;
    }

    public String getSeason() {
        return season;
    }

    public String getType() {
        return type;
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
