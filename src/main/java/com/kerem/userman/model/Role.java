package com.kerem.userman.model;

import org.hibernate.validator.constraints.NotBlank;

public class Role {
    private int id;
    
    @NotBlank(message = "Please enter your role name")
	private String name;
    
	private boolean canUserAdd;
	private boolean canUserEdit;
	private boolean canUserDelete;

	public Role() {}
	
	public Role(String name, boolean canUserAdd, boolean canUserEdit, boolean canUserDelete) {
		this.name = name;
		this.canUserAdd = canUserAdd;
		this.canUserEdit = canUserEdit;
		this.canUserDelete = canUserDelete;
	}

	public Role(int id, String name, boolean canUserAdd, boolean canUserEdit, boolean canUserDelete) {
		this.id = id;
		this.name = name;
		this.canUserAdd = canUserAdd;
		this.canUserEdit = canUserEdit;
		this.canUserDelete = canUserDelete;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCanUserAdd() {
		return canUserAdd;
	}

	public void setCanUserAdd(boolean canUserAdd) {
		this.canUserAdd = canUserAdd;
	}

	public boolean isCanUserEdit() {
		return canUserEdit;
	}

	public void setCanUserEdit(boolean canUserEdit) {
		this.canUserEdit = canUserEdit;
	}

	public boolean isCanUserDelete() {
		return canUserDelete;
	}

	public void setCanUserDelete(boolean canUserDelete) {
		this.canUserDelete = canUserDelete;
	}
	
	
	
	
}
