package account;

import transactions.Transaction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountId;
    private String ownerUsername;
    private double balance;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactionHistory = new ArrayList<>();

    // Constructors, getters, and setters
}
