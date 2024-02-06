package org.poo.cb;

import java.util.List;

class CreateUser extends Command {
    public void command(List<String> strings) {
        StringBuilder address = new StringBuilder();
        for (int i = 3; i < strings.size(); i++)
            address.append(strings.get(i)).append(" ");
        address.deleteCharAt(address.length() - 1);
        User user = new User(strings.get(0), strings.get(1), strings.get(2), address.toString());
        int found = 0;
        List<User> users = AdminSingleton.getInstance().users;
        for (User value : users)
            if (value.email.equals(strings.get(0))) {
                found = 1;
                System.out.println("User with " + strings.get(0) + " already exists");
                break;
            }
        if (found == 0) {
            AdminSingleton.getInstance().users.add(user);
        }
    }
}

class AddFriend extends Command {
    public void command(List<String> strings) {
        User userSource = AdminSingleton.getInstance().findUser(strings.get(0));
        User userDest = AdminSingleton.getInstance().findUser(strings.get(1));
        if (userSource == null) {
            System.out.println("User with email " + strings.get(0) + " doesn't exist");
        } else if (userDest == null) {
            System.out.println("User with email " + strings.get(1) + " doesn't exist");
        } else {
            if (userSource.friends.contains(userDest)) {
                System.out.println("User with " + userDest.email + " is already a friend");
            } else {
                userSource.friends.add(userDest);
                userDest.friends.add(userSource);
            }
        }
    }
}

class AddAccount extends Command {
    public void command(List<String> strings) {

    }
}

class AddMoney extends Command {
    public void command(List<String> strings) {

    }
}

class ExchangeMoney extends Command {
    public void command(List<String> strings) {

    }
}

class TransferMoney extends Command {
    public void command(List<String> strings) {

    }
}

class BuyStocks extends Command {
    public void command(List<String> strings) {

    }
}

class RecommendedStocks extends Command {
    public void command(List<String> strings) {

    }
}

class ListUser extends Command {
    public void command(List<String> strings) {
        User user = AdminSingleton.getInstance().findUser(strings.get(0));
        if (user == null) {
            System.out.println("User with " + user.email + " doesn't exist");
        } else {
            System.out.print("{\"email\":\"" + user.email + "\",\"firstname\":\"" + user.firstName +
                    "\",\"lastname\":\"" + user.lastName + "\",\"address\":\"" + user.address + "\",\"friends\":[");
            for (int i = 0; i < user.friends.size(); i++) {
                System.out.print("\"");
                System.out.print(user.friends.get(i).email);
                System.out.print("\"");
                if (i < user.friends.size() - 1)
                    System.out.print(",");
            }
            System.out.println("]}");
        }
    }
}

class ListPortfolio extends Command {
    public void command(List<String> strings) {

    }
}

public abstract class Command {
    public abstract void command(List<String> strings);
}
