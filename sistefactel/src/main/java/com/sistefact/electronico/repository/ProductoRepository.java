package com.sistefact.electronico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sistefact.electronico.models.Producto;



@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
	Iterable<Producto> findByUser_Id(@Param("id") Long id);
}
