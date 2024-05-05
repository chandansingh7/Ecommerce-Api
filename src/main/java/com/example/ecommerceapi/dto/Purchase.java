package com.example.ecommerceapi.dto;

import com.example.ecommerceapi.entity.Address;
import com.example.ecommerceapi.entity.Customer;
import com.example.ecommerceapi.entity.Order;
import com.example.ecommerceapi.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
