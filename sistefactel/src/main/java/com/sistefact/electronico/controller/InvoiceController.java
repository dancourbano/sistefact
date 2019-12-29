package com.sistefact.electronico.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistefact.electronico.AppProperties.SistefactelAPPUrl;
import com.sistefact.electronico.AppProperties.SistefactelAPPView;
import com.sistefact.electronico.exception.ModeloNotFoundException;
import com.sistefact.electronico.models.AjaxResponseBody;
import com.sistefact.electronico.models.Cliente;
import com.sistefact.electronico.service.ClienteService;



@Controller
@RequestMapping(SistefactelAPPUrl.INVOICE_ROOT)
public class InvoiceController {
	@Autowired
	private ClienteService clienteService;
	@RequestMapping(method = RequestMethod.GET)
    public String initForm() {
        return SistefactelAPPView.INVOICE;
    }
	
	@GetMapping(value = "/listAll",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> getAll() {
		List<Cliente> cliente = new ArrayList<>();
		cliente = clienteService.getAll();
		
		return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);
	}
	
	@RequestMapping(value = SistefactelAPPUrl.GET_CLIENTE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Cliente> getById(@PathVariable Long id) throws Exception {
        System.out.println("Long.parseLong(String.valueOf(idCliente)) "+Long.parseLong(String.valueOf(id)));
        Cliente cliente=clienteService.getById(id);
        if(cliente==null) return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
    }
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AjaxResponseBody> save(@Valid @RequestBody Cliente Cliente, BindingResult result) {
		Cliente clienteSave = new Cliente();
		clienteSave = clienteService.save(Cliente);
		AjaxResponseBody resultAjax = new AjaxResponseBody();
		try {

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCliente}").buildAndExpand(clienteSave.getIdCliente()).toUri();
		 
        resultAjax.setMessage("Operacion Exitosa");        
        return new ResponseEntity<AjaxResponseBody>(resultAjax, HttpStatus.OK);
		}catch(Exception e){
            resultAjax.setErrorMessage(e.getMessage());         
            
            return new ResponseEntity<AjaxResponseBody>(resultAjax, HttpStatus.OK);
        }
		

	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AjaxResponseBody> update(@Valid @RequestBody Cliente Cliente) {
		clienteService.update(Cliente);
		AjaxResponseBody resultAjax = new AjaxResponseBody();
		resultAjax.setMessage("Operacion Exitosa");  
		return new ResponseEntity<AjaxResponseBody>(resultAjax, HttpStatus.OK);
	}
	
	@RequestMapping(value = SistefactelAPPUrl.DELETE_CLIENTE, method = RequestMethod.GET)
	public ResponseEntity<AjaxResponseBody> delete(@PathVariable Long idCliente) {
		Cliente Cliente = clienteService.getById(idCliente);
		AjaxResponseBody resultAjax = new AjaxResponseBody();
		if (Cliente == null) {
			throw new ModeloNotFoundException("ID: " + idCliente);
		} else {
			clienteService.delete(idCliente);

		}
		resultAjax.setMessage("Se Eliminó con éxito");
        return ResponseEntity.status(HttpStatus.OK).body(resultAjax);
	}
}
