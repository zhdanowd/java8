package streamex.generators;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Fibonacci {
    public static void main(String[] args) {


        generateStatelessFibonacciSequence();

        generateStatefullFibonacciSequence();

    }

    private static void generateStatefullFibonacciSequence() {
        IntStream.generate(new IntSupplier() {

            private int previousNumber = 0;
            private int currentNumber = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = previousNumber;
                previousNumber = currentNumber;
                currentNumber = oldPrevious + previousNumber;
                return oldPrevious;
            }
        }).limit(20)
                .forEach(System.out::println);
    }

    private static void generateStatelessFibonacciSequence() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);
    }
}
