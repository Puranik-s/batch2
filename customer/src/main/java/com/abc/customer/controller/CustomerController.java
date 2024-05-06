package com.abc.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.abc.customer.entity.Customer;
import com.abc.customer.service.CustomerService;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @PostMapping("/save")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
	public ResponseEntity<Customer> fetchUserDetails(@PathVariable("id") int userId) {
		Customer customer = customerService.getCustomerById(userId);
			return new ResponseEntity<>(customer,HttpStatus.OK);
	}
    
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    
	 @PutMapping("/update") public ResponseEntity<Customer> editUser(@RequestBody Customer customer) {
		 customerService.updateCustomerr(customer); ResponseEntity<Customer> responseEntity =
	  new ResponseEntity<>(customer,HttpStatus.OK); return responseEntity; }
	  
	  @DeleteMapping("/{id}") public ResponseEntity<Void>
	  deleteUser(@PathVariable("id") int id) { customerService.deleteCustomer(id);
	  ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
	  return responseEntity; }
}
