package restaurant2.customer;

import restaurant2.Constants;

import java.util.Objects;

public class Customer{
    private String name;
    private int phone;
    private String address;

    public Customer(String name, int phone, String address) {
        this.name = name;
        this.phone = phone;
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
