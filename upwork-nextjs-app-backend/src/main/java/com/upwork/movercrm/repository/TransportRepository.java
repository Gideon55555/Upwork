package com.upwork.movercrm.repository;

import com.upwork.movercrm.entity.Transport;
import com.upwork.movercrm.entity.Transport.TransportStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {
    
    Optional<Transport> findByVehicleNumber(String vehicleNumber);
    
    List<Transport> findByStatus(TransportStatus status);
    
    List<Transport> findByActiveTrue();
    
    List<Transport> findByDriverId(Long driverId);
    
    @Query("SELECT t FROM Transport t WHERE " +
           "t.vehicleNumber LIKE CONCAT('%', :searchTerm, '%') OR " +
           "LOWER(t.vehicleType) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(t.model) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Transport> searchTransport(@Param("searchTerm") String searchTerm);
    
    List<Transport> findByStatusAndActiveTrue(TransportStatus status);
    
    Boolean existsByVehicleNumber(String vehicleNumber);
} 