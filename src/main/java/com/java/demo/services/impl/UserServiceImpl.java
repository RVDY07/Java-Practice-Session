package com.java.demo.services.impl;

import com.java.demo.datastore.model.UserModel;
import com.java.demo.datastore.repositories.UserRepository;
import com.java.demo.exceptions.DataException;
import com.java.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Value("${spring.application.name}")
    private String name;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String introduce() {
        return name;
    }

    @Override
    public List<?> fetchUsers() {
       return userRepository.findAll();
    }

    @Override
    public UserModel addUser(UserModel userModel) throws DataException {
        return userRepository.save(userModel);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserModel> userModelOptional = userRepository.findByUserName(username);

        if(!userModelOptional.isPresent()){
            return null;
        }

        return new User(userModelOptional.get().getUserName(),
                        userModelOptional.get().getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority(userModelOptional.get().getRoles())));
    }
}
