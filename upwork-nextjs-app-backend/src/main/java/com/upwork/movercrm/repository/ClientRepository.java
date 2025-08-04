package com.upwork.movercrm.repository;

import com.upwork.movercrm.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    
    Optional<Client> findByPhone(String phone);
    
    Optional<Client> findByEmail(String email);
    
    List<Client> findByNameContainingIgnoreCase(String name);
    
    List<Client> findByPhoneContaining(String phone);
    
    List<Client> findByIsFavoriteTrue();
    
    @Query("SELECT c FROM Client c WHERE " +
           "LOWER(c.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "c.phone LIKE CONCAT('%', :searchTerm, '%') OR " +
           "LOWER(c.email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Client> searchClients(@Param("searchTerm") String searchTerm);
    
    Boolean existsByPhone(String phone);
    
    Boolean existsByEmail(String email);
} 