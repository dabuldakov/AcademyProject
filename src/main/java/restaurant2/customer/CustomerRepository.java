package restaurant2.customer;

import restaurant2.Array;
import restaurant2.exceptions.NotFoundArrayException;
import restaurant2.Registration;

public class CustomerRepository implements Registration {
    private Array customerArray;

    public CustomerRepository(CustomerDB customerDB) {
        customerArray = customerDB.getArray();
    }

    public Customer getByName(String name) throws NotFoundArrayException {

        Object[] customers = customerArray.getArray();
        for (Object o : customers) {
            Customer customer = (Customer) o;
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        throw new NotFoundArrayException("Name not found in CustomerDB.");
    }

    @Override
    public Array getArray() {
        return customerArray;
    }

    @Override
    public void setArray(Array array) {
        customerArray = array;
    }
}
