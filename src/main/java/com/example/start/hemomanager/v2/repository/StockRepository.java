package com.example.start.hemomanager.v2.repository;

import com.example.start.hemomanager.v2.domain.Stock;
import com.example.start.hemomanager.v2.response.StockFullResponse;
import com.example.start.hemomanager.v2.response.StockSimpleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("SELECT new com.example.start.hemomanager.v2.response.StockFullResponse(s.id, s.bloodType, s.collectionDate, s.insertDate) FROM stock s JOIN s.hemocenter h WHERE s.hemocenter.uuid =:uuid ORDER BY uuid asc")
    List<StockFullResponse> findByHemocenter(@Param("uuid") Integer uuid);

    @Modifying @Query("DELETE FROM stock WHERE id = ?1")
    void deleteReferenceById(Integer id);

    List<Stock> findAllByHemocenterUuid(int hemocenterId);
}
