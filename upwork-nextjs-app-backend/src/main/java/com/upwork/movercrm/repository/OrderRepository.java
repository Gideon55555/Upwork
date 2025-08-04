package com.upwork.movercrm.repository;

import com.upwork.movercrm.entity.Order;
import com.upwork.movercrm.entity.Order.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    Optional<Order> findByOrderNumber(String orderNumber);
    
    List<Order> findByClientId(Long clientId);
    
    List<Order> findByStatus(OrderStatus status);
    
    List<Order> findByDate(LocalDate date);
    
    List<Order> findByDateBetween(LocalDate startDate, LocalDate endDate);
    
    List<Order> findByTeamId(Long teamId);
    
    List<Order> findByTransportId(Long transportId);
    
    @Query("SELECT o FROM Order o WHERE " +
           "o.orderNumber LIKE CONCAT('%', :searchTerm, '%') OR " +
           "LOWER(o.service) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(o.client.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Order> searchOrders(@Param("searchTerm") String searchTerm, Pageable pageable);
    
    @Query("SELECT o FROM Order o WHERE o.date = :date")
    List<Order> findOrdersByDate(@Param("date") LocalDate date);
    
    @Query("SELECT o FROM Order o WHERE o.date BETWEEN :startDate AND :endDate")
    List<Order> findOrdersByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    Boolean existsByOrderNumber(String orderNumber);
} 