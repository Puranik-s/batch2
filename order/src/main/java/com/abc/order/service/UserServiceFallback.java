package com.abc.order.service;


import org.springframework.stereotype.Component;
import com.abc.order.model.Customer;

@Component
public class UserServiceFallback implements UserServiceConsumer {

	@Override
	public Customer getUserDetailsById(int userId) {
		// TODO Auto-generated method stub
		return new Customer();
	}

	
	}

   
