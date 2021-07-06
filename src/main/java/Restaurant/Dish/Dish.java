package Restaurant.Dish;

import java.util.Objects;

public class Dish {
    private String name;
    private int cost;
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";

    Dish(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return cost == dish.cost && name.equals(dish.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + ANSI_PURPLE + name + ANSI_RESET + '\'' +
                ", cost=" + cost +
                '}';
    }
}
