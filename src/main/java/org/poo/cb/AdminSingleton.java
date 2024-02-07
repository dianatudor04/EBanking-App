package org.poo.cb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminSingleton {
    private static AdminSingleton instance;
    List<User> users = new ArrayList<>();
    List<ConcreteAction> actions;

    public User findUser(String email) {
        for (User user: users)
            if (user.email.equals(email))
                return user;
        return null;
    }

    public ConcreteAction getAction(String company) {
        for (ConcreteAction action: actions)
            if (action.getCompany().equals(company))
                return action;
        return null;
    }

    public List<ConcreteAction> getStockValues(String path) {
        String filePath = "src/main/resources/" + path;
        List<ConcreteAction> actions = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(filePath))) {
            String line;
            int firstLine = 1;
            while ((line = r.readLine()) != null) {
                if (firstLine == 1) {
                    firstLine = 0;
                } else {
                    String[] values = line.split(",");
                    ConcreteAction action = new ConcreteAction(values[0]);
                    for (int i = 1; i <= 10; i++)
                        action.stockValues.add(Double.parseDouble(values[i]));
                    actions.add(action);
                }
            }
        } catch (IOException error) {
            System.out.println(error.toString());
        }
        return actions;
    }

    private AdminSingleton() {
    }

    public static AdminSingleton getInstance() {
        if (instance == null)
            instance = new AdminSingleton();
        return instance;
    }
}
