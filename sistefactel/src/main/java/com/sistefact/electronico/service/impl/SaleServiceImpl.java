package com.sistefact.electronico.service.impl;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistefact.electronico.models.Invoice;
import com.sistefact.electronico.models.Sale;
import com.sistefact.electronico.repository.SaleRepository;
import com.sistefact.electronico.service.SaleService;

import javassist.NotFoundException;

import java.util.*;

/**
 * Created by veljko on 23.8.18.
 */
@Service
@Slf4j
public class SaleServiceImpl implements SaleService {

    @Autowired
    SaleRepository saleRepository;

    @Override
    public Set<Sale> getSales() {
        

        Set<Sale> saleSet = new HashSet<>();
        saleRepository.findAll().iterator().forEachRemaining(saleSet::add);
        return saleSet;

    }

    @Override
    public List<Sale> getSalesByInvoice(Invoice invoice) {

        List<Sale> saleSet = new ArrayList<>();
        saleRepository.findSalesByInvoice(invoice).iterator().forEachRemaining(saleSet::add);

        return saleSet;
    }

    @Override
    public Sale findById(Long id) {

        Optional<Sale> sale = saleRepository.findById(id);
        if(!sale.isPresent()) {
            try {
				throw new NotFoundException("Sale Not Found. For ID value: " + id.toString() );
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return sale.get();
    }

    @Override
    public Sale saveSale(Sale sale) {

        Sale saveSale = saleRepository.save(sale);        
        return saveSale;
    }

    @Override
    public void deleteById(Long id) {
        saleRepository.deleteById(id);
    }
}
