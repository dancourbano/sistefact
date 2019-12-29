package com.sistefact.electronico.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistefact.electronico.models.Cliente;
import com.sistefact.electronico.repository.ClienteRepository;
import com.sistefact.electronico.service.ClienteService;

import java.util.List;
@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepo;



    @Override
    public Cliente getById(Long id) {
        return clienteRepo.getOne(id);
    }

    @Override
    public Cliente findByNombreAndApellido(String nombre,String apellido) {
        return clienteRepo.findByNombreAndApellido(nombre,apellido);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    @Override
    public Cliente update(Cliente cliente) {
        
        return clienteRepo.save(cliente);
    }

    @Override
    public void delete(Long id) {
        clienteRepo.deleteById(id);
    }

    @Override
    public List<Cliente> getAll() {
        return (List<Cliente>) clienteRepo.findAll();
    }
}
