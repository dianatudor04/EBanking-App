package org.poo.cb;

import java.util.ArrayList;
import java.util.List;

public class ConcreteAction implements Action {
    private String company;
    List<Double> stockValues = new ArrayList<>();

    public Double SMAAverage() {
        List<Double> SMALast5Days = stockValues.subList(5, stockValues.size());
        double last5Average = 0;
        double last10Average = 0;
        for (Double value: stockValues)
            last10Average = last10Average + value;
        for (Double value: SMALast5Days)
            last5Average = last5Average + value;
        double difference = last5Average / 5 - last10Average / 10;
        return difference;
    }

    public ConcreteAction(String company) {
        this.company= company;
    }

    @Override
    public String getCompany() {
        return company;
    }
}
