package com.oualid.benazzouz.salestaxesservice.service.impl;

import com.oualid.benazzouz.salestaxesservice.dto.InvoiceDTO;
import com.oualid.benazzouz.salestaxesservice.dto.ItemInDTO;
import com.oualid.benazzouz.salestaxesservice.service.SalesTaxService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class SalesTaxServiceImplTest {

    private SalesTaxService salesTaxService;

    @BeforeEach
    void setUp() {
        salesTaxService = new SalesTaxServiceImpl();
    }

    @Test
    void testCalculateInvoice_EmptyItemList() {
        List<ItemInDTO> items = List.of(); // Invalid: Empty list

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> salesTaxService.calculateInvoice(items),
                "Expected IllegalArgumentException when item list is empty"
        );

        Assertions.assertEquals("The item list cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testCalculateInvoice_NullItemName() {
        List<ItemInDTO> items = List.of(
                new ItemInDTO(null, new BigDecimal("10.00"), true, false) // Invalid: null name
        );

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> salesTaxService.calculateInvoice(items),
                "Expected IllegalArgumentException when item name is null"
        );

        Assertions.assertEquals("Item name cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testCalculateInvoice_EmptyItemName() {
        List<ItemInDTO> items = List.of(
                new ItemInDTO("", new BigDecimal("10.00"), true, false) // Invalid: empty name
        );

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> salesTaxService.calculateInvoice(items),
                "Expected IllegalArgumentException when item name is empty"
        );

        Assertions.assertEquals("Item name cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testCalculateInvoice_NullItemPrice() {
        List<ItemInDTO> items = List.of(
                new ItemInDTO("Item with null price", null, true, false) // Invalid: null price
        );

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> salesTaxService.calculateInvoice(items),
                "Expected IllegalArgumentException when item price is null"
        );

        Assertions.assertEquals("Item price must be a non-negative value.", exception.getMessage());
    }

    @Test
    void testCalculateInvoice_NegativeItemPrice() {
        List<ItemInDTO> items = List.of(
                new ItemInDTO("Item with negative price", new BigDecimal("-10.00"), true, false) // Invalid: negative price
        );

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> salesTaxService.calculateInvoice(items),
                "Expected IllegalArgumentException when item price is negative"
        );

        Assertions.assertEquals("Item price must be a non-negative value.", exception.getMessage());
    }

    @Test
    void testCalculateInvoice_Input1() {
        List<ItemInDTO> items = List.of(
                new ItemInDTO("Book", new BigDecimal("12.49"), true, false), // Exempt + Not Imported
                new ItemInDTO("Music CD", new BigDecimal("14.99"), false, false), // Non-exempt + Not Imported
                new ItemInDTO("Chocolate bar", new BigDecimal("0.85"), true, false) // Exempt + Not Imported
        );
        // Act
        InvoiceDTO invoice = salesTaxService.calculateInvoice(items);

        // Verify each item's price now includes the tax
        Assertions.assertEquals(new BigDecimal("12.49"), invoice.getPurchasedItems().get(0).getPrice(), "Book price should be 12.49");
        Assertions.assertEquals(new BigDecimal("16.49"), invoice.getPurchasedItems().get(1).getPrice(), "Music CD price should be 16.49");
        Assertions.assertEquals(new BigDecimal("0.85"), invoice.getPurchasedItems().get(2).getPrice(), "Chocolate bar price should be 0.85");

        // Verify total taxes and total price
        Assertions.assertNotNull(invoice, "Invoice should not be null");
        Assertions.assertEquals(new BigDecimal("1.50"), invoice.getTotalTaxes(), "Total taxes should be 1.50");
        Assertions.assertEquals(new BigDecimal("29.83"), invoice.getTotalPrice(), "Total price should be 29.83");
    }

    @Test
    void testCalculateInvoice_Input2() {
        List<ItemInDTO> items = List.of(
                new ItemInDTO("imported box of chocolates", new BigDecimal("10.00"), true, true), // Exempt + Imported
                new ItemInDTO("imported bottle of perfume", new BigDecimal("47.50"), false, true) // Non-exempt + Imported
        );
        // Act
        InvoiceDTO invoice = salesTaxService.calculateInvoice(items);

        // Verify each item's price now includes the tax
        Assertions.assertEquals(new BigDecimal("10.50"), invoice.getPurchasedItems().get(0).getPrice(), "imported box of chocolates price should be 10.50");
        Assertions.assertEquals(new BigDecimal("54.65"), invoice.getPurchasedItems().get(1).getPrice(), "imported bottle of perfume price should be 54.65");

        // Verify total taxes and total price
        Assertions.assertNotNull(invoice, "Invoice should not be null");
        Assertions.assertEquals(new BigDecimal("7.65"), invoice.getTotalTaxes(), "Total taxes should be 7.65");
        Assertions.assertEquals(new BigDecimal("65.15"), invoice.getTotalPrice(), "Total price should be 65.15");
    }

    @Test
    void testCalculateInvoice_Input3() {
        List<ItemInDTO> items = List.of(
                new ItemInDTO("imported bottle of perfume", new BigDecimal("27.99"), false, true), // Non-exempt + Imported
                new ItemInDTO("bottle of perfume", new BigDecimal("18.99"), false, false), // Non-exempt + Not Imported
                new ItemInDTO("packet of headache pills", new BigDecimal("9.75"), true, false), // Exempt + Not Imported
                new ItemInDTO("box of imported chocolates", new BigDecimal("11.25"), true, true) // Exempt + Imported
        );
        // Act
        InvoiceDTO invoice = salesTaxService.calculateInvoice(items);

        // Verify each item's price now includes the tax
        Assertions.assertEquals(new BigDecimal("32.19"), invoice.getPurchasedItems().get(0).getPrice(), "imported bottle of perfume price should be 32.19");
        Assertions.assertEquals(new BigDecimal("20.89"), invoice.getPurchasedItems().get(1).getPrice(), "bottle of perfume price should be 20.89");
        Assertions.assertEquals(new BigDecimal("9.75"), invoice.getPurchasedItems().get(2).getPrice(), "packet of headache pills price should be 9.75");
        Assertions.assertEquals(new BigDecimal("11.85"), invoice.getPurchasedItems().get(3).getPrice(), "box of imported chocolates price should be 11.85");

        // Verify total taxes and total price
        Assertions.assertNotNull(invoice, "Invoice should not be null");
        Assertions.assertEquals(new BigDecimal("6.70"), invoice.getTotalTaxes(), "Total taxes should be 6.70");
        Assertions.assertEquals(new BigDecimal("74.68"), invoice.getTotalPrice(), "Total price should be 74.68");
    }

}
