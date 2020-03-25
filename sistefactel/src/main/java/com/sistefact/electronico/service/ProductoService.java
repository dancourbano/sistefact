package com.sistefact.electronico.service;

import java.util.Set;


import org.springframework.data.repository.query.Param;

import com.sistefact.electronico.models.Producto;
public interface ProductoService extends ICRUD<Producto>{
	
	Set<Producto> getProductsByUser(Long userId);
	
}