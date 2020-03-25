package com.sistefact.electronico.controller;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sistefact.electronico.models.Cliente;
import com.sistefact.electronico.models.Invoice;
import com.sistefact.electronico.models.Producto;
import com.sistefact.electronico.models.UserCompany;
import com.sistefact.electronico.service.ClienteService;
import com.sistefact.electronico.service.InvoiceService;
import com.sistefact.electronico.service.ProductoService;
import com.sistefact.electronico.service.SaleService;
import com.sistefact.electronico.service.UserService;

import java.util.List;
import java.util.Set;

/**
 * Created by veljko on 29.7.18.
 */
@Slf4j
@Controller
public class IndexController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    ProductoService productoService;

    @Autowired
    SaleService saleService;
    
    @Autowired
    UserService  userService;
    
    @Autowired
    ClienteService  clienteService;
    

    @GetMapping("/products")
    public String getProductsPage(Model model) {
                 
        Set<Producto> productSet = productoService.getProductsByUser(1L);

        model.addAttribute("products", productSet);

        return "product/products";
    }

    @GetMapping("/invoice/add")
    public String addNewInvoice() {

       

        Invoice newInvoice = invoiceService.addNewInvoice(1L);

        return "redirect:show/" + newInvoice.getId();
    }

    @GetMapping("/invoice/show/{id}")
    public String showById(@PathVariable String id, Model model) {

        

        UserCompany userCompany = userService.getUserCompany(1L);
        Invoice invoice = invoiceService.findById(new Long(id));
        List<Cliente> customers = clienteService.getCustomersByUser(1L);

        model.addAttribute("invoice", invoice);
        model.addAttribute("userCompany", userCompany);
        model.addAttribute("customers", customers);
        model.addAttribute("sales", saleService.getSalesByInvoice(invoice));
        model.addAttribute("products", productoService.getProductsByUser(1L));

        return "invoice/show";
    }

}