package com.harri.modules.banking.entities.jpa;

import com.harri.modules.banking.enums.AccountType;
import jakarta.persistence.*;

@Entity
@Table(name="accounts")
public class Account  {

    @Id
    @GeneratedValue
    public Long id;

    private AccountType type;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Account() {
    }

    public Account(AccountType type, Customer customer, Long id) {
        this.type = type;
        this.customer = customer;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }


}
