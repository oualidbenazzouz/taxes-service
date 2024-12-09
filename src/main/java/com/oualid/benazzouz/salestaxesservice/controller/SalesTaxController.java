package com.oualid.benazzouz.salestaxesservice.controller;

import com.oualid.benazzouz.salestaxesservice.dto.InvoiceDTO;
import com.oualid.benazzouz.salestaxesservice.dto.ItemInDTO;
import com.oualid.benazzouz.salestaxesservice.service.SalesTaxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<InvoiceDTO> calculateInvoice(@RequestBody List<ItemInDTO> items) {
        InvoiceDTO invoice = salesTaxService.calculateInvoice(items);
        return ResponseEntity.ok(invoice);
    }
}
