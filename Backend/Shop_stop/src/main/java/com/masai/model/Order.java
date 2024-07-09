package com.masai.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.masai.dto.ProductDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;

	@NotNull(message = "{Order.date.invalid}")
	private LocalDate orderDate;

	@NotBlank(message = "{Order.status.invalid}")
	@NotEmpty(message = "{Order.status.invalid}")
	@NotNull(message = "{Order.status.invalid}")
	private String orderStatus;

	@NotBlank(message = "{Order.location.invalid}")
	@NotEmpty(message = "{Order.location.invalid}")
	@NotNull(message = "{Order.location.invalid}")
	private String location;

	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
	


	    public List<ProductDTO> getProductDtoList() {
	        return productDtoList;
	    }

	@ElementCollection
    private List<ProductDTO> productDtoList = new ArrayList<>();

    // Setters and getters
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setProductDtoList(List<ProductDTO> productDtoList) {
        this.productDtoList = productDtoList;
    }
	@NotNull(message = "{Order.total.invalid}")
	private Double total;
	 // Setters and getters
    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotal() {
        return total;
    }

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

}