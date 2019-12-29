package com.sistefact.electronico.controller;





import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistefact.electronico.AppProperties.SistefactelAPPView;
import com.sistefact.electronico.exception.ModeloNotFoundException;
import com.sistefact.electronico.models.Venta;
import com.sistefact.electronico.service.ProductoService;
import com.sistefact.electronico.service.VentaService;




@Controller
@RequestMapping("/ventas")
public class VentaController {

	@Autowired
	private VentaService ventaService;
	@Autowired
	private ProductoService productoService;
	@RequestMapping(method = RequestMethod.GET)
    public String initForm() {
        return SistefactelAPPView.INVOICE;
    }
	@GetMapping( "/invoice2" )	 
    public String form(Model model) {
		model.addAttribute("productos", productoService.getAll());
        return SistefactelAPPView.INVOICE_FORM;
    }
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Venta>> getAll(){
		List<Venta> listVenta = new ArrayList<>();
		listVenta = ventaService.getAll();
		return new ResponseEntity<List<Venta>>(listVenta, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public Venta getById(@PathVariable("id") Long id) {
		Venta venta = ventaService.getById(id);		
		return venta;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> save(@Valid @RequestBody Venta venta){
		Venta cons = new Venta();
		cons = ventaService.save(venta);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cons.getIdVenta()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@Valid @RequestBody Venta Venta) {		
		ventaService.update(Venta);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Long id) {
		Venta cons = ventaService.getById(id);
		if (cons == null) {
			throw new ModeloNotFoundException("ID: " + id);
		} else {
			ventaService.delete(id);
		}
	}

}
