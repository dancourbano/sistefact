package com.sistefact.electronico.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistefact.electronico.models.Cliente;
import com.sistefact.electronico.models.Invoice;



/**
 * Created by veljko on 4.8.18.
 */
@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    Iterable<Invoice> findInvoicesByCustomer(Cliente customer);

    Iterable<Invoice> findByUser_Id(Long userId);

    @Query(value = "SELECT invoice_number FROM invoice WHERE user_id =:userId ORDER BY invoice_number DESC LIMIT 1", nativeQuery = true)
    String findLastInvoiceNumber(@Param("userId") Long userId);
}
