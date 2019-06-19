package com.luo.dao;

import com.luo.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    User getUser(String id);

    boolean addUser(User userModel);

    boolean updateUser(String id, String name);

    boolean deleteUser(String id);
}
