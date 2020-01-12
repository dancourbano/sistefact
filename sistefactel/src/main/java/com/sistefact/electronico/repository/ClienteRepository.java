package com.sistefact.electronico.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sistefact.electronico.models.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByNombreAndApellido(String nombre,String Apellido);

    
}
