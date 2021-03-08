package com.sistefact.electronico.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistefact.electronico.models.UserCompany;

/**
 * Created by veljko on 17.9.18.
 */
@Repository
public interface UserCompanyRepository extends CrudRepository<UserCompany, Long> {

    UserCompany findByUser_Id(@Param("id") Long id);
}
