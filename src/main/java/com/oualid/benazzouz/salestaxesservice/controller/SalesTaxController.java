package com.oualid.benazzouz.salestaxesservice.controller;

import com.oualid.benazzouz.salestaxesservice.dto.InvoiceDTO;
import com.oualid.benazzouz.salestaxesservice.dto.ItemInDTO;
import com.oualid.benazzouz.salestaxesservice.service.SalesTaxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoice")
public class SalesTaxController {

    @Autowired
    private SalesTaxService salesTaxService;

    @PostMapping("/calculate")
    @Operation(summary = "Calculate total taxes and total price for a list of items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invoice calculated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = InvoiceDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected error",
                    content = @Content)
    })
    public ResponseEntity<InvoiceDTO> calculateInvoice(@RequestBody List<ItemInDTO> items) {
        InvoiceDTO invoice = salesTaxService.calculateInvoice(items);
        return ResponseEntity.ok(invoice);
    }
}
