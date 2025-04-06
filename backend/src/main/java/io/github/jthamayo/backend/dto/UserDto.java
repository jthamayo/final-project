package io.github.jthamayo.backend.dto;

public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    ////////////////// GETTERS&SETTERS///////////////////////

    public Long getId() {
	return this.id;
    }

    public String getFirstName() {
	return this.firstName;
    }

    public String getLastName() {
	return this.lastName;
    }

    public String getEmail() {
	return this.email;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    //////////////// CONSTRUCTOR////////////////

    public UserDto() {
	this.firstName = "Unknown";
	this.lastName = "Unknown";
	this.email = "Unknown";
    }

    public UserDto(String firstName, String lastName, String email) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
    }

    public UserDto(Long id, String firstName, String lastName, String email) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
    }

}
