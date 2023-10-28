package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SalesSummaryDTO(obj.seller.name, SUM(obj.amount)) "
            + "FROM Sale obj "
            + "WHERE (CAST(:min AS date) IS NULL OR obj.date >= :min) "
            + "AND (CAST(:max AS date) IS NULL OR obj.date <= :max) "
            + "GROUP BY obj.seller "
            + "ORDER BY obj.seller.name")
    List<SalesSummaryDTO> salesSummary(LocalDate min, LocalDate max);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SalesReportDTO(obj) "
            +"FROM Sale obj "
            + "WHERE (CAST(:min AS date) IS NULL OR obj.date >= :min) "
            + "AND (CAST(:max AS date) IS NULL OR obj.date <= :max) "
            + "AND (:nameSeller IS NULL OR UPPER(obj.seller.name) LIKE UPPER(CONCAT('%',:nameSeller,'%'))) "
            + "ORDER BY obj.seller.name")
    List<SalesReportDTO> salesReport(LocalDate min, LocalDate max, String nameSeller);

}
