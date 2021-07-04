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

    public static void main(String[] args) throws NotFoundArrayException {
        //DATABASE
        CustomerDB customerDB = new CustomerDB();
        CustomerRepository customerRepository = new CustomerRepository(customerDB);
        Array customerArray = customerDB.getArray();

        DishDB dishDB = new DishDB();
        DishRepository dishRepository = new DishRepository(dishDB);
        Array dishArray = dishDB.createDB();

        OrderDB orderDB = new OrderDB();
        OrderRepository orderRepository = new OrderRepository(orderDB);
        Array orderArray = orderDB.getArray();

        //PRINT
        customerArray.print();
        dishArray.print();
        orderArray.print();

        int remove = customerArray.removeAll(customerRepository.getByName("Max"));
        System.out.println("Removed customers: " + remove);
        customerArray.print();

        //ORDER
        Array orderDish1 = new Array();
        orderDish1.add(dishRepository.getByName("Borsh"));
        orderDish1.add(dishRepository.getByName("Bread"));
        Array orderDish2 = new Array();
        orderDish2.add(dishRepository.getByName("Losos"));
        orderDish2.add(dishRepository.getByName("Juice"));
        orderDish2.add(dishRepository.getByName("Bread"));
        orderDish2.add(dishRepository.getByName("Cake"));
        Order order1 = orderRepository.createOrder(orderDish1, customerRepository.getByName("Vika"));
        orderArray.print();

        //SERVICES
        KitchenService kitchenService = new KitchenService();
        kitchenService.run(order1, orderRepository);
        orderArray.print();
        kitchenService.finished(order1, orderRepository);
        orderArray.print();

        DeliveringService deliveringService = new DeliveringService();
        deliveringService.run(order1, orderRepository);
        orderArray.print();
        deliveringService.finished(order1, orderRepository);
        orderArray.print();

        //REPORTS
        System.out.println("---REPORTS BY CUSTOMER---");
        Array ordersByCustomer = orderRepository.getOrdersByCustomer(customerRepository.getByName("Vika"));
        ordersByCustomer.print();

        System.out.println("---REPORTS BY STATUS---");
        Array ordersByStatus = orderRepository.getOrdersByStatus(OrderStatus.DELIVERED);
        ordersByStatus.print();

    }
}
