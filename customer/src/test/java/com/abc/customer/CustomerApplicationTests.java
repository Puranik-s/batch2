package com.abc.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.customer.entity.Customer;
import com.abc.customer.entity.User;
import com.abc.customer.exception.ResourceNotFoundException;
import com.abc.customer.repository.CustomerRepository;
import com.abc.customer.service.CustomerServiceImpl;


@SpringBootTest(properties="eureka.client.enabled=false")
public class CustomerApplicationTests {

	@InjectMocks
	private CustomerServiceImpl customerService;

	@Mock
	private CustomerRepository customerRepository;

	@Test
	public void testViewCustomerDetailsById() {
		User user = new User();
		Customer customer = new Customer();
		user.setUserId(1);
		user.setUsername("samiksha");
		user.setPassword("1234");
		customer.setFirstName("samiksha");
		customer.setLastName("puranik");
		customer.setMobile("9653462175");
		customer.setEmail("samiksha@gmail.com");
		customer.setAddress("bavdhan");

		when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
		Customer actualObj = customerService.getCustomerById(1);
		assertEquals("samiksha", actualObj.getFirstName());
	}

	@Test
	public void testViewCustomerDetailsByIdWithException() {
		when(customerRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomerById(1));
	}

	@Test
	public void testViewAllCustomers() {
		User user = new User();
		Customer customer = new Customer();
		user.setUserId(1);
		user.setUsername("samiksha");
		user.setPassword("1234");
		customer.setFirstName("samiksha");
		customer.setLastName("puranik");
		customer.setMobile("9653462175");
		customer.setEmail("samiksha@gmail.com");
		customer.setAddress("bavdhan");

		User user1 = new User();
		Customer customer1 = new Customer();
		user1.setUserId(2);
		user1.setUsername("riya");
		user1.setPassword("123");
		customer1.setFirstName("riya");
		customer1.setLastName("patil");
		customer1.setMobile("7093623624");
		customer1.setEmail("riya@gmail.com");
		customer1.setAddress("nagar");

		User user2 = new User();
		Customer customer2 = new Customer();
		user2.setUserId(3);
		user2.setUsername("arpita");
		user2.setPassword("123");
		customer2.setFirstName("arpita");
		customer2.setLastName("sutar");
		customer2.setMobile("7034268624");
		customer2.setEmail("arpita@gmail.com");
		customer2.setAddress("satara");

		List<User> users = new ArrayList<>();
		List<Customer> customers = new ArrayList<>();
		users.add(user);
		users.add(user1);
		users.add(user2);
		customers.add(customer);
		customers.add(customer1);
		customers.add(customer2);

		when(customerRepository.findAll()).thenReturn(customers);

		List<Customer> customerList = customerService.getAllCustomers();
		assertEquals(3, customerList.size());
	}
	@Test
	public void testSaveCustomer() {
		User user3 = new User();
		Customer customer3 = new Customer();
		user3.setUserId(4);
		user3.setUsername("John");
		user3.setPassword("123");
		customer3.setFirstName("John");
		customer3.setLastName("Dixit");
		customer3.setMobile("6789012345");
		customer3.setEmail("john@example.com");
		customer3.setAddress("mumbai");

		when(customerRepository.save(customer3)).thenReturn(customer3);

		Customer savedCustomer = customerService.saveCustomer(customer3);

		assertEquals("John", savedCustomer.getFirstName());
		assertEquals("Dixit", savedCustomer.getLastName());
		assertEquals("6789012345", savedCustomer.getMobile());
		assertEquals("john@example.com", savedCustomer.getEmail());
		assertEquals("mumbai", savedCustomer.getAddress());

		verify(customerRepository, times(1)).save(customer3);
	}

	@Test
	public void testSaveCustomerWithException() {
		User user3 = new User();
		Customer customer3 = new Customer();
		user3.setUserId(4);
		user3.setUsername("John");
		user3.setPassword("123");
		customer3.setFirstName("John");
		customer3.setLastName("Dixit");
		customer3.setMobile("6789012345");
		customer3.setEmail("john@example.com");
		customer3.setAddress("mumbai");

		when(customerRepository.save(customer3)).thenThrow(new RuntimeException("Failed to save customer"));

		assertThrows(RuntimeException.class, () -> customerService.saveCustomer(customer3));
	}

	@Test
	public void testUpdateCustomer() {
	    // Create a customer object
	    Customer customer = new Customer();
	    customer.setUserId(1);
	    customer.setUsername("samiksha");
		customer.setPassword("1234");
	    customer.setFirstName("samiksha");
	    customer.setLastName("puranik");
	    customer.setMobile("9653462175");
	    customer.setEmail("samiksha@gmail.com");
	    customer.setAddress("bavdhan");

	    // Assume customer exists in the database
	    when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

	    // Perform the update
	    Customer updatedCustomer = customerService.updateCustomerr(customer);

	    // Assertions
	    assertEquals("samiksha", updatedCustomer.getFirstName());
	    // Add more assertions as needed

	    // Verify that the save method was called once
	    verify(customerRepository, times(1)).save(customer);
	}
	@Test
	public void testUpdateCustomerWithException() {
	    // Create a customer object

		User user = new User();
	    Customer customer = new Customer();
	    user.setUserId(1);
	    user.setUsername("samiksha");
	    user.setPassword("1234");
	    customer.setFirstName("samiksha");
	    customer.setLastName("puranik");
	    customer.setMobile("9653462175");
	    customer.setEmail("samiksha@gmail.com");
	    customer.setAddress("bavdhan");

	    // Assume customer does not exist in the database
	    when(customerRepository.findById(1)).thenReturn(Optional.empty());
	    // Perform the update and expect an exception
	    assertThrows(ResourceNotFoundException.class, () -> customerService.updateCustomerr(customer));
	}
	@Test
	public void testDeleteCustomerById() {
	    // Create a customer object
		User user = new User();
	    Customer customer = new Customer();
	    user.setUserId(1);
	    user.setUsername("samiksha");
	    user.setPassword("1234");
	    customer.setFirstName("samiksha");
	    customer.setLastName("puranik");
	    customer.setMobile("9653462175");
	    customer.setEmail("samiksha@gmail.com");
	    customer.setAddress("bavdhan");

	    // Assume customer with ID 1 exists in the database
	    when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
	    // Perform the deletion
	    customerService.deleteCustomer(1);
	    // Verify that the delete method was called once with the correct customer
	    verify(customerRepository, times(1)).delete(customer);
	}
	@Test
	public void testDeleteCustomerByIdWithException() {
	    // Assume customer with ID 1 does not exist in the database
	    when(customerRepository.findById(1)).thenReturn(Optional.empty());

	    // Perform the deletion and expect an exception
	    assertThrows(ResourceNotFoundException.class, () -> customerService.deleteCustomer(1));
	}
}