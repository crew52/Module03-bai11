package codegym.c10.baitap11.service;

import codegym.c10.baitap11.model.Customer;

import java.util.List;

public interface ICustomerCRUD {
    List<Customer> displayAllCustomer();
    void createCustomer(Customer customer);
    boolean updateCustomer(int customerId, Customer updatedCustomer);
    boolean deleteCustomer(int customerId);
    Customer findById(int customerId);
}
