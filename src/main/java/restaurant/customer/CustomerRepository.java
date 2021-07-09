package restaurant.customer;

import restaurant.ArrayList;
import restaurant.exceptions.NotFoundArrayException;
import restaurant.Registration;

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

/*        Object[] customers = customerArrayList.getArrayList();
        for (Object o : customers) {
            Customer customer = (Customer) o;
            if (customer.getName().equals(name)) {
                return customer;
            }
        }*/
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
