package com.sistefact.electronico.service;




import com.sistefact.electronico.models.Cliente;

public interface ClienteService extends ICRUD<Cliente>{
	public Cliente findByNombreAndApellido(String nombre,String apellido);
}
