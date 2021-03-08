package com.sistefact.electronico.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistefact.electronico.models.Producto;
import com.sistefact.electronico.repository.ProductoRepository;
import com.sistefact.electronico.service.ProductoService;

;
@Service
public class ProductoServiceImpl implements ProductoService{
	 @Autowired
	    private ProductoRepository productoRepo;

	    @Override
	    public Producto getById(Long id) {
	        return productoRepo.getOne(id);
	    }

	     

	    @Override
	    public Producto save(Producto Producto) {
	        return productoRepo.save(Producto);
	    }

	    @Override
	    public Producto update(Producto Producto) {
	         
	        return productoRepo.save(Producto);
	    }

	    @Override
	    public void delete(Long id) {
	    	productoRepo.deleteById(id);
	    }

	    @Override
	    public List<Producto> getAll() {
	        return (List<Producto>) productoRepo.findAll();
	    }
	    
	    @Override
	    public Set<Producto> getProductsByUser(Long userId) {

	        Set<Producto> productSet = new HashSet<>();
	        productoRepo.findByUser_Id(userId).iterator().forEachRemaining(productSet::add);

	        return productSet;
	    }
}
