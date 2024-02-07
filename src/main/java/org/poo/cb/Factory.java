package org.poo.cb;

public class Factory {
    public AccountAbstract createAccount(String currency) {
        switch (currency) {
            case "USD":
                return new AccountStockCurrencies("USD");
            case "EUR":
                return new Account("EUR");
            case "GBP":
                return new Account("GBP");
            case "JPY":
                return new Account("JPY");
            case "CAD":
                return new Account("CAD");
            default:
                return null;
        }
    }
}
