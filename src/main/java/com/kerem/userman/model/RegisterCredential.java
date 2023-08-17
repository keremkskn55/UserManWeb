package com.kerem.userman.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class RegisterCredential {
	@NotBlank(message = "Please enter your email")
	private String email;
	@NotBlank(message = "Please enter your name")
	private String name;
	@NotBlank(message = "Please enter your surname")
	private String surname;
	@Min(value = 18, message = "Must bigger than 18")
	@Max(value = 110, message = "Must smaller than 110")
	private int age;
	@NotBlank(message = "Please enter your password")
	private String password;
	
	public RegisterCredential() {}

	public RegisterCredential(String email, String name, String surname, int age, String password) {
		super();
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
