package com.oualid.benazzouz.salestaxesservice.service.impl;

import com.oualid.benazzouz.salestaxesservice.dto.InvoiceDTO;
import com.oualid.benazzouz.salestaxesservice.dto.ItemDTO;
import com.oualid.benazzouz.salestaxesservice.service.SalesTaxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Implementation of the SalesTaxService interface.
 * This class calculates the total taxes and prices for a list of purchased items.
 */
public class SalesTaxServiceImpl implements SalesTaxService {

    private static final Logger logger = LoggerFactory.getLogger(SalesTaxServiceImpl.class);

    // Tax rates: 10% basic sales tax and 5% import duty tax
    private static final BigDecimal BASIC_SALES_TAX = new BigDecimal("0.10");
    private static final BigDecimal IMPORT_DUTY_TAX = new BigDecimal("0.05");


    /**
     * Calculates the total taxes and total price for a list of purchased items and generates an invoice.
     *
     * @param items A list of ItemDTO representing the items purchased.
     * @return An InvoiceDTO containing the list of items, total taxes, and total price.
     * @throws IllegalArgumentException if the item list is null, empty, or contains invalid attributes.
     */
    @Override
    public InvoiceDTO calculateInvoice(List<ItemDTO> items) {

        BigDecimal totalTaxes = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("The item list cannot be null or empty.");
        }

        for (ItemDTO item : items) {

            if (item.getName() == null || item.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Item name cannot be null or empty.");
            }
            if (item.getPrice() == null || item.getPrice().compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Item price must be a non-negative value.");
            }

            BigDecimal itemTax = calculateItemTax(item);
            BigDecimal itemTotal = item.getPrice().add(itemTax);

            totalTaxes = totalTaxes.add(itemTax);
            totalPrice = totalPrice.add(itemTotal);
        }

        return new InvoiceDTO(items, totalTaxes, totalPrice);
    }

    /**
     * Calculates the total tax for a given item based on its price, exemption status,
     * and whether it is imported. Applies basic sales tax and import duty where applicable
     * and rounds the tax to the nearest 0.05.
     *
     * @param item The ItemDTO for which tax is being calculated.
     * @return The total rounded tax for the item.
     */
    private BigDecimal calculateItemTax(ItemDTO item) {
        BigDecimal tax = BigDecimal.ZERO;

        if (!item.isExempt()) {
            tax = tax.add(item.getPrice().multiply(BASIC_SALES_TAX));
        }
        if (item.isImported()) {
            tax = tax.add(item.getPrice().multiply(IMPORT_DUTY_TAX));
        }

        return roundTax(tax);
    }

    /**
     * Rounds the given tax up to the nearest multiple of 0.05.
     *
     * @param tax The calculated tax as a BigDecimal.
     * @return The tax rounded up to the nearest 0.05.
     */
    private BigDecimal roundTax(BigDecimal tax) {
        BigDecimal increment = new BigDecimal("0.05"); // Round up to the nearest 0.05
        return tax.divide(increment, 0, RoundingMode.UP).multiply(increment);
    }
}