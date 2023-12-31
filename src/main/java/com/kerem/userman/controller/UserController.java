package com.kerem.userman.controller;

import java.util.List;

import javax.validation.Valid;

import com.kerem.userman.model.Role;
import com.kerem.userman.model.User;
import com.kerem.userman.service.RoleService;
import com.kerem.userman.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/users")
public class UserController {
	
	
	private final UserService userService;
	private final RoleService roleService;
	
	@Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


	@GetMapping("/test")
    public String handler(Model model) {

        model.addAttribute("message", "Hi. This is a Test Page.");
        return "hello";
    }
	
	@GetMapping("/addUser")
    public String saveUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		List<Role> roles = roleService.getRoles();
		model.addAttribute("roles", roles);
        return "add-user";
    }
	
	@PostMapping("/addUser")
    public String saveUser(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
	        return "add-user";
	    }
		user.setRole(roleService.getRoleById(user.getRoleId()));
		boolean isAdded = userService.addUser(user);
        
        if (isAdded) {
        	return "redirect:/users/userList";
        }
        else {
        	model.addAttribute("errorMessage", "Failed to add user. Please try again.");
            return "add-user";
        } 
    }
    
	@GetMapping("/userList")
    public String listUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user-list";
    }
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUserById(@PathVariable("id") int id, Model model) {
        userService.deleteUser(id);
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
    	return "redirect:/users/userList";
    }
	
	@GetMapping("/updateUser/{id}")
	public String updateUserById(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
		List<Role> roles = roleService.getRoles();
		model.addAttribute("roles", roles);
    	return "edit-user";
    }
	
	@PostMapping("/updateUser")
	public String updateUser(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
	        return "edit-user";
	    }
		user.setRole(roleService.getRoleById(user.getRoleId()));
		boolean isUpdated = userService.updateUser(user);
        if (isUpdated) {
        	return "redirect:/users/userList";
        }
        else {
        	model.addAttribute("errorMessage", "Failed to add user. Please try again.");
            return "edit-user";
        } 
    }
}