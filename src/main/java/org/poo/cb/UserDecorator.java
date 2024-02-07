package org.poo.cb;

public class UserDecorator extends ActionDecorator {
    private int noOfStocks;

    public UserDecorator(Action action, int noOfStocks) {
        super(action);
        this.noOfStocks = noOfStocks;
    }
}
