package restaurant.dish;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import restaurant.Constants;

import java.util.Objects;

@Component
@Scope("prototype")
public class Dish {
    private String name;
    private int cost;

    public Dish() {
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
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
        return "dish{" +
                "name='" + Constants.ANSI_PURPLE + name + Constants.ANSI_RESET + '\'' +
                ", cost=" + cost +
                '}';
    }
}
