package com.ardakkan.backend.dto;

import com.ardakkan.backend.entity.UserRoles; // UserRoles Enum'ını import ediyoruz
import java.util.List;

public class UserDTO {
    private Long id;
    private String FirstName;
    private String LastName;
    private String email;
    private UserRoles role; // Kullanıcı rolü
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserRoles getRole() {
		return role;
	}
	public void setRole(UserRoles role) {
		this.role = role;
	}
    
}
