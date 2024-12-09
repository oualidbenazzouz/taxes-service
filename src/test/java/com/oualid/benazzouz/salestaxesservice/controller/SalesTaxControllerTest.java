package com.oualid.benazzouz.salestaxesservice.controller;

import com.oualid.benazzouz.salestaxesservice.dto.InvoiceDTO;
import com.oualid.benazzouz.salestaxesservice.dto.ItemOutDTO;
import com.oualid.benazzouz.salestaxesservice.service.SalesTaxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SalesTaxController.class)
public class SalesTaxControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalesTaxService salesTaxService;

    @Test
    void calculateInvoiceThenOK() throws Exception {

        // Mock the behavior of the service
        when(salesTaxService.calculateInvoice(anyList())).thenReturn(
                new InvoiceDTO(List.of(new ItemOutDTO("Book", new BigDecimal("12.49"))),
                        new BigDecimal("1.5"),
                        new BigDecimal("29.83")));

        // Perform a POST request to the endpoint
        mockMvc.perform(post("/api/v1/invoice/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"name\":\"Book\",\"price\":12.49,\"isExempt\":true,\"isImported\":false}]"))
                .andExpect(status().isOk()) // Verify HTTP 200 response
                .andExpect(jsonPath("$.totalTaxes").value("1.5")) // Verify the total taxes
                .andExpect(jsonPath("$.totalPrice").value("29.83")); // Verify the total price

    }

    @Test
    void calculateInvoiceThenBadRequest() throws Exception {

        // Arrange: Mock the service to throw IllegalArgumentException
        doThrow(new IllegalArgumentException("The item list cannot be null or empty."))
                .when(salesTaxService).calculateInvoice(anyList());

        // Provide an empty list
        String emptyItemsJson = "[]";

        // Act & Assert
        mockMvc.perform(post("/api/v1/invoice/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(emptyItemsJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("The item list cannot be null or empty.")); // Ensure error message is exists

    }

    @Test
    void calculateInvoiceThenInternalServerError() throws Exception {
        // Arrange: Mock the service to throw a generic exception
        doThrow(new RuntimeException("Unexpected error"))
                .when(salesTaxService).calculateInvoice(anyList());

        // Provide an empty list
        String validItemsJson = "[]";

        // Act & Assert
        mockMvc.perform(post("/api/v1/invoice/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validItemsJson))
                .andExpect(status().isInternalServerError()) // Expect 500 Internal Server Error
                .andExpect(content().string("An unexpected error occurred.")); // Ensure generic error message
    }



}
