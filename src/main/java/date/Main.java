package date;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.time.Period.between;

public class Main {
    public static void main(String[] args) {

        List<DayOfWeek> dayOfWeeks = new ArrayList<>();
        dayOfWeeks.add(DayOfWeek.SUNDAY);
        dayOfWeeks.add(DayOfWeek.MONDAY);

        generateLocalDateTimeRange(LocalDateTime.parse("1986-04-08T12:30:45"),60, dayOfWeeks);
    }

    private static void generateLocalDateTimeRange(final LocalDateTime from, int days,
                                                   List<DayOfWeek> dayOfWeeks) {

        Stream.iterate(from, dateTime -> dateTime.plusDays(1))
                .limit(days)
                .filter(dateTime -> dayOfWeeks.contains(dateTime.getDayOfWeek()))
                .forEach(System.out::println);
    }
}
