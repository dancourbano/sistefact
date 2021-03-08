package com.sistefact.electronico.service;



import java.util.Set;

import com.sistefact.electronico.models.Invoice;

/**
 * Created by veljko on 5.8.18.
 */
public interface InvoiceService {

    Set<Invoice> getInvoices();

    Set<Invoice> getUserInvoices(Long userId);

    Invoice findById(Long id);

    Invoice saveInvoice(Invoice invoice);

    Invoice addNewInvoice(Long userId);

    void updateInvoiceTotal(Long invoiceId);

    void deleteById(Long id);
}
