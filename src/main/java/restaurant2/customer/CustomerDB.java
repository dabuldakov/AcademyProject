package restaurant2.customer;

import restaurant2.Array;

public class CustomerDB{

    private Array array;

    public CustomerDB() {
        createDB();
    }

    private void createDB(){
        Array customerArray = new Array();
        Customer customer1 = new Customer("Jon", 650333, "Tomsk city, Lenina street 15");
        Customer customer2 = new Customer("Mark", 777505, "Moskow city, Voikova street 24");
        Customer customer3 = new Customer("Vika", 656623, "Tomsk city, Frunze street 23");
        Customer customer4 = new Customer("Nikopol", 900000, "Vena city, Crinbow street 24");
        Customer max = new Customer("Max", 666777, "Tomsk city, Kartashova street 44");
        Customer anton = new Customer("Anton", 734455, "Krasnodar city, Lesnaya street 98");
        customerArray.add(customer1);
        customerArray.add(customer2);
        customerArray.add(customer3);
        customerArray.add(customer4);
        customerArray.add(max);
        customerArray.add(anton);
        array = customerArray;
    }

    public Array getArray() {
        return array;
    }
}
