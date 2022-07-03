package com.java.demo.services;

import com.java.demo.datastore.model.UserModel;
import com.java.demo.exceptions.DataException;

import java.util.List;
import java.util.Map;

public interface UserService {

    String introduce();

    List<?> fetchUsers();

    UserModel addUser(UserModel userModel) throws DataException;

}
