package applefilter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

public class Main {

    public static void main(String[] args) {

        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("green", 122.5));
        apples.add(new Apple("green", 122.5));
        apples.add(new Apple("red", 122));
        apples.add(new Apple("yellow", 300));
        apples.add(new Apple("red", 155));
        apples.add(new Apple("green", 155.5));
        apples.add(new Apple("red", 140));

        Predicate<Apple> greenApples = apple -> apple.getColor().equals("green");
        Predicate<Apple> notGreenApples = greenApples.negate();

        System.out.println(filter(apples, greenApples));
        System.out.println(filter(apples, notGreenApples));

        System.out.println(filter(apples, apple -> apple.getWeight() >= 150));
        System.out.println(filter(apples, apple -> apple.getWeight() < 150 && apple.getColor().equals("green")));

        apples.sort(comparing(Apple::getColor).thenComparingDouble(Apple::getWeight).reversed());
        System.out.println(apples);

    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {

        List<T> results = new ArrayList<>();

        for (T item : list) {
            if (predicate.test(item)) {
                results.add(item);
            }

        }
        return results;
    }
}
