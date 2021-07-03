package Restaurant.Customer;

import Restaurant.Array;
import Restaurant.Exceptions.NotFoundArrayException;

public class CustomerRepository {
    private Array customerArray;

    public CustomerRepository(Array array) {
        customerArray = array;
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
}
