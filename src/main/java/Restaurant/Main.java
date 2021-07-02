package Restaurant;

import Restaurant.Customer.Customer;
import Restaurant.Customer.CustomerArray;
import Restaurant.Customer.RegistrationCustomer;
import Restaurant.Dish.Dish;
import Restaurant.Dish.DishArray;
import Restaurant.Dish.RegistrationDish;
import Restaurant.Kitchen.KitchenService;
import Restaurant.Order.Order;
import Restaurant.Order.RegistrationOrder;

public class Main {

    public static void main(String[] args) throws MyException {

        RegistrationCustomer registrationCustomer = new RegistrationCustomer();
        Array customerArray = registrationCustomer.createDB();
        customerArray.print();
        Customer max = new Customer("Max", 666777, "Tomsk city, Kartashova street 44");
        registrationCustomer.add(max);
        customerArray.print();

        RegistrationDish registrationDish = new RegistrationDish();
        Array dishArray = registrationDish.createDB();
        dishArray.print();

        RegistrationOrder registrationOrder = new RegistrationOrder();

        DishArray orderDish1 = new DishArray();
        orderDish1.add(registrationDish.bread);
        orderDish1.add(registrationDish.borsh);

        DishArray orderDish2 = new DishArray();
        orderDish2.add(registrationDish.losos);
        orderDish2.add(registrationDish.cake);
        orderDish2.add(registrationDish.juice);

        Order order1 = registrationOrder.createOrder(orderDish1, max);
        Order order2 = registrationOrder.createOrder(orderDish2, max);
        Array orderArray = registrationOrder.getArray();
        orderArray.print();

        KitchenService.cocking(order1, registrationOrder);
        orderArray.print();

    }
}
