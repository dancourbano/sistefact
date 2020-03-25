package com.sistefact.electronico.controller;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sistefact.electronico.models.Cliente;
import com.sistefact.electronico.models.Invoice;
import com.sistefact.electronico.models.Sale;
import com.sistefact.electronico.service.InvoiceService;
import com.sistefact.electronico.service.SaleService;

/**
 * Created by veljko on 27.8.18.
 */
@Slf4j
@RestController
@RequestMapping(path = "rest/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    SaleService saleService;
   
    @PostMapping("/{id}/saveCustomer")
    public ResponseEntity<String> saveInvoiceCustomer(@PathVariable Long id, @RequestParam("customer") String customerId) {

        Cliente customer = new Cliente();
        customer.setIdCliente(Long.parseLong(customerId));
        Invoice invoice = invoiceService.findById(id);
        invoice.setCustomer(customer);

        invoiceService.saveInvoice(invoice);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/{id}/sale")
    public Sale addSale(@PathVariable Long id, @RequestBody Sale sale) {

        Invoice invoice = new Invoice();
        invoice.setId(id);
        sale.setInvoice(invoice);

        saleService.saveSale(sale);
        invoiceService.updateInvoiceTotal(id);

        return sale;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInvoice(@PathVariable Long id) {

        invoiceService.deleteById(id);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @DeleteMapping("/sale/{id}")
    public ResponseEntity<String> deleteSale(@PathVariable Long id) {

        saleService.deleteById(id);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
