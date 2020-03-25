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

import com.sistefact.electronico.AppProperties.SistefactelAPPUrl;
import com.sistefact.electronico.AppProperties.SistefactelAPPView;
import com.sistefact.electronico.exception.ModeloNotFoundException;
import com.sistefact.electronico.models.AjaxResponseBody;
import com.sistefact.electronico.models.Producto;
import com.sistefact.electronico.service.ProductoService;


@Controller
@RequestMapping("producto")
public class ProductoController {
	@Autowired
	private ProductoService productoService;
	@RequestMapping(method = RequestMethod.GET)
    public String initForm() {
        return SistefactelAPPView.PRODUCTO;
    }
	@GetMapping(value = "/listAll",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> getAll() {
		List<Producto> Producto = new ArrayList<>();
		Producto = productoService.getAll();
		
		return new ResponseEntity<List<Producto>>(Producto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<Producto> getById(@PathVariable("id") Long id) {
		Producto producto = productoService.getById(id);
		
		if (producto == null) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		
		return new ResponseEntity<Producto>(producto,HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AjaxResponseBody> save(@Valid @RequestBody Producto producto, BindingResult result) {
		Producto ProductoSave = new Producto();
		 
		ProductoSave = productoService.save(producto);
		AjaxResponseBody resultAjax = new AjaxResponseBody();
		try {
			
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idProducto}").buildAndExpand(ProductoSave.getIdProducto()).toUri();
		 
        resultAjax.setMessage("Operacion Exitosa");        
        return new ResponseEntity<AjaxResponseBody>(resultAjax, HttpStatus.OK);
		}catch(Exception e){
            resultAjax.setErrorMessage(e.getMessage());         
            
            return new ResponseEntity<AjaxResponseBody>(resultAjax, HttpStatus.OK);
        }
		

	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AjaxResponseBody> update(@Valid @RequestBody Producto Producto) {
		productoService.update(Producto);
		AjaxResponseBody resultAjax = new AjaxResponseBody();
		resultAjax.setMessage("Operacion Exitosa");  
		return new ResponseEntity<AjaxResponseBody>(resultAjax,HttpStatus.OK);
	}
	
	@RequestMapping(value = SistefactelAPPUrl.DELETE_PRODUCTO, method = RequestMethod.GET)
	public ResponseEntity<AjaxResponseBody> delete(@PathVariable Long idProducto) {
		Producto Producto = productoService.getById(idProducto);
		AjaxResponseBody resultAjax = new AjaxResponseBody();
		if (Producto == null) {
			throw new ModeloNotFoundException("ID: " + idProducto);
		} else {
			productoService.delete(idProducto);

		}
		resultAjax.setMessage("Se Eliminó con éxito");
        return ResponseEntity.status(HttpStatus.OK).body(resultAjax);
	}
	
}
