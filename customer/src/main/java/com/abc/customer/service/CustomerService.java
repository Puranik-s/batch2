package com.abc.customer.service;

import java.util.List;
import com.abc.customer.entity.Customer;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    Customer getCustomerById(int userId);

    List<Customer> getAllCustomers();

    Customer updateCustomerr(Customer customer);

    void deleteCustomer(int id);

}