package optional;

import java.util.Optional;

public class Person {

    private Optional<Car> car = Optional.empty();

    public Car getCar() {
        return car.get();
    }

}
