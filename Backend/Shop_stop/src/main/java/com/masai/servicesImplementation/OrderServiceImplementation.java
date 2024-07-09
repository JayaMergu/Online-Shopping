package com.masai.servicesImplementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dto.ProductDTO;
import com.masai.exceptions.AdminException;
import com.masai.exceptions.CartException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.OrderException;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.UserException;
import com.masai.model.Admin;
import com.masai.model.Cart;
import com.masai.model.Customer;
import com.masai.model.Order;
import com.masai.model.Product;
import com.masai.model.User;
import com.masai.repository.CartRepo;
import com.masai.repository.CustomerRepo;
import com.masai.repository.OrderRepo;
import com.masai.repository.ProductRepo;
import com.masai.services.LoginLogoutAdminService;
import com.masai.services.LoginLogoutCustomerService;
import com.masai.services.OrderService;

import net.bytebuddy.utility.RandomString;

@Service
public class OrderServiceImplementation implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private LoginLogoutCustomerService loginLogoutCustomerService;

    @Autowired
    private LoginLogoutAdminService loginLogoutAdminService;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Order> viewAllOrdersbyUserId(String userId, String userPassword, String key, String customerUserId)
            throws OrderException, UserException, LoginException, CustomerException, AdminException {

        // Authenticate admin using the provided key and user credentials
        User adminUser = new User(userId, userPassword);
        Admin admin = loginLogoutAdminService.authenticateAdmin(adminUser, key);

        if (admin != null) {
            // Admin authenticated successfully, proceed with fetching orders
            Optional<Customer> optional_customer = customerRepo.findByMobileNumber(customerUserId);

            if (optional_customer.isPresent()) {
                Customer customer = optional_customer.get();
                List<Order> listOfOrders = customer.getListOfOrders();

                if (!listOfOrders.isEmpty()) {
                    return listOfOrders;
                } else {
                    throw new OrderException("No Orders Found For Customer with ID: " + customerUserId);
                }
            } else {
                throw new CustomerException("No Customer Found with ID: " + customerUserId);
            }
        } else {
            throw new UserException("Admin Authentication Failed. Please log in.");
        }
    }

    @Override
    public Order removeOrder(Integer orderId, String key, User user)
            throws OrderException, LoginException, CustomerException, UserException {

        User validatedUser = loginLogoutCustomerService.authenticateCustomer(user, key);

        if (validatedUser != null) {
            Optional<Customer> optionalCustomer = customerRepo.findByMobileNumber(user.getId());

            if (optionalCustomer.isPresent()) {
                Optional<Order> optionalOrder = orderRepo.findById(orderId);

                if (optionalOrder.isPresent()) {
                    Order order = optionalOrder.get();
                    order.setOrderStatus("Cancelled");

                    // Additional logic for handling product quantities, etc.

                    return orderRepo.save(order);
                } else {
                    throw new OrderException("No Order Found with ID: " + orderId);
                }
            } else {
                throw new CustomerException("No Customer Found with ID: " + user.getId());
            }
        } else {
            throw new LoginException("Authentication Failed. Please log in.");
        }
    }

    @Override
    public List<Order> viewallOrdersByDate(String key, String stringdate)
            throws OrderException, CustomerException, LoginException {

        try {
            LocalDate date = LocalDate.parse(stringdate);
            Customer customer = loginLogoutCustomerService.validateCustomer(key);

            if (customer != null) {
                List<Order> listOfOrdersByLocalDate = orderRepo.findByOrderDate(date);

                if (!listOfOrdersByLocalDate.isEmpty()) {
                    return listOfOrdersByLocalDate;
                } else {
                    throw new OrderException("No Orders Found For Date: " + date);
                }
            } else {
                throw new CustomerException("No Customer Found. Please log in.");
            }
        } catch (LoginException e) {
            throw e; // Re-throwing the LoginException to propagate it
        } catch (Exception e) {
            throw new OrderException("Error fetching orders by date", e);
        }
    }

    @Override
    public List<Order> viewAllOrdersByLocation(String userId, String userPassword, String key, String location)
            throws OrderException, UserException, AdminException {
        try {
            Admin admin = loginLogoutAdminService.authenticateAdmin(new User(userId, userPassword), key);

            if (admin != null) {
                List<Order> listOfOrdersByLocation = orderRepo.findByLocation(location);

                if (!listOfOrdersByLocation.isEmpty()) {
                    return listOfOrdersByLocation;
                } else {
                    throw new OrderException("No Orders Found For Location: " + location);
                }
            } else {
                throw new AdminException("Admin Authentication Failed. Please log in.");
            }
        } catch (LoginException e) {
            // Handle LoginException here, either log it or rethrow as a more specific exception
            throw new AdminException("Failed to authenticate admin: " + e.getMessage(), e);
        } catch (Exception e) {
            // Handle other exceptions and rethrow as appropriate
            throw new OrderException("Error fetching orders by location", e);
        }
    }



    @Override
    public Order addOrder(String key)
            throws LoginException, CustomerException, OrderException, CartException, ProductException {

        Customer customer = loginLogoutCustomerService.validateCustomer(key);

        if (customer != null) {
            Optional<Cart> optionalCart = cartRepo.findByCustomer(customer);

            if (optionalCart.isPresent()) {
                Cart cart = optionalCart.get();
                Order order = new Order();
                order.setCustomer(customer);
                order.setAddress(customer.getAddress());
                order.setLocation(customer.getAddress().getCity());
                order.setOrderDate(LocalDate.now());
                order.setOrderStatus("Order Confirmed");

                List<ProductDTO> listOfCartProducts = cart.getProducts();

                if (!listOfCartProducts.isEmpty()) {
                    Double totalPrice = 0.0;
                    List<ProductDTO> listOfOrderedProducts = new ArrayList<>();

                    for (ProductDTO cartProduct : listOfCartProducts) {
                        Optional<Product> optionalProduct = productRepo.findById(cartProduct.getProductId());

                        if (optionalProduct.isPresent()) {
                            Product product = optionalProduct.get();
                            Integer availableQuantity = product.getQuantity();

                            if (availableQuantity >= cartProduct.getQuantity()) {
                                Double price = cartProduct.getPrice() * cartProduct.getQuantity();
                                totalPrice += price;
                                product.setQuantity(availableQuantity - cartProduct.getQuantity());
                                productRepo.save(product);
                                listOfOrderedProducts.add(cartProduct);
                            } else {
                                throw new ProductException("Available Quantity of Product " + product.getProductName() + " is insufficient: " + availableQuantity);
                            }
                        } else {
                            throw new ProductException("No Product Found With ID: " + cartProduct.getProductId());
                        }
                    }

                    order.setTotal(totalPrice);
                    order.setProductDtoList(listOfOrderedProducts);
                    customer.getListOfOrders().add(order);
                    cart.setProducts(new ArrayList<>());
                    cartRepo.save(cart);

                    return orderRepo.save(order);
                } else {
                    throw new OrderException("Cart is Empty. Please Add Products To Place an Order.");
                }
            } else {
                throw new CartException("No Cart Found For Customer ID: " + customer.getCustomerId());
            }
        } else {
            throw new CustomerException("No Customer Found. Please log in.");
        }
    }

    @Override
    public List<Order> viewOrder(String key) throws LoginException, CustomerException, OrderException {

        Customer customer = loginLogoutCustomerService.validateCustomer(key);

        if (customer != null) {
            List<Order> listOfOrders = customer.getListOfOrders();

            if (!listOfOrders.isEmpty()) {
                return listOfOrders;
            } else {
                throw new OrderException("No Orders Found For Customer.");
            }
        } else {
            throw new CustomerException("No Customer Found. Please log in.");
        }
    }
}
