package com.harri.modules.banking.enums;

public enum AccountType {
    CHECKING("CHECKING"),
    SAVING("SAVING");

    private final String accountType;

    AccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }
}
