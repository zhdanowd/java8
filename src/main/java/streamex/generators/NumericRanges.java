package streamex.generators;

import java.util.stream.IntStream;

public class NumericRanges {
    public static void main(String[] args) {
        System.out.println(IntStream.rangeClosed(1, 100).filter(number -> number % 2 == 0).sum());
    }
}
