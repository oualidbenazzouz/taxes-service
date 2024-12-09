package com.oualid.benazzouz.salestaxesservice.dto;

import java.math.BigDecimal;

public class ItemInDTO {

    private String name;
    private BigDecimal price;
    private boolean isExempt;
    private boolean isImported;

    public ItemInDTO() {
    }

    public ItemInDTO(String itemName, BigDecimal itemPrice, boolean isExempt, boolean isImported) {
        this.name = itemName;
        this.price = itemPrice;
        this.isExempt = isExempt;
        this.isImported = isImported;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isExempt() {
        return isExempt;
    }

    public void setExempt(boolean exempt) {
        isExempt = exempt;
    }

    public boolean isImported() {
        return isImported;
    }

    public void setImported(boolean imported) {
        isImported = imported;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "itemName='" + name + '\'' +
                ", itemPrice=" + price +
                ", isExempt=" + isExempt +
                ", isImported=" + isImported +
                '}';
    }
}
