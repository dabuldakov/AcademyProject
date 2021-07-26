package restaurant.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import restaurant.ArrayList;
import restaurant.DataBase;
import restaurant.Registration;
import restaurant.exceptions.NotFoundArrayException;

import javax.annotation.PostConstruct;

@Component
public class CustomerRepository implements Registration {

    private ArrayList customerArrayList;

    @Autowired
    public CustomerRepository(DataBase dataBase) {
        customerArrayList = dataBase.getMap().get("customerDataBase");
    }

    public Customer getByName(String name) throws NotFoundArrayException {

        for (int i = 0; i < customerArrayList.length(); i++) {
            Customer customer = (Customer) customerArrayList.get(i);
            if (customer.getName().equals(name))
                return customer;
        }
        throw new NotFoundArrayException("Name not found in CustomerDB.");
    }

    public ArrayList getByNameAll(String name) throws NotFoundArrayException {

        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < customerArrayList.length(); i++) {
            Customer customer = (Customer) customerArrayList.get(i);
            if (customer.getName().equals(name))
                arrayList.add(customer);
        }
        if (arrayList.length() == 0)
            throw new NotFoundArrayException("Name not found in CustomerDB.");
        return arrayList;
    }

    @Override
    public ArrayList getArray() {
        return customerArrayList;
    }

    @Override
    public void setArray(ArrayList arrayList) {
        customerArrayList = arrayList;
    }
}
