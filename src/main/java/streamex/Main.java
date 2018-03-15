package streamex;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

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

        Function<String, Integer> function = Integer:: parseInt;

        Class<?> clazz = function.getClass();
        function.apply("12");


        getTransactionsByYear(transactions, 2011).stream().forEach(System.out::println);

        getCitiesOfTraders(transactions).forEach(System.out::println);

        getTradersFromCity(transactions, "Cambridge").forEach(System.out::println);

        System.out.println(getSortedTradersName(transactions));

        System.out.println(areAnyTradersFromCity(transactions, "Milan"));

        printAllTransactionsFromTradersFromCity(transactions, "Cambridge");

        System.out.println(maxTransaction(transactions));

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

    public static Transaction minTransaction(List<Transaction> transactions) {
        return transactions.stream()
                .min(comparingInt(Transaction::getValue))
                .get();
    }




}
