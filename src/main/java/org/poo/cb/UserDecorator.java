package org.poo.cb;

public class UserDecorator extends ActionDecorator {
    private int noOfStocks;

    public int getNoOfStocks() {
        return noOfStocks;
    }

    public void setNoOfStocks(int noOfStocks) {
        this.noOfStocks = noOfStocks;
    }

    public UserDecorator(ConcreteAction action, int noOfStocks) {
        super(action);
        this.noOfStocks = noOfStocks;
    }
}
