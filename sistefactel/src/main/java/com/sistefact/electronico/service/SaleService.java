package com.sistefact.electronico.service;



import java.util.List;
import java.util.Set;

import com.sistefact.electronico.models.Invoice;
import com.sistefact.electronico.models.Sale;

/**
 * Created by veljko on 5.8.18.
 */
public interface SaleService {

    Set<Sale> getSales();

    List<Sale> getSalesByInvoice(Invoice invoice);

    Sale findById(Long id);

    Sale saveSale(Sale sale);

    void deleteById(Long id);
}
