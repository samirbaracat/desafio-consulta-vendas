package com.devsuperior.dsmeta.dto;

public class SalesSummaryDTO {
    
    private String sellerName;
    private Double total;

    public SalesSummaryDTO() {
    }

    public SalesSummaryDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
    
}
