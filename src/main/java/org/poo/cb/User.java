package org.poo.cb;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    String email;
    String firstName;
    String lastName;
    String address;
    Portfolio portfolio;
    List<User> friends = new ArrayList<>();

    public User(String email, String firstName, String lastName, String address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.portfolio = new Portfolio();
    }

    public Account findAccount(String type) {
        for (Account account: portfolio.accounts)
            if (account.type.equals(type))
                return account;
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName, address);
    }
}
