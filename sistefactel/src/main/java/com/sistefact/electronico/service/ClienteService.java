package com.sistefact.electronico.service;




import java.util.List;

import com.sistefact.electronico.dto.ClienteDto;
import com.sistefact.electronico.models.Cliente;


public interface ClienteService extends ICRUD<Cliente>{
	public Cliente findByNombreAndApellido(String nombre,String apellido);
	public Cliente getClienteByDni(String dni);
	public Cliente getClienteByRUC(String ruc);
	List<Cliente> getCustomersByUser(Long id);
}
