package com.portfolio.backend.repository;

import com.portfolio.backend.model.About;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutRepository extends JpaRepository<About, Long> {
    
    // Since we'll only have one About record, we can add custom methods if needed
    About findFirstByOrderByIdAsc();
}
