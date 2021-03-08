package com.sistefact.electronico.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sistefact.electronico.AppProperties.SistefactelAPPUrl;
import com.sistefact.electronico.dto.ClienteDto;
import com.sistefact.electronico.models.AjaxResponseBody;
import com.sistefact.electronico.models.Cliente;
import com.sistefact.electronico.service.ClienteService;

@RestController
@RequestMapping("/clienteRest")
public class RestClienteController {
	@Autowired
	private ClienteService clienteService;
	@PostMapping(path= "/getByDNI",headers = "Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
	public Cliente reniecCliente(@RequestBody Cliente cliente) {
		Cliente clienteObtenido=clienteService.getClienteByDni(cliente.getIdentificador());
		return clienteObtenido;
	}
	 
}
