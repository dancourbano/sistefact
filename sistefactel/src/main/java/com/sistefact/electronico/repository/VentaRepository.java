package com.sistefact.electronico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.sistefact.electronico.models.Venta;


 

public interface VentaRepository extends JpaRepository<Venta,Long> {


}
