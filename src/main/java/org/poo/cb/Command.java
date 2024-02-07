package org.poo.cb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        User user = AdminSingleton.getInstance().findUser(strings.get(0));
        int found = 0;
        for (Account account: user.portfolio.accounts)
            if (account.type.equals(strings.get(1))) {
                found = 1;
                System.out.println("Account in currency " + strings.get(0) + " already exists for user");
                break;
            }
        if (found == 0) {
            user.portfolio.accounts.add(new Account(strings.get(1)));
        }
    }
}

class AddMoney extends Command {
    public void command(List<String> strings) {
        User user = AdminSingleton.getInstance().findUser(strings.get(0));
        Account account = user.findAccount(strings.get(1));
        account.sum = account.sum + Double.parseDouble(strings.get(2));
    }
}

class ExchangeMoney extends Command {
    static Double[][] exchangeValues = new Double[5][5];

    public static void getExchangeValues() {
        String filePath = "src/main/resources/common/exchangeRates.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int pozLine = 0;
            while ((line = reader.readLine()) != null) {
                if (pozLine > 0) {
                    String[] values = line.split(",");
                    for (int i = 1; i < values.length; i++) {
                        exchangeValues[i - 1][pozLine - 1] = Double.parseDouble(values[i]);
                    }
                }
                pozLine++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void command(List<String> strings) {
        ExchangeMoney.getExchangeValues();
        User user = AdminSingleton.getInstance().findUser(strings.get(0));
        Account accountSource = user.findAccount(strings.get(1));
        Account accountDest = user.findAccount(strings.get(2));
        double sum = Double.parseDouble(strings.get(3));
        double exchangeValue = exchangeValues[this.dictionary.get(accountSource.type)][this.dictionary.get(accountDest.type)];
        if (sum * exchangeValue > accountSource.sum) {
            System.out.println("Insufficient amount in account " + accountSource.type + " for exchange");
        } else {
            accountDest.sum = accountDest.sum + sum;
            if (sum * exchangeValue > accountSource.sum / 2) {
                sum = sum * 1.01;
            }
            accountSource.sum = accountSource.sum - sum * exchangeValue;
        }
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
        User user = AdminSingleton.getInstance().findUser(strings.get(0));
        if (user == null) {
            System.out.println("User with " + user.email + " doesn't exist");
        } else {
            System.out.print("{\"stocks\":" + "[]" + ",\"accounts\":[");
            for (int i = 0; i < user.portfolio.accounts.size(); i++) {
                System.out.print("{\"currencyName\":\"");
                System.out.print(user.portfolio.accounts.get(i).type);
                System.out.print("\",");
                System.out.print("\"amount\":\"");
                System.out.printf("%.2f", user.portfolio.accounts.get(i).sum);
                System.out.print("\"}");
                if (i < user.portfolio.accounts.size() - 1)
                    System.out.print(",");
            }
            System.out.println("]}");
        }
    }
}

public abstract class Command {
    Map<String, Integer> dictionary = new HashMap<>();

    public Command() {
        dictionary.put("EUR", 0);
        dictionary.put("GBP", 1);
        dictionary.put("JPY", 2);
        dictionary.put("CAD", 3);
        dictionary.put("USD", 4);
    }

    public abstract void command(List<String> strings);
}
