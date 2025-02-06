package codegym.c10.baitap11.service;

import codegym.c10.baitap11.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceDB implements ICustomerCRUD {
    private List<Customer> customers = new ArrayList<>();

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Sử dụng "com.mysql.cj.jdbc.Driver" thay cho "com.mysql.jdbc.Driver"
            String url = "jdbc:mysql://localhost:3306/test11_db";
            String username = "root";
            String password = "123456789";

            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Database connection error", e); // Đưa ra thông báo chi tiết về lỗi
        }
    }

    @Override
    public List<Customer> displayAllCustomer() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setAge(rs.getInt("age"));
                customers.add(customer);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching customers", e); // Cải thiện thông báo lỗi
        }

        return customers;
    }

    @Override
    public void createCustomer(Customer customer) {
        String query = "INSERT INTO customer (name, age) VALUES (?, ?)";

        // Sử dụng try-with-resources để tự động đóng tài nguyên
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, customer.getName());
            stmt.setInt(2, customer.getAge());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error creating customer", e); // Cải thiện thông báo lỗi
        }
    }

    @Override
    public boolean updateCustomer(int customerId, Customer updatedCustomer) {
        String query = "UPDATE customer SET name = ?, age = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, updatedCustomer.getName());
            stmt.setInt(2, updatedCustomer.getAge());
            stmt.setInt(3, customerId);

            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0; // Trả về true nếu có dòng bị cập nhật

        } catch (SQLException e) {
            throw new RuntimeException("Error updating customer", e);
        }
    }

    @Override
    public boolean deleteCustomer(int customerId) {
        String sql = "DELETE FROM customer WHERE id = ?;";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0; // Trả về true nếu có dòng bị xóa

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer findById(int customerId) {
        Customer customer = null;
        String query = "SELECT * FROM customer WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer();
                    customer.setId(rs.getInt("id"));
                    customer.setName(rs.getString("name"));
                    customer.setAge(rs.getInt("age"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding customer by ID", e);
        }

        return customer;
    }
}
