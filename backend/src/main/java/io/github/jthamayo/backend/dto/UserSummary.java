package io.github.jthamayo.backend.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.github.jthamayo.backend.entity.Job;

public class UserSummary {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public UserSummary(long id, String firstName, String lastName, String username, String email) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.username = username;
	this.email = email;
    }

    public UserSummary(String firstName, String lastName, String username, String email) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.username = username;
	this.email = email;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

}
