package com.sistefact.electronico.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sistefact.electronico.models.User;

import java.util.Date;

/**
 * Created by veljko on 9.9.18.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String email);
}

