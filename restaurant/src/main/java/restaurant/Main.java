package restaurant;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import restaurant.customer.Customer;
import restaurant.customer.CustomerRepository;
import restaurant.delivering.DeliveringService;
import restaurant.dish.Dish;
import restaurant.dish.DishRepository;
import restaurant.exceptions.AddArrayException;
import restaurant.exceptions.NotFoundArrayException;
import restaurant.kitchen.KitchenService;
import restaurant.order.Order;
import restaurant.order.OrderRepository;
import restaurant.order.OrderStatus;

public class Main {

    public static void main(String[] args) throws NotFoundArrayException, AddArrayException {
        //SPRING IOC container
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        DataBase dataBase = context.getBean("dataBase", DataBase.class);
        CustomerRepository customerRepository = context.getBean("customerRepository", CustomerRepository.class);
        DishRepository dishRepository = context.getBean("dishRepository", DishRepository.class);
        OrderRepository orderRepository = context.getBean("orderRepository", OrderRepository.class);

        //CREATE DataBase
        createCustomerDB(context, customerRepository);
        createDishDB(context, dishRepository);

        //PRINT
        customerRepository.getArray().print();
        dishRepository.getArray().print();

        //CUSTOMER removed
        int remove = customerRepository.getArray().removeAll(customerRepository.getByName("Max"));
        System.out.println("Removed customers: " + remove);
        customerRepository.getArray().print();

        //ORDER
        ArrayList orderDish1 = new ArrayList();
        orderDish1.add(dishRepository.getByName("Borsh"));
        orderDish1.add(dishRepository.getByName("Bread"));
        Order order1 = orderRepository.createOrder(orderDish1, customerRepository.getByName("Vika"));
        orderRepository.getArray().print();

        //SERVICES
        Service kitchenService = context.getBean("kitchenService", KitchenService.class);
        kitchenService.run(order1, orderRepository);
        orderRepository.getArray().print();
        kitchenService.finished(order1, orderRepository);
        orderRepository.getArray().print();

        DeliveringService deliveringService = context.getBean("deliveringService", DeliveringService.class);
        deliveringService.run(order1, orderRepository);
        orderRepository.getArray().print();
        deliveringService.finished(order1, orderRepository);
        orderRepository.getArray().print();

        //REPORTS
        System.out.println("---REPORTS BY CUSTOMER---");
        ArrayList ordersByCustomer = orderRepository.getOrdersByCustomer(customerRepository.getByName("Vika"));
        ordersByCustomer.print();

        System.out.println("---REPORTS BY STATUS---");
        ArrayList ordersByStatus = orderRepository.getOrdersByStatus(OrderStatus.DELIVERED);
        ordersByStatus.print();

    }

    private static void createCustomerDB(AnnotationConfigApplicationContext context, CustomerRepository customerRepository) throws AddArrayException {
        Customer customer1 = context.getBean("customer", Customer.class);
        customer1.setName("Jon");
        customer1.setPhone(650333);
        customer1.setAddress("Tomsk city, Lenina street 15");

        Customer customer2 = context.getBean("customer", Customer.class);
        customer2.setName("Mark");
        customer2.setPhone(777505);
        customer2.setAddress("Moskow city, Voikova street 24");

        Customer customer3 = context.getBean("customer", Customer.class);
        customer3.setName("Vika");
        customer3.setPhone(656623);
        customer3.setAddress("Tomsk city, Frunze street 23");

        Customer customer4 = context.getBean("customer", Customer.class);
        customer4.setName("Nikopol");
        customer4.setPhone(900000);
        customer4.setAddress("Vena city, Crinbow street 24");

        Customer max = context.getBean("customer", Customer.class);
        max.setName("Max");
        max.setPhone(666777);
        max.setAddress("Tomsk city, Kartashova street 44");


        Customer anton = context.getBean("customer", Customer.class);
        anton.setName("Anton");
        anton.setPhone(734455);
        anton.setAddress("Krasnodar city, Lesnaya street 98");

        Customer vova = context.getBean("customer", Customer.class);
        vova.setName("Vova");
        vova.setPhone(666777);
        vova.setAddress("Kemerovo city, Lenina street 112");

        customerRepository.add(max);
        customerRepository.add(customer1);
        customerRepository.add(customer2);
        customerRepository.getArray().add(max);
        customerRepository.getArray().add(max);
        customerRepository.add(customer3);
        customerRepository.add(customer4);
        customerRepository.getArray().add(max);
        customerRepository.add(anton);
        customerRepository.getArray().add(max);
        customerRepository.getArray().add(max);
        customerRepository.add(vova);
    }

    private static void createDishDB(AnnotationConfigApplicationContext context, DishRepository dishRepository) throws AddArrayException {
        Dish borsh = context.getBean("dish", Dish.class);
        borsh.setName("Borsh");
        borsh.setCost(150);
        Dish losos = context.getBean("dish", Dish.class);
        losos.setName("Losos");
        losos.setCost(270);
        Dish cake = context.getBean("dish", Dish.class);
        cake.setName("Cake");
        cake.setCost(70);
        Dish juice = context.getBean("dish", Dish.class);
        juice.setName("Juice");
        juice.setCost(50);
        Dish bread = context.getBean("dish", Dish.class);
        bread.setName("Bread");
        bread.setCost(5);
        dishRepository.add(borsh);
        dishRepository.add(losos);
        dishRepository.add(cake);
        dishRepository.add(juice);
        dishRepository.add(bread);
    }
}
