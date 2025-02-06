package codegym.c10.baitap11.service;

import codegym.c10.baitap11.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerImpl implements ICustomerCRUD{
    private List<Customer> customers = new ArrayList<>();

    public CustomerImpl() {
        // Thêm một số khách hàng mẫu
        customers.add(new Customer(1, "Nguyen Van A", 21));
        customers.add(new Customer(2, "Tran Thi B", 22));
        customers.add(new Customer(3, "Le Van C", 30));
    }

    @Override
    public List<Customer> displayAllCustomer() {
        return customers;
    }

    @Override
    public void createCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean updateCustomer(int customerId, Customer updatedCustomer) {
        Customer customer = findById(customerId);
        if (customer != null) {
            customer.setName(updatedCustomer.getName());
            customer.setAge(updatedCustomer.getAge());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(int customerId) {
        return customers.removeIf(customer -> customer.getId() == customerId);
    }

    @Override
    public Customer findById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }
}
