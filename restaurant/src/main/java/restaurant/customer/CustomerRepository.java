package restaurant.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import restaurant.ArrayList;
import restaurant.Registration;
import restaurant.exceptions.NotFoundArrayException;

@Component
public class CustomerRepository implements Registration {
    private ArrayList customerArrayList;

    public CustomerRepository(CustomerDB customerDB) {
        customerArrayList = customerDB.getArrayList();
    }

    public Customer getByName(String name) throws NotFoundArrayException {

        for (int i = 0; i < customerArrayList.length(); i++) {
            Customer customer = (Customer) customerArrayList.get(i);
            if (customer.getName().equals(name))
                return customer;
        }
        throw new NotFoundArrayException("Name not found in CustomerDB.");
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
