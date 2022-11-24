package com.example.start.hemomanager.v2.repository;

import com.example.start.hemomanager.v2.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    Long countAll();

    @Query(value = "SELECT COUNT(*) FROM stock WHERE blood_type = 'APos'", nativeQuery = true)
    Long countByTypeAPos();

    @Query(value = "SELECT COUNT(*) FROM stock WHERE blood_type = 'ANeg'", nativeQuery = true)
    Long countByTypeANeg();

    @Query(value = "SELECT COUNT(*) FROM stock WHERE blood_type = 'ANeg'", nativeQuery = true)
    Long countByTypeBPos();

    @Query(value = "SELECT COUNT(*) FROM stock WHERE blood_type = 'BNeg'", nativeQuery = true)
    Long countByTypeBNeg();

    @Query(value = "SELECT COUNT(*) FROM stock WHERE blood_type = 'ANeg'", nativeQuery = true)
    Long countByTypeABPos();

    @Query(value = "SELECT COUNT(*) FROM stock WHERE blood_type = 'ABNeg'", nativeQuery = true)
    Long countByTypeABNeg();

    @Query(value = "SELECT COUNT(*) FROM stock WHERE blood_type = 'ANeg'", nativeQuery = true)
    Long countByTypeOPos();

    @Query(value = "SELECT COUNT(*) FROM stock WHERE blood_type = 'ONeg'", nativeQuery = true)
    Long countByTypeONeg();
}
