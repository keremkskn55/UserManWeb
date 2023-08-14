package com.kerem.userman.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.NotBlank;

public class User {
	public int id;
	
	@NotBlank(message = "Please enter your email")
	private String email;
    
	@NotBlank(message = "Please enter your name")
	private String name;
    
	@NotBlank(message = "Please enter your surname")
	private String surname;
    
	@Min(value = 18, message = "Must bigger than 18")
	@Max(value = 110, message = "Must smaller than 110")
	private int age;
	
	public User() {}

	public User(String email, String name, String surname, int age) {
		super();
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.age = age;
	}

	public User(int id, String email, String name, String surname, int age) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.age = age;
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return String.format("{%s, %s, %s, %d}", this.email, this.name, this.surname, this.age);
	}

}
