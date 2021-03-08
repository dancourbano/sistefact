package com.sistefact.electronico.repository;


import org.springframework.data.repository.CrudRepository;

import com.sistefact.electronico.models.Invoice;
import com.sistefact.electronico.models.Sale;

import java.util.List;

/**
 * Created by veljko on 4.8.18.
 */
public interface SaleRepository extends CrudRepository<Sale, Long> {

    List<Sale> findSalesByInvoice(Invoice invoice);
}
