package com.example.start.hemomanager.v2.repository;

import com.example.start.hemomanager.v2.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    @Query(value = "SELECT COUNT(*) FROM stock WHERE id > 0", nativeQuery = true)
    long countByBloodType();

    @Query(value = "SELECT COUNT(*) FROM stock WHERE blood_type = 'APos'", nativeQuery = true)
    long countByTypeAPos();

    @Query(value = "SELECT COUNT(*) FROM stock WHERE blood_type = 'ANeg'", nativeQuery = true)
    long countByTypeANeg();

    @Query(value = "SELECT COUNT(*) FROM stock WHERE blood_type = 'ANeg'", nativeQuery = true)
    long countByTypeBPos();

    @Query(value = "SELECT COUNT(*) FROM stock WHERE blood_type = 'BNeg'", nativeQuery = true)
    long countByTypeBNeg();

    @Query(value = "SELECT COUNT(*) FROM stock WHERE blood_type = 'ANeg'", nativeQuery = true)
    long countByTypeABPos();

    @Query(value = "SELECT COUNT(*) FROM stock WHERE blood_type = 'ABNeg'", nativeQuery = true)
    long countByTypeABNeg();

    @Query(value = "SELECT COUNT(*) FROM stock WHERE blood_type = 'ANeg'", nativeQuery = true)
    long countByTypeOPos();

    @Query(value = "SELECT COUNT(*) FROM stock WHERE blood_type = 'ONeg'", nativeQuery = true)
    long countByTypeONeg();
}
