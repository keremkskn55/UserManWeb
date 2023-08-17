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
@RequestMapping("/roles")
public class RoleController {
	
	private final RoleService roleService;
	
	@Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
	
	@GetMapping("/test")
    public String handler(Model model) {

        model.addAttribute("message", "Hi. This is a Test Page.");
        return "hello";
    }
	
	@GetMapping("/addRole")
    public String saveUser(Model model) {
		model.addAttribute("role", new Role());
        return "add-role";
    }
	
	@PostMapping("/addRole")
    public String saveUser(@Valid Role role, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
	        return "add-role";
	    }
		
		boolean isAdded = roleService.addRole(role);
        
        if (isAdded) {
        	return "redirect:/roles/roleList";
        }
        else {
        	model.addAttribute("errorMessage", "Failed to add role. Please try again.");
            return "add-role";
        } 
    }
    
	@GetMapping("/roleList")
    public String listUsers(Model model) {
        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);
        return "role-list";
    }
	
	@GetMapping("/deleteRole/{id}")
	public String deleteUserById(@PathVariable("id") int id, Model model) {
		roleService.deleteRole(id);
        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);
    	return "redirect:/roles/roleList";
    }
	
	@GetMapping("/updateRole/{id}")
	public String updateUserById(@PathVariable("id") int id, Model model) {
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
    	return "edit-role";
    }
	
	@PostMapping("/updateRole")
	public String updateUser(@Valid Role role, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
	        return "edit-role";
	    }
		
		boolean isUpdated = roleService.updateRole(role);
        if (isUpdated) {
        	return "redirect:/roles/roleList";
        }
        else {
        	model.addAttribute("errorMessage", "Failed to edit role. Please try again.");
            return "edit-role";
        } 
    }

}
