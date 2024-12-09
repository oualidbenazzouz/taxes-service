package com.oualid.benazzouz.salestaxesservice.dto;

import java.math.BigDecimal;
import java.util.List;

public class InvoiceDTO {

    private List<ItemOutDTO> purchasedItems;
    private BigDecimal totalTaxes;
    private BigDecimal totalPrice;

    public InvoiceDTO() {
    }

    public InvoiceDTO(List<ItemOutDTO> items, BigDecimal totalTaxes, BigDecimal totalPrice) {
        this.purchasedItems = items;
        this.totalTaxes = totalTaxes;
        this.totalPrice = totalPrice;
    }

    public List<ItemOutDTO> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(List<ItemOutDTO> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }

    public BigDecimal getTotalTaxes() {
        return totalTaxes;
    }

    public void setTotalTaxes(BigDecimal totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "InvoiceDTO{" +
                "items=" + purchasedItems +
                ", totalTaxes=" + totalTaxes +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
