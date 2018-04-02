package streamex.dishes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.toSet;

public class Main {
    public static void main(String[] args) {

        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        printVegetarian(menu);

        printNumberOfDishes(menu);

        printNumberOfDishesWithCollector(menu);

        printTotalCaloriesOfMenu(menu);

        printCaloriesStatistic(menu);

        printAllDishes(menu);

        groupByTypes(menu);

        groupByTypesCaloriesLevel(menu);

        groupByMostCaloriesType(menu);

        printCaloricLevelsByType(menu);
    }

    private static void printCaloricLevelsByType(final List<Dish> menu) {
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream()
                .collect(groupingBy(Dish::getType, mapping(dish -> {
                            if (dish.getCalories() < 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() < 650) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        },
                        toSet())));
        System.out.println(caloricLevelsByType);
    }

    private static void groupByMostCaloriesType(final List<Dish> menu) {
        Map<Dish.Type, Dish> mostCaloricByType = menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get)));
        System.out.println(mostCaloricByType);
    }

    private static void groupByTypes(final List<Dish> menu) {
        menu.stream()
                .collect(groupingBy(Dish::getType))
                .forEach((type, dishes) -> System.out.println(type + dishes.toString()));
    }

    private static void groupByTypesCaloriesLevel(final List<Dish> menu) {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByCaloriesAndType = menu.stream()
                .collect(groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() < 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() > 600) {
                                return CaloricLevel.FAT;
                            } else {
                                return CaloricLevel.NORMAL;
                            }
                        })));
        System.out.println(dishesByCaloriesAndType);
    }

    private static void printAllDishes(final List<Dish> menu) {
        System.out.println(menu.stream()
                .map(Dish::getName)
                .collect(joining(", ")));
    }

    private static void printCaloriesStatistic(final List<Dish> menu) {
        System.out.println(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)));
    }

    private static void printTotalCaloriesOfMenu(final List<Dish> menu) {
        System.out.println(menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories)));
    }

    private static void printNumberOfDishesWithCollector(final List<Dish> menu) {
        System.out.println(menu.stream().collect(Collectors.counting()));
    }

    private static void printNumberOfDishes(final List<Dish> menu) {
        menu.stream()
                .mapToInt(dish -> 1)
                .reduce(Integer::sum)
                .ifPresent(System.out::println);
    }

    private static void printVegetarian(List<Dish> menu) {
        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(System.out::println);
    }
}
