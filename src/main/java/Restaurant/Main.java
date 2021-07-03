package Restaurant;

import Restaurant.Customer.Customer;
import Restaurant.Customer.CustomerRepository;
import Restaurant.Customer.CustomerDB;
import Restaurant.Delivering.DeliveringService;
import Restaurant.Dish.DishDB;
import Restaurant.Dish.DishRepository;
import Restaurant.Exceptions.AddArrayException;
import Restaurant.Exceptions.NotFoundArrayException;
import Restaurant.Kitchen.KitchenService;
import Restaurant.Order.Order;
import Restaurant.Order.OrderDB;
import Restaurant.Order.OrderRepository;
import Restaurant.Order.OrderStatus;

public class Main {

    public static void main(String[] args) throws NotFoundArrayException, AddArrayException {

        CustomerDB customerDB = new CustomerDB();
        Array customerArray = customerDB.getArray();
        CustomerRepository customerRepository = new CustomerRepository(customerArray);

        DishDB dishDB = new DishDB();
        Array dishArray = dishDB.createDB();
        DishRepository dishRepository = new DishRepository(dishArray);

        OrderDB orderDB = new OrderDB();
        Array orderArray = orderDB.getArray();
        OrderRepository orderRepository = new OrderRepository(orderArray);

        customerArray.print();
        dishArray.print();
        orderArray.print();

        Customer max2 = new Customer("Max", 666777, "Tomsk city, Kartashova street 44");
        Customer max3 = new Customer("Max", 666777, "Tomsk city, Kartashova street 44");
        customerArray.add(max2);
        customerArray.add(max3);
        customerArray.print();
        int remove = customerArray.removeAll(max2);
//        boolean remove = customerArray.remove(8);
        System.out.println("Removed customers number: " + remove);
        customerArray.print();

        Array orderDish1 = new Array();
        orderDish1.add(dishRepository.getByName("Borsh"));
        orderDish1.add(dishRepository.getByName("Bread"));
        Array orderDish2 = new Array();
        orderDish2.add(dishRepository.getByName("Losos"));
        orderDish2.add(dishRepository.getByName("Juice"));
        orderDish2.add(dishRepository.getByName("Bread"));
        orderDish2.add(dishRepository.getByName("Cake"));
        Order order1 = orderDB.createOrder(orderDish1, customerRepository.getByName("Vika"));
        Order order2 = orderDB.createOrder(orderDish2, customerRepository.getByName("Anton"));

        orderArray.print();

        KitchenService kitchenService = new KitchenService();
        kitchenService.run(order1, orderDB);
        kitchenService.run(order2, orderDB);
        orderArray.print();

        DeliveringService deliveringService = new DeliveringService();
        deliveringService.run(order1, orderDB);
        orderArray.print();

        System.out.println("---REPORTS BY CUSTOMER---");
        Array ordersByCustomer = orderRepository.getOrdersByCustomer(customerRepository.getByName("Vika"));
        ordersByCustomer.print();

        System.out.println("---REPORTS BY STATUS---");
        Array ordersByStatus = orderRepository.getOrdersByStatus(OrderStatus.COOKING);
        ordersByStatus.print();

    }
}
