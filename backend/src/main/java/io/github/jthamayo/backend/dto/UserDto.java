package io.github.jthamayo.backend.dto;

import java.util.List;

public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String number;
    private Long groupId;
    private List<Long> jobIds;
    private Long homeAddressId;

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

    public List<Long> getJobIds() {
	return jobIds;
    }

    public Long getHomeAddressId() {
	return this.homeAddressId;
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

    public void setJobIds(List<Long> jobIds) {
	this.jobIds = jobIds;
    }

    public void setHomeAddressId(Long homeAddressId) {
	this.homeAddressId = homeAddressId;
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

    public UserDto(Long id, String firstName, String lastName, String email, String number, Long groupId,
	    List<Long> jobIds, Long homeAddressId) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.number = number;
	this.groupId = groupId;
	this.jobIds = jobIds;
	this.homeAddressId = homeAddressId;
    }

}
