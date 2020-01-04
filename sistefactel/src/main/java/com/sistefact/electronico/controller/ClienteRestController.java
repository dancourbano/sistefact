package com.sistefact.electronico.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistefact.electronico.dto.ClienteDto;
import com.sistefact.electronico.models.AjaxResponseBody;
import com.sistefact.electronico.models.Cliente;
import com.sistefact.electronico.service.ClienteService;

@RestController
public class ClienteRestController {
	@Autowired
	ClienteService clienteService;
	@PostMapping(path= "/getByDNI",headers = "Accept=application/json")
	public Cliente save(@Valid @RequestBody Cliente Cliente, BindingResult result) {
		return clienteService.getClienteByDni(Cliente.getIdentificador());	
		
	}
}
