package com.kerem.userman.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.NotBlank;

public class User {
	public int id;
	
	@NotBlank(message = "Please enter your username")
	private String username;
    
	@NotBlank(message = "Please enter your name")
	private String name;
    
	@NotBlank(message = "Please enter your surname")
	private String surname;
    
	@Min(value = 18, message = "Must bigger than 18")
	@Max(value = 110, message = "Must smaller than 110")
	private int age;
	
	public User() {}

	public User(String username, String name, String surname, int age) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.age = age;
	}

	public User(int id, String username, String name, String surname, int age) {
		super();
		this.id = id;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	
	
	
	
	

}
