package org.poo.cb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args == null) {
            System.out.println("Running Main");
        } else {
            String path = "src/main/resources/" + args[2];
            AdminSingleton.getInstance().users = new ArrayList<>();
            try (BufferedReader r = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = r.readLine()) != null) {
                    Command command;
                    List<String> commandArguments = new ArrayList<>(List.of(line.split(" ")));
                    String commandName = commandArguments.get(0) + " " + commandArguments.get(1);
                    if (commandName.equals("CREATE USER")) {
                        command = new CreateUser();
                    } else if (commandName.equals("BUY STOCKS")) {
                        command = new BuyStocks();
                    } else if (commandName.equals("ADD FRIEND")) {
                        command = new AddFriend();
                    } else if (commandName.equals("ADD ACCOUNT")) {
                        command = new AddAccount();
                    } else if (commandName.equals("ADD MONEY")) {
                        command = new AddMoney();
                    } else if (commandName.equals("EXCHANGE MONEY")) {
                        command = new ExchangeMoney();
                    } else if (commandName.equals("TRANSFER MONEY")) {
                        command = new TransferMoney();
                    } else if (commandName.equals("LIST USER")) {
                        command = new ListUser();
                    } else if (commandName.equals("LIST PORTFOLIO")) {
                        command = new ListPortfolio();
                    } else {
                        command = new RecommendedStocks();
                    }
                    command.command(commandArguments.subList(2, commandArguments.size()));
                }
            } catch (IOException error) {
                System.out.println(error.toString());
            }
        }
    }
}