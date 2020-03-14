package com.store.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    public User(){

    }

    public User(@Size(min = 6, max = 15) @NotBlank String login,
                @Size(min = 6) @NotBlank String password,
                @Email @Size( max = 30) @NotBlank String email,
                String role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 6, max = 15)
    @NotBlank
    private String login;

    @Size(min = 6)
    @NotBlank
    private String password;

    @Email
    @Size( max = 30)
    @NotBlank
    private String email;

    private String role;

    @Max(999999999)
    @Min(0)
    private Integer balance;

    private String activationCode;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
    private Set<Orderr> orders = new HashSet<Orderr>();

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="list_desires_id",  referencedColumnName = "id")
    private ListDesires listDesires;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="basket_id",  referencedColumnName = "id")
    private Basket basket;

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public Integer getBalance() {
        return balance;
    }

    public ListDesires getListDesires() {
        return listDesires;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public void setListDesires(ListDesires listDesires) {
        this.listDesires = listDesires;
    }

    public Set<Orderr> getOrders() {
        return orders;
    }

    public void addOrder(Orderr orderr) {
        this.orders.add(orderr);
    }
}
