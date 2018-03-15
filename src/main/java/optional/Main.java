package optional;

import java.util.Optional;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("a", "21");
        properties.setProperty("b", "true");
        properties.setProperty("c", "-13");


        System.out.println(readDuration(properties, "a"));
        System.out.println(readDuration(properties, "b"));
        System.out.println(readDuration(properties, "c"));
    }

    private static int readDuration(final Properties properties, final String key) {
        return Optional
                .ofNullable(properties.getProperty(key))
                .flatMap(Main::parseInt)
                .filter(duration -> duration > 0)
                .orElse(0);
    }

    private static Optional<Integer> parseInt(String value) {
        try {
            return Optional.of(Integer.parseInt(value));
        } catch (NumberFormatException e){
            return Optional.empty();
        }
    }

}
