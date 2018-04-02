package streamex;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumberPairs {

    public static void main(String[] args) {

        List<Integer> firstList = Arrays.asList(1, 3, 5);
        List<Integer> secondList = Arrays.asList(2, 4);

        List<int[]> numbersPairs = firstList.stream().
                flatMap(numberFromFirstList -> secondList.stream()
                        .map(numberFromSecondList -> new int[]{numberFromFirstList, numberFromSecondList}))
                .collect(Collectors.toList());

        numbersPairs.stream().forEach(pair -> System.out.println(pair[0] + "," + pair[1]));
    }
}
