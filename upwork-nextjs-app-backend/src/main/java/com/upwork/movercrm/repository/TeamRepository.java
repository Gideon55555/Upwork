package com.upwork.movercrm.repository;

import com.upwork.movercrm.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    
    Optional<Team> findByName(String name);
    
    List<Team> findByActiveTrue();
    
    List<Team> findByTeamLeaderId(Long teamLeaderId);
    
    @Query("SELECT t FROM Team t WHERE " +
           "LOWER(t.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(t.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Team> searchTeams(@Param("searchTerm") String searchTerm);
    
    Boolean existsByName(String name);
} 