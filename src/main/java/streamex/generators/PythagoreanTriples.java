package streamex.generators;

import java.util.stream.IntStream;

public class PythagoreanTriples {
    public static void main(String[] args) {
        IntStream.rangeClosed(1, 30)
                .boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 30)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(triangle -> triangle[2] % 1 == 0))
                .forEach(triangle -> System.out.println(triangle[0] + "," + triangle[1] + "," + triangle[2]));

    }
}
