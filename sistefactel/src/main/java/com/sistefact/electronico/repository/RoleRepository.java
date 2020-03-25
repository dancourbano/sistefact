package com.sistefact.electronico.repository;


import org.springframework.data.repository.CrudRepository;

import com.sistefact.electronico.models.Role;

/**
 * Created by veljko on 9.9.18.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}