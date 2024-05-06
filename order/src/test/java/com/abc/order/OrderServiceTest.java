package com.abc.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.order.entity.Order;
import com.abc.order.entity.OrderItem;
import com.abc.order.exception.ResourceNotFoundException;
import com.abc.order.repository.OrderRepository;
import com.abc.order.service.OrderServiceImpl;


@SpringBootTest
public class OrderServiceTest {
	@InjectMocks
	private OrderServiceImpl orderService;
	
	@Mock
	private OrderRepository orderRepository;
	
	@Test
	public void testUpdateOrder() {
	   
		Order order= new Order();
		OrderItem orderItem = new OrderItem();
		order.setOrderId(1);
		order.setOrderTotal(100);
		order.setOrderDate(LocalDate.of(2024,05,02));
		order.setOrderStatus("pending");
		orderItem.setOrderItemId(1);
		orderItem.setQuantity(2);
		orderItem.setMedieId(3);
		orderItem.setItemTotal(100);

	    // Assume order exists in the database
	    when(orderRepository.findById(1)).thenReturn(Optional.of(order));

	    // Perform the update
	    Order updatedOrder = orderService.updateOrder(order);

	    // Assertions
	    assertEquals(100, updatedOrder.getOrderTotal());
	    // Add more assertions as needed

	    // Verify that the save method was called once
	    verify(orderRepository, times(1)).save(order);
	}

	@Test
	public void testUpdateOrderWithException() {
	    // Create a order object
		
		Order order= new Order();
		OrderItem orderItem = new OrderItem();
		order.setOrderId(1);
		order.setOrderTotal(100);
		order.setOrderDate(LocalDate.of(2024,05,02));
		order.setOrderStatus("pending");
		orderItem.setOrderItemId(1);
		orderItem.setQuantity(2);
		orderItem.setMedieId(3);
		orderItem.setItemTotal(100);

	    // Assume order does not exist in the database
	    when(orderRepository.findById(1)).thenReturn(Optional.empty());

	    // Perform the update and expect an exception
	    assertThrows(ResourceNotFoundException.class, () -> orderService.updateOrder(order));
	}

	@Test
	public void testDeleteOrderById() {
	    // Create a order object

		Order order= new Order();
		OrderItem orderItem = new OrderItem();
		order.setOrderId(1);
		order.setOrderTotal(100);
		order.setOrderDate(LocalDate.of(2024,05,02));
		order.setOrderStatus("pending");
		orderItem.setOrderItemId(1);
		orderItem.setQuantity(2);
		orderItem.setMedieId(3);
		orderItem.setItemTotal(100);

	    // Assume order with ID 1 exists in the database
	    when(orderRepository.findById(1)).thenReturn(Optional.of(order));

	    // Perform the deletion
	    orderService.deleteOrder(1);

	    // Verify that the delete method was called once with the correct order
	    verify(orderRepository, times(1)).delete(order);
	}

	@Test
	public void testDeleteOrderByIdWithException() {
	    // Assume order with ID 1 does not exist in the database
	    when(orderRepository.findById(1)).thenReturn(Optional.empty());

	    // Perform the deletion and expect an exception
	    assertThrows(ResourceNotFoundException.class, () -> orderService.deleteOrder(1));
	}
}