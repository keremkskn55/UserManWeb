package com.kerem.userman.service;

import java.util.List;

import com.kerem.userman.model.User;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	List<User> getUsers();
	boolean addUser(User user);
	boolean deleteUser(int id);
	User getUserById(int id);
	boolean updateUser(User user);
}
