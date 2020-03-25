package com.sistefact.electronico.service;



import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.sistefact.electronico.models.User;
import com.sistefact.electronico.models.UserCompany;

import javassist.NotFoundException;

/**
 * Created by veljko on 9.9.18.
 */
public interface UserService {

    User findUserByEmail(String email);

    

   
    void deleteUser(Long userId);

    Set<User> getAllUsers();
    User getUser(Long userId);

    

    UserCompany getUserCompany(Long userId);

    

    

    

   


   

}
