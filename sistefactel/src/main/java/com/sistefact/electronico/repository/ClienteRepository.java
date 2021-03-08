package com.sistefact.electronico.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sistefact.electronico.models.Cliente;


@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    Cliente findByNombreAndApellido(String nombre,String Apellido);
    List<Cliente> findByUser_Id(@Param("id") Long id);
}
