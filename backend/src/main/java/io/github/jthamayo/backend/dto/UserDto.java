package io.github.jthamayo.backend.dto;

public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String number;
    private Long groupId;

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

    public String getNumber() {
	return this.number;
    }

    public Long getGroupId() {
	return this.groupId;
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

    public void setNumber(String number) {
	this.number = number;
    }

    public void setGroupId(Long groupId) {
	this.groupId = groupId;
    }

    //////////////// CONSTRUCTOR////////////////

    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String email, String number) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.number = number;

    }

    public UserDto(Long id, String firstName, String lastName, String email, String number, Long groupId) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.number = number;
	this.groupId = groupId;
    }

}
