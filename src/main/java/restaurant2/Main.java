package restaurant2;

import restaurant2.customer.Customer;
import restaurant2.customer.CustomerRepository;
import restaurant2.customer.CustomerDB;
import restaurant2.delivering.DeliveringService;
import restaurant2.dish.DishDB;
import restaurant2.dish.DishRepository;
import restaurant2.exceptions.AddArrayException;
import restaurant2.exceptions.NotFoundArrayException;
import restaurant2.kitchen.KitchenService;
import restaurant2.order.Order;
import restaurant2.order.OrderDB;
import restaurant2.order.OrderRepository;
import restaurant2.order.OrderStatus;

public class Main {

    public static void main(String[] args) throws NotFoundArrayException, AddArrayException {
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

        //CUSTOMER registration
        Customer customer = new Customer("Vova", 666777, "Kemerovo city, Lenina street 112");
        customerRepository.add(customer);

        //CUSTOMER removed
        int remove = customerArray.removeAll(customerRepository.getByName("Max"));
        System.out.println("Removed customers: " + remove);
        customerArray.print();

        //ORDER
        Array orderDish1 = new Array();
        orderDish1.add(dishRepository.getByName("Borsh"));
        orderDish1.add(dishRepository.getByName("Bread"));
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
