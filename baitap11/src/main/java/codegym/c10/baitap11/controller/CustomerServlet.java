package codegym.c10.baitap11.controller;

import codegym.c10.baitap11.model.Customer;
import codegym.c10.baitap11.service.CustomerImpl;
import codegym.c10.baitap11.service.ICustomerCRUD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "customer", urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {
    private ICustomerCRUD customerService = new CustomerImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = action == null ? "" : action;
        switch (action) {
            case "list":
                showCustomers(req, resp);
                break;
            case "create":
                showFormCreate(req, resp);
                break;
            case "detail":
                showCustomerDetail(req, resp);
                break;
            case "delete":
                deleteCustomer(req, resp);
                break;
            case "edit":
                showFormEdit(req, resp);
                break;
        }
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Customer customer = customerService.findById(id);
        System.out.println("Customer ID: " + id);
        System.out.println("Customer: " + customer);

        if (customer == null) {
            System.out.println("Customer not found for ID: " + id);  // Log khi không tìm thấy khách hàng
            String error = "EDIT - Customer not found for ID: " + id;
            req.setAttribute("error", error);
            req.getRequestDispatcher("/notFound.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("customer", customer);

        req.getRequestDispatcher("/edit.jsp").forward(req, resp);
    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        boolean isDeleted = customerService.deleteCustomer(id);

        if (isDeleted) {
            resp.sendRedirect(req.getContextPath() + "/customers?action=list");
        } else {
            String error = "DELETE - Delete failed!";
            req.setAttribute("error", error);
            req.getRequestDispatcher("/notFound.jsp").forward(req, resp);
        }
    }

    private void showCustomerDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Customer customer = customerService.findById(id);
        if (customer == null) {
            System.out.println("Customer not found for ID: " + id);  // Log khi không tìm thấy khách hàng
            String error = "DETAIL - Customer not found for ID: " + id;
            req.setAttribute("error", error);
            req.getRequestDispatcher("/notFound.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("customer", customer);

        req.getRequestDispatcher("/detail.jsp").forward(req, resp);
    }


    private static void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/create.jsp").forward(req, resp);
    }

    private void showCustomers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customerList = customerService.displayAllCustomer();
        req.setAttribute("customerList", customerList);
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = action == null ? "" : action;

        switch (action) {
            case "create":
                createCustomer(req, resp);
                break;
            case "edit":
                updateCustomer(req, resp);
                break;
        }
    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));

        Customer customer = new Customer(id, name, age);
        customerService.createCustomer(customer);
        resp.sendRedirect(req.getContextPath() + "/customers?action=list");
    }

    private void updateCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));

        System.out.println(id + " " + name + " " + age);
        Customer customer = new Customer(id, name, age);
        boolean isUpdated = customerService.updateCustomer(id, customer);

        if (isUpdated) {
            resp.sendRedirect(req.getContextPath() + "/customers?action=list");
        } else {
            req.setAttribute("error", "Update failed!");
            req.getRequestDispatcher("/edit.jsp").forward(req, resp);
        }
    }
}
