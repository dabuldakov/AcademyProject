package Restaurant.Customer;

import Restaurant.Array;
import Restaurant.Registration;

public class RegistrationCustomer implements Registration {

    private Array array;

    public Array createDB(){
        Array customerArray = new CustomerArray();
        Customer customer1 = new Customer("Jon", 650333, "Tomsk city, Lenina street 15");
        Customer customer2 = new Customer("Mark", 777505, "Moskow city, Voikova street 24");
        Customer customer3 = new Customer("Vika", 656623, "Tomsk city, Frunze street 23");
        Customer customer4 = new Customer("Nikopol", 900000, "Vena city, Crinbow street 24");
//        Customer max = new Customer("Max", 666777, "Tomsk city, Kartashova street 44");
        customerArray.add(customer1);
        customerArray.add(customer2);
        customerArray.add(customer3);
        customerArray.add(customer4);
//        customerArray.add(max);
        array = customerArray;
        return customerArray;
    }

    @Override
    public Array getArray() {
        return array;
    }
}
