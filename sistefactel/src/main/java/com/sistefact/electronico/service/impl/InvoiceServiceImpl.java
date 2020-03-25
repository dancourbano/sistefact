package com.sistefact.electronico.service.impl;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistefact.electronico.models.Invoice;
import com.sistefact.electronico.models.Sale;
import com.sistefact.electronico.models.User;
import com.sistefact.electronico.repository.InvoiceRepository;
import com.sistefact.electronico.repository.SaleRepository;
import com.sistefact.electronico.service.InvoiceService;

import javassist.NotFoundException;

import java.util.*;

/**
 * Created by veljko on 25.8.18.
 */
@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    SaleRepository saleRepository;

    @Override
    public Set<Invoice> getInvoices() {
        Set<Invoice> invoices = new HashSet<>();
        invoiceRepository.findAll().iterator().forEachRemaining(invoices::add);

        return invoices;
    }

    @Override
    public Set<Invoice> getUserInvoices(Long userId) {
        
        Set<Invoice> invoices = new HashSet<>();
        invoiceRepository.findByUser_Id(userId).iterator().forEachRemaining(invoices::add);

        return invoices;
    }

    @Override
    public Invoice findById(Long id) {

        Optional<Invoice> invoice = invoiceRepository.findById(id);

        if(!invoice.isPresent()) {
            try {
				throw new NotFoundException("Invoice Not Found. For ID value: " + id.toString() );
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return invoice.get();
    }

    @Override
    public Invoice saveInvoice(Invoice invoice) {

        Invoice saveInvoice = invoiceRepository.save(invoice);        
        return saveInvoice;
    }

    @Override
    public Invoice addNewInvoice(Long userId) {

        Invoice invoice = new Invoice();
        User user = new User();
        user.setId(userId);
        invoice.setUser(user);
        invoice.setTime(new Date());
        invoice.setInvoiceNumber(calculateInvoiceNumber(userId));

        Invoice saveInvoice = invoiceRepository.save(invoice);

        return saveInvoice;
    }

    @Override
    public void updateInvoiceTotal(Long invoiceId) {

        Invoice invoice = findById(invoiceId);
        List<Sale> sales = saleRepository.findSalesByInvoice(invoice);
        double invoiceTotal = 0;
        for (Sale s : sales) {
            invoiceTotal += s.getTotalAmount();
        }
        invoice.setTotal(invoiceTotal);

        invoiceRepository.save(invoice);
    }

    @Override
    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }

    private String calculateInvoiceNumber(Long userId) {

        String lastInvoiceNumber = invoiceRepository.findLastInvoiceNumber(userId);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int now = cal.get(Calendar.YEAR);

        if(lastInvoiceNumber != null) {
            String[] parts = lastInvoiceNumber.split("-");
            int year = Integer.parseInt(parts[0]);
            int number = Integer.parseInt(parts[1]);

            if (now != year) {
                return now + "-" + 1;
            } else {
                number++;
                return year + "-" + number;
            }
        } else {
            return now + "-" + 1;
        }
    }
}
