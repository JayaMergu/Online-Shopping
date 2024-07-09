package com.masai.model;

import java.util.List; // Import List from java.util package

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;

    @NotNull(message = "{Customer.name.invalid}")
    @NotBlank(message = "{Customer.name.invalid}")
    @NotEmpty(message = "{Customer.name.invalid}")
    private String firstName;

    @NotNull(message = "{Customer.name.invalid}")
    @NotBlank(message = "{Customer.name.invalid}")
    @NotEmpty(message = "{Customer.name.invalid}")
    private String lastName;

    @NotNull(message = "{Customer.contact.invalid}")
    @NotBlank(message = "{Customer.contact.invalid}")
    @NotEmpty(message = "{Customer.contact.invalid}")
    private String mobileNumber;

    @NotNull(message = "{Customer.password.invalid}")
    @NotBlank(message = "{Customer.password.invalid}")
    @NotEmpty(message = "{Customer.password.invalid}")
    private String password;

    @NotNull(message = "{Customer.email.invalid}")
    @NotBlank(message = "{Customer.email.invalid}")
    @NotEmpty(message = "{Customer.email.invalid}")
    private String email;
    
    private Address address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> listOfOrders; // Define List of Orders here

    // Add getters and setters

    // Getter for customerId
    public Integer getCustomerId() {
        return customerId;
    }

    // Getter for mobileNumber
    public String getMobileNumber() {
        return mobileNumber;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for address
    public void setAddress(Address address) {
        this.address = address;
    }

    // Getter for address
    public Address getAddress() {
        return address;
    }

    // Setter for listOfOrders
    public void setListOfOrders(List<Order> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }
    public List<Order> getListOfOrders() {
        return listOfOrders;
    }
}
