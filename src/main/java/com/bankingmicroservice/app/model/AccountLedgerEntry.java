package com.bankingmicroservice.app.model;

import com.bankingmicroservice.app.enums.CardTypes;
import com.bankingmicroservice.app.enums.LedgerEntryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "accounts_ledger_entries")
public class AccountLedgerEntry {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ledgerEntryId;

    @Enumerated(value = EnumType.STRING)
    private LedgerEntryType entryType;

    private String accountNumber;

    private double amount;

    @CreationTimestamp
    private ZonedDateTime createdAt;
    @UpdateTimestamp
    private ZonedDateTime updatedAt;

}
