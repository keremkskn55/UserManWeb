package com.kerem.userman.controller;

import java.util.List;

import javax.validation.Valid;

import com.kerem.userman.model.SignInCredential;
import com.kerem.userman.model.User;
import com.kerem.userman.service.AuthService;
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
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService authService;
	
	@Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
	
	@GetMapping("/test")
    public String handler(Model model) {

        model.addAttribute("message", "Hi. This is a Test Page For Auth.");
        return "hello";
    }
	
	@GetMapping("/signIn")
    public String saveUser(Model model) {
		model.addAttribute("signInCredential", new SignInCredential());
        return "login";
    }
	
	@PostMapping("/signIn")
    public String saveUser(@Valid SignInCredential signInCredential, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
	        return "login";
	    }
		
		String token = authService.login(signInCredential);
        
        if (token != null) {
        	model.addAttribute("jwtToken", token);
        	return "login-loading";
        }
        else {
        	model.addAttribute("errorMessage", "Failed to login. Please try again.");
            return "login";
        } 
    }
}
