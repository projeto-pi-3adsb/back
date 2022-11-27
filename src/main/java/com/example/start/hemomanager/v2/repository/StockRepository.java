package com.example.start.hemomanager.v2.repository;

import com.example.start.hemomanager.v2.domain.Stock;
import com.example.start.hemomanager.v2.response.StockSimpleResponse;
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

    @Query("SELECT new com.example.start.hemomanager.v2.response.StockSimpleResponse(COUNT(*), s.bloodType) FROM stock s JOIN s.hemocenter WHERE s.hemocenter.uuid =:uuid GROUP BY s.bloodType")
    List<StockSimpleResponse> groupByBloodType(@Param("uuid") Integer uuid);

    @Query("SELECT * FROM stock s JOIN s.hemocenter WHERE s.hemocenter.uuid =:uuid")
    List<Stock> getFromStockHemocenter(@Param("uuid") Integer uuid);
}
