package restaurant.customer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import restaurant.Constants;

import java.util.Objects;

@Component
@Scope("prototype")
public class Customer{
    private String name;
    private int phone;
    private String address;

    public Customer() {
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "customer{" +
                "name='" + Constants.ANSI_GREEN + name + Constants.ANSI_RESET +'\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return phone == customer.phone && name.equals(customer.name) && Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, address);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
