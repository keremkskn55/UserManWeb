package com.kerem.userman.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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
        	return "token-loader";
        }
        else {
        	model.addAttribute("errorMessage", "Failed to login. Please try again.");
            return "login";
        } 
    }
	
	@PostMapping("/tokenChecker")
    public String processToken(@RequestBody String jwtToken) {
		authService.saveJwtTokenLocal(jwtToken);
		
        return "redirect:/users/addUser";
    }
}
