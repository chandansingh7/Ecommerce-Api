package com.example.ecommerceapi.service;

import com.example.ecommerceapi.dto.Purchase;
import com.example.ecommerceapi.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
