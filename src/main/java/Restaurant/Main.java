package Restaurant;

import Restaurant.Customer.Customer;
import Restaurant.Customer.DBCustomer;
import Restaurant.Delivering.DeliveringService;
import Restaurant.Dish.DishArray;
import Restaurant.Dish.DBDish;
import Restaurant.Kitchen.KitchenService;
import Restaurant.Order.Order;
import Restaurant.Order.DBOrder;
import Restaurant.Order.OrderArray;
import Restaurant.Order.Status;

public class Main {

    public static void main(String[] args) throws MyException {

        DBCustomer dbCustomer = new DBCustomer();
        Array customerArray = dbCustomer.createDB();
        customerArray.print();
        Customer max = new Customer("Max", 666777, "Tomsk city, Kartashova street 44");
        Customer anton = new Customer("Anton", 734455, "Krasnodar city, Lesnaya street 98");
        dbCustomer.add(max);
        dbCustomer.add(anton);
        customerArray.print();

        DBDish dbDish = new DBDish();
        Array dishArray = dbDish.createDB();
        dishArray.print();

        DBOrder dbOrder = new DBOrder();

        DishArray orderDish1 = new DishArray();
        orderDish1.add(dbDish.bread);
        orderDish1.add(dbDish.borsh);

        DishArray orderDish2 = new DishArray();
        orderDish2.add(dbDish.losos);
        orderDish2.add(dbDish.cake);
        orderDish2.add(dbDish.juice);

        Order order1 = dbOrder.createOrder(orderDish1, max);
        Order order2 = dbOrder.createOrder(orderDish2, anton);
        Array orderArray = dbOrder.getArray();
        orderArray.print();

        KitchenService kitchenService = new KitchenService();
        kitchenService.run(order1, dbOrder);
        kitchenService.run(order2, dbOrder);
        orderArray.print();

        DeliveringService deliveringService = new DeliveringService();
        deliveringService.run(order1, dbOrder);
        orderArray.print();

        System.out.println("---REPORTS BY CUSTOMER---");

        OrderArray arrayWithCustomer = dbOrder.getArray();
        OrderArray ordersByCustomer = arrayWithCustomer.getOrdersByCustomer(max, dbOrder.getArray());
        ordersByCustomer.print();

        System.out.println("---REPORTS BY STATUS---");
        OrderArray arrayWithStatus = dbOrder.getArray();
        OrderArray ordersByStatus = arrayWithStatus.getOrdersByStatus(Status.COOKING, dbOrder.getArray());
        ordersByStatus.print();

        System.out.println("test");
    }
}
