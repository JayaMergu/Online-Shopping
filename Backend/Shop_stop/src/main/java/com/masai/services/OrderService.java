package com.masai.services;

import java.util.List;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.CartException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.OrderException;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.UserException;
import com.masai.model.Order;
import com.masai.model.User;

public interface OrderService {

    public Order addOrder(String key)
            throws LoginException, CustomerException, OrderException, CartException, ProductException;

    public Order removeOrder(Integer orderId, String key, User user)
            throws OrderException, LoginException, CustomerException, UserException;

    public List<Order> viewOrder(String key) throws LoginException, CustomerException, OrderException;

    public List<Order> viewallOrdersByDate(String key, String stringdate)
            throws OrderException, CustomerException, LoginException;

    // Admin
    List<Order> viewAllOrdersByLocation(String userId, String userPassword, String key, String location)
            throws OrderException, UserException, AdminException;

    // Admin
    List<Order> viewAllOrdersbyUserId(String userId, String userPassword, String key, String customerUserId)
            throws OrderException, UserException, LoginException, CustomerException, AdminException;
    
}
