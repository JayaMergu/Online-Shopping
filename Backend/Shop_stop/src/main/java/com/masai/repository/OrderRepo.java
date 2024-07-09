package com.masai.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

//	@Query("from Order o where o.OrderDate=:date")
	  public List<Order> findByOrderDate(LocalDate date);

//	@Query("from Order o where o.Location=:location")
	  public List<Order> findByLocation(String location);

}