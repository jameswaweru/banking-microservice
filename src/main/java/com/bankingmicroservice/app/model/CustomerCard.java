package com.bankingmicroservice.app.model;

import com.bankingmicroservice.app.enums.CardTypes;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_cards")
public class CustomerCard {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerCardId;

    @Column(length = 16)
    private String cardNumber;

    private long customerAccountId;

    @Column(length = 11)
    private String accountNumber;

    @Enumerated(value = EnumType.STRING)
    private CardTypes cardType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId" , referencedColumnName = "customerId", nullable = true)
    public Customer customer;
}
