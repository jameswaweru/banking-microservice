package com.bankingmicroservice.app.model;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_accounts")
public class CustomerAccount {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerAccountId;

    @Column(length = 11)
    private String accountNumber;

    private double currentBalance;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId" , referencedColumnName = "customerId", nullable = true)
    public Customer customer;


}
