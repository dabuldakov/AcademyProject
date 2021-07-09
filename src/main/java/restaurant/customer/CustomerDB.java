package restaurant.customer;

import restaurant.ArrayList;

public class CustomerDB{

    private ArrayList arrayList;

    public CustomerDB() {
        createDB();
    }

    private void createDB(){
        ArrayList customerArrayList = new ArrayList();
        Customer customer1 = new Customer("Jon", 650333, "Tomsk city, Lenina street 15");
        Customer customer2 = new Customer("Mark", 777505, "Moskow city, Voikova street 24");
        Customer customer3 = new Customer("Vika", 656623, "Tomsk city, Frunze street 23");
        Customer customer4 = new Customer("Nikopol", 900000, "Vena city, Crinbow street 24");
        Customer max = new Customer("Max", 666777, "Tomsk city, Kartashova street 44");
        Customer anton = new Customer("Anton", 734455, "Krasnodar city, Lesnaya street 98");
        customerArrayList.add(customer1);
        customerArrayList.add(customer2);
        customerArrayList.add(customer3);
        customerArrayList.add(customer4);
        customerArrayList.add(max);
        customerArrayList.add(anton);
        arrayList = customerArrayList;
    }

    public ArrayList getArrayList() {
        return arrayList;
    }
}
