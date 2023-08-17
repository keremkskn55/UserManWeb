package com.kerem.userman.service;

import java.util.List;

import com.kerem.userman.model.Role;

import org.springframework.stereotype.Service;

@Service
public interface RoleService {
	List<Role> getRoles();
	boolean addRole(Role role);
	boolean deleteRole(int id);
	Role getRoleById(int id);
	boolean updateRole(Role role);
}
