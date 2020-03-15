package com.store.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode
public class User {

    public User() {

    }

    public User(@Size(min = 6, max = 15) @NotBlank String login,
                @Size(min = 6) @NotBlank String password,
                @Email @Size(max = 30) @NotBlank String email,
                String role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Size(min = 6, max = 15)
    @NotBlank
    @Getter
    @Setter
    private String login;

    @Size(min = 6)
    @NotBlank
    @Getter
    @Setter
    private String password;

    @Email
    @Size(max = 30)
    @NotBlank
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String role;

    @Max(999999999)
    @Min(0)
    @Getter
    @Setter
    private Integer balance;

    @Getter
    @Setter
    private String activationCode;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @Getter
    private Set<Orderr> orders = new HashSet<Orderr>();

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "list_desires_id", referencedColumnName = "id")
    @Setter
    @Getter
    private ListDesires listDesires;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    @Setter
    @Getter
    private Basket basket;

    public void addOrder(Orderr orderr) {
        this.orders.add(orderr);
    }
}
