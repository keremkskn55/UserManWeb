package com.kerem.userman.model;

import org.hibernate.validator.constraints.NotBlank;

public class SignInCredential {
	@NotBlank(message = "Please enter your email")
	private String email;
	@NotBlank(message = "Please enter your password")
	private String password;
	
	
	
	public SignInCredential() {}
	public SignInCredential(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
