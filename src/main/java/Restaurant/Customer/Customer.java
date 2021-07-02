package Restaurant.Customer;

import java.util.Objects;

public class Customer{
    String name;
    int phone;
    String address;

    public Customer(String name, int phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
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
}
