package com.sistefact.electronico.service.impl;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistefact.electronico.models.Invoice;
import com.sistefact.electronico.models.Role;
import com.sistefact.electronico.models.User;
import com.sistefact.electronico.models.UserCompany;
import com.sistefact.electronico.repository.RoleRepository;
import com.sistefact.electronico.repository.UserCompanyRepository;
import com.sistefact.electronico.repository.UserRepository;
import com.sistefact.electronico.service.UserService;

import javassist.NotFoundException;

import java.util.*;

/**
 * Created by veljko on 9.9.18.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserCompanyRepository userCompanyRepository;

    

    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByUsername(email);
    }

    

    

    @Override
    public User getUser(Long userId) {

        Optional<User> userOptional = userRepository.findById(userId);

        if (!userOptional.isPresent()) {
            try {
				throw new NotFoundException("User not found!");
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return userOptional.get();
    }

    

    @Override
    public void deleteUser(Long userId) {

        userRepository.deleteById(userId);
    }

    @Override
    public Set<User> getAllUsers() {

        Set<User> users = new HashSet<>();
        userRepository.findAll().iterator().forEachRemaining(users :: add);

        return users;
    }

   

    @Override
    public UserCompany getUserCompany(Long companyId) {
    	 Optional<UserCompany> userCompany = userCompanyRepository.findById(companyId);

         if(!userCompany.isPresent()) {
             try {
 				throw new NotFoundException("serCompany Not Found. For ID value: " + companyId.toString() );
 			} catch (NotFoundException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
         }

         return userCompany.get();
         
    }
}
