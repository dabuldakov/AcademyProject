package restaurant;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import restaurant.customer.Customer;
import restaurant.customer.CustomerDB;
import restaurant.customer.CustomerRepository;
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
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        //DATABASE
        CustomerRepository customerRepository = context.getBean("customerRepository", CustomerRepository.class);
        createDB(context, customerRepository);

        DishDB dishDB = new DishDB();
        DishRepository dishRepository = new DishRepository(dishDB);
        ArrayList dishArrayList = dishDB.createDB();

        OrderDB orderDB = new OrderDB();
        OrderRepository orderRepository = new OrderRepository(orderDB);
        ArrayList orderArrayList = orderDB.getArrayList();

        //PRINT
        customerRepository.getArray().print();
        dishArrayList.print();
        orderArrayList.print();

        //CUSTOMER registration
        Customer customer = new Customer();
        customer.setName("Vova");
        customer.setPhone(666777);
        customer.setAddress("Kemerovo city, Lenina street 112");
        customerRepository.add(customer);

        //CUSTOMER removed
        int remove = customerRepository.getArray().removeAll(customerRepository.getByName("Max"));
        System.out.println("Removed customers: " + remove);
        customerRepository.getArray().print();

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

    private static void createDB(AnnotationConfigApplicationContext context, CustomerRepository customerRepository) throws AddArrayException {
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

        customerRepository.add(customer1);
        customerRepository.add(customer2);
        customerRepository.add(customer3);
        customerRepository.add(customer4);
        customerRepository.add(max);
        customerRepository.add(anton);
    }
}
