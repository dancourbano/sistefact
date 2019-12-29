package com.sistefact.electronico.repository;


import org.springframework.data.repository.CrudRepository;
import com.sistefact.electronico.models.Cuenta;

public interface CuentaRepository  extends CrudRepository<Cuenta, Integer> {
    Cuenta findByUsuario(String usauario);
}