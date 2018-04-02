package streamex.reduction;

import java.util.Arrays;
import java.util.List;

public class ReducingExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2, 3, 5, 7);

        //Summing all elements of list with initial value = 5
        int sum = numbers.stream().reduce(5, (a, b) -> a + b);
        System.out.println(sum);


        //Summing all elements of list without initial value
        numbers.stream().reduce((a, b) -> a + b).ifPresent(System.out::println);

        //Get maximum element from the list
        numbers.stream().reduce((x, y) -> x < y ? y : x).ifPresent(System.out::println);
        numbers.stream().reduce(Integer::max).ifPresent(System.out::println);

        //Get minimum element from the list
        numbers.stream().reduce((x, y) -> x < y ? x : y).ifPresent(System.out::println);
        numbers.stream().reduce(Integer::min).ifPresent(System.out::println);

    }
}
