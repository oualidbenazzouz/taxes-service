package com.oualid.benazzouz.salestaxesservice.dto;

import java.math.BigDecimal;

public class ItemInDTO {

    private String name;
    private BigDecimal price;
    private boolean exempt;
    private boolean imported;

    public ItemInDTO() {
    }

    public ItemInDTO(String itemName, BigDecimal itemPrice, boolean isExempt, boolean isImported) {
        this.name = itemName;
        this.price = itemPrice;
        this.exempt = isExempt;
        this.imported = isImported;
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
        return exempt;
    }

    public void setExempt(boolean exempt) {
        this.exempt = exempt;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "itemName='" + name + '\'' +
                ", itemPrice=" + price +
                ", isExempt=" + exempt +
                ", isImported=" + imported +
                '}';
    }
}
