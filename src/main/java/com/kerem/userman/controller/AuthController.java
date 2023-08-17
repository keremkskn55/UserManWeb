package com.kerem.userman.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.kerem.userman.model.RegisterCredential;
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
    public String saveUser(HttpServletRequest request, @Valid SignInCredential signInCredential, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
	        return "login";
	    }
		
		String token = authService.login(signInCredential);
		
        if (token != null) {
        	HttpSession session = request.getSession();
    	    session.setAttribute("jwtToken", token);
        	return "redirect:/users/addUser";
        }
        else {
        	model.addAttribute("errorMessage", "Failed to login. Please try again.");
            return "login";
        } 
    }
	
	@GetMapping("/register")
    public String registerUser(Model model) {
		model.addAttribute("registerCredential", new RegisterCredential());
        return "register";
    }
	
	@PostMapping("/register")
    public String registerUser(HttpServletRequest request, @Valid RegisterCredential registerCredential, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
	        return "register";
	    }
		
		String token = authService.register(registerCredential);
		
        if (token != null) {
        	HttpSession session = request.getSession();
    	    session.setAttribute("jwtToken", token);
        	return "redirect:/users/addUser";
        }
        else {
        	model.addAttribute("errorMessage", "Failed to login. Please try again.");
            return "login";
        } 
    }
}
