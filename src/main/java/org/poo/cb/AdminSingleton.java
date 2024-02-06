package org.poo.cb;

import java.util.ArrayList;
import java.util.List;

public class AdminSingleton {
    private static AdminSingleton instance;
    List<User> users = new ArrayList<>();

    public User findUser(String email) {
        for (User user: users)
            if (user.email.equals(email))
                return user;
        return null;
    }

    private AdminSingleton() {
    }
    public static AdminSingleton getInstance() {
        if (instance == null)
            instance = new AdminSingleton();
        return instance;
    }
}
