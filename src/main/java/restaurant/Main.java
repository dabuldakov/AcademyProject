package restaurant;

import restaurant.customer.Customer;
import restaurant.customer.CustomerRepository;
import restaurant.customer.CustomerDB;
import restaurant.delivering.DeliveringService;
import restaurant.dish.DishDB;
import restaurant.dish.DishRepository;
import restaurant.exceptions.AddArrayException;
import restaurant.exceptions.NotFoundArrayException;
import restaurant.kitchen.KitchenService;
import restaurant.order.Order;
import restaurant.order.OrderDB;
import restaurant.order.OrderRepository;
import restaurant.order.OrderStatus;

public class Main {

    public static void main(String[] args) throws NotFoundArrayException, AddArrayException {
        //DATABASE
        CustomerDB customerDB = new CustomerDB();
        CustomerRepository customerRepository = new CustomerRepository(customerDB);
        ArrayList customerArrayList = customerDB.getArrayList();

        DishDB dishDB = new DishDB();
        DishRepository dishRepository = new DishRepository(dishDB);
        ArrayList dishArrayList = dishDB.createDB();

        OrderDB orderDB = new OrderDB();
        OrderRepository orderRepository = new OrderRepository(orderDB);
        ArrayList orderArrayList = orderDB.getArrayList();

        //PRINT
        customerArrayList.print();
        dishArrayList.print();
        orderArrayList.print();

        //CUSTOMER registration
        Customer customer = new Customer("Vova", 666777, "Kemerovo city, Lenina street 112");
        customerRepository.add(customer);

        //CUSTOMER removed
        int remove = customerArrayList.removeAll(customerRepository.getByName("Max"));
        System.out.println("Removed customers: " + remove);
        customerArrayList.print();

        //ORDER
        ArrayList orderDish1 = new ArrayList();
        orderDish1.add(dishRepository.getByName("Borsh"));
        orderDish1.add(dishRepository.getByName("Bread"));
        Order order1 = orderRepository.createOrder(orderDish1, customerRepository.getByName("Vika"));
        orderArrayList.print();

        //SERVICES
        KitchenService kitchenService = new KitchenService();
        kitchenService.run(order1, orderRepository);
        orderArrayList.print();
        kitchenService.finished(order1, orderRepository);
        orderArrayList.print();

        DeliveringService deliveringService = new DeliveringService();
        deliveringService.run(order1, orderRepository);
        orderArrayList.print();
        deliveringService.finished(order1, orderRepository);
        orderArrayList.print();

        //REPORTS
        System.out.println("---REPORTS BY CUSTOMER---");
        ArrayList ordersByCustomer = orderRepository.getOrdersByCustomer(customerRepository.getByName("Vika"));
        ordersByCustomer.print();

        System.out.println("---REPORTS BY STATUS---");
        ArrayList ordersByStatus = orderRepository.getOrdersByStatus(OrderStatus.DELIVERED);
        ordersByStatus.print();

    }
}
