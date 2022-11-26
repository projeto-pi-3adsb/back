package com.example.start.hemomanager.v2.repository;

import com.example.start.hemomanager.v2.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    @Query(value = "SELECT COUNT(*) FROM stock WHERE id > 0", nativeQuery = true)
    long countByBloodType();

    @Query(value = "SELECT COUNT(*) FROM stock WHERE blood_type = :blood_type", nativeQuery = true)
    long countByType(@Param("blood_type") String blood_type);
}
