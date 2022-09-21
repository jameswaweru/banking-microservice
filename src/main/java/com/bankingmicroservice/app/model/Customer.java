package com.bankingmicroservice.app.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;

    @Column(length = 100)
    private String customerFullName;

    private String idNumber;

    @OneToMany(targetEntity = CustomerAccount.class)
    public Set<CustomerAccount> customerAccounts = new HashSet<>();

    @OneToMany(targetEntity = CustomerCard.class)
    public Set<CustomerCard> customerCards = new HashSet<>();

    @CreationTimestamp
    private ZonedDateTime createdAt;
    @UpdateTimestamp
    private ZonedDateTime updatedAt;

}
