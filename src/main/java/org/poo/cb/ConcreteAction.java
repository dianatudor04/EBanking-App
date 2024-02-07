package org.poo.cb;

import java.util.ArrayList;
import java.util.List;

public class ConcreteAction implements Action {
    private String company;
    List<Double> stockValues = new ArrayList<>();

    public ConcreteAction(String company) {
        this.company= company;
    }

    @Override
    public String getCompany() {
        return company;
    }
}
