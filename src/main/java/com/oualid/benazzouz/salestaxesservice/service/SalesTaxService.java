package com.oualid.benazzouz.salestaxesservice.service;

import com.oualid.benazzouz.salestaxesservice.dto.InvoiceDTO;
import com.oualid.benazzouz.salestaxesservice.dto.ItemInDTO;

import java.util.List;

public interface SalesTaxService {

    /**
     * Calculates the invoice details for the given list of items.
     *
     * @param items the list of items purchased
     * @return the calculated invoice containing total taxes and total price
     */
    InvoiceDTO calculateInvoice(List<ItemInDTO> items);
}
