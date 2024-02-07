package org.poo.cb;

import java.util.ArrayList;
import java.util.List;

public class AccountStockCurrencies extends AccountAbstract {
    List<UserDecorator> actions = new ArrayList<>();

    public AccountStockCurrencies(String type) {
        this.type = type;
        this.sum = 0.00;
    }
}
