package codegym.c10.baitap11.service;

import codegym.c10.baitap11.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceDB implements ICustomerCRUD{
    private List<Customer> customers = new ArrayList<>();
    private Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test11_db";
            String username = "root";
            String password = "123456789";
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    @Override
    public List<Customer> displayAllCustomer() {
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customer;");
            ResultSet rs = stmt.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setAge(rs.getInt("age"));
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createCustomer(Customer customer) {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO customer (name, age) VALUES (?, ?);");
            stmt.setString(1, customer.getName());
            stmt.setInt(2, customer.getAge());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateCustomer(int customerId, Customer updatedCustomer) {
        return false;
    }

    @Override
    public boolean deleteCustomer(int customerId) {
        return false;
    }

    @Override
    public Customer findById(int customerId) {
        Connection conn = getConnection();
        Customer customer = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customer WHERE id = ?;");
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setAge(rs.getInt("age"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return customer;
    }
}
