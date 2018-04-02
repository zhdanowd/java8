package streamex.tradersoperations;


import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //Find all transactions in the year 2011 and sort them by value (small to high).
        getTransactionsByYear(transactions, 2011).stream().forEach(System.out::println);

        //What are all the unique cities where the traders work
        getCitiesOfTraders(transactions).forEach(System.out::println);

        //Find all traders from Cambridge and sort them by name
        getTradersFromCity(transactions, "Cambridge").forEach(System.out::println);

        //Return a string of all traders’ names sorted alphabetically.
        System.out.println(getSortedTradersName(transactions));

        //Are any traders based in Milan?
        System.out.println(areAnyTradersFromCity(transactions, "Milan"));

        //Print all transactions’ values from the traders living in Cambridge.
        printAllTransactionsFromTradersFromCity(transactions, "Cambridge");

        //What’s the highest value of all the transactions?
        System.out.println(maxTransaction(transactions));

        //Find the transaction with the smallest value
        System.out.println(minTransaction(transactions));
    }

    public static List<Transaction> getTransactionsByYear(List<Transaction> transactions, int year) {
        return transactions.stream()
                .filter(transaction -> transaction.getYear() == year)
                .sorted(comparingInt(Transaction::getValue))
                .collect(toList());
    }

    public static List<String> getCitiesOfTraders(List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());
    }

    public static List<Trader> getTradersFromCity(List<Transaction> transactions, String city) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals(city))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
    }

    public static String getSortedTradersName(List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining());
    }

    public static Boolean areAnyTradersFromCity(List<Transaction> transactions, String city) {
        return transactions.stream()
                .anyMatch(transaction -> transaction.getTrader()
                        .getCity()
                        .equals(city));
    }

    public static void printAllTransactionsFromTradersFromCity(List<Transaction> transactions, String city) {
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals(city))
                .forEach(transaction -> System.out.println(transaction.getTrader() + " transaction value:" +
                        transaction.getValue()));
    }

    public static Transaction maxTransaction(List<Transaction> transactions) {
        return transactions.stream()
                .max(comparingInt(Transaction::getValue))
                .get();
    }

    public static Integer minTransaction(List<Transaction> transactions) {
        return transactions.stream()
                .mapToInt(Transaction::getValue)
                .min()
                .getAsInt();
    }

}
