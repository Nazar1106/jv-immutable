package core.basesyntax;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Make this class immutable. See requirements in task description.
 */
public final class Car {
    private final int year;
    private final String color;
    private final List<Wheel> wheels;
    private final Engine engine;

    //implement this class
    public Car(int year, String color, List<Wheel> wheels, Engine engine) {
        this.year = year;
        this.color = color;
        List<Wheel> deepCopy = new ArrayList<>();
        for (Wheel wheel : wheels) {
            deepCopy.add(wheel.clone()); // Клонування кожного об'єкта Wheel
        }
        this.wheels = Collections.unmodifiableList(deepCopy);
        this.engine = engine != null ? engine.clone() : null;
    }

    @Override
    public String toString() {
        return "Car{"
                + "year="
                + year
                + ", color='"
                + color
                + '\''
                + ", wheels="
                + wheels
                + ", engine="
                + engine
                + '}';
    }

    Car changeEngine(Engine engine) {
        return new Car(year, color, wheels, engine.clone());
    }

    Car changeColor(String newColor) {
        return new Car(year, newColor, wheels, engine.clone());
    }

    Car addWheel(Wheel newWheel) {
        List<Wheel> updateList = new ArrayList<>(getWheels());
        updateList.add(newWheel);
        return new Car(getYear(), getColor(), updateList, engine.clone());
    }

    public int getYear() {
        return year; //todo: no need copy because that's not object!
    }

    public String getColor() {
        return color;
    }

    public List<Wheel> getWheels() {
        List<Wheel> deepCopy = new ArrayList<>();
        for (Wheel wheel : wheels) {
            deepCopy.add(wheel.clone());
        }
        return deepCopy;
    }

    public Engine getEngine() {
        if (engine == null) {
            return null;
        }
        return engine.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return getYear() == car.getYear() && Objects.equals(getColor(), car.getColor())
                && Objects.equals(getWheels(), car.getWheels())
                && Objects.equals(getEngine(), car.getEngine());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getYear(), getColor(), getWheels(), getEngine());
    }
}
