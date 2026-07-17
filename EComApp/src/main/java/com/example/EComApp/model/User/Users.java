package com.example.EComApp.model.User;

import com.example.EComApp.model.Address.Address;
import com.example.EComApp.model.base.BaseEntity;
import com.example.EComApp.model.cart.Cart;
import com.example.EComApp.model.enums.Role;
import com.example.EComApp.model.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Address> addressList = new ArrayList<>();

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY)
    private List<Order> orderList = new ArrayList<>();

    @OneToOne(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Cart cart;

}
