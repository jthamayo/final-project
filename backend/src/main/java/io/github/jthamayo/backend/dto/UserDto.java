package io.github.jthamayo.backend.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {

    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Username is required")
    private String username;

    @Size(max = 40, message = "Email must be under 40 characters")
    @NotBlank(message = "Email is required")
    @Email(message = "Not a valid email format")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[+]?\\d{7,15}$", message = "Invalid phone number format")
    private String phoneNumber;

    private Long groupId;

    private List<Long> jobIds;

    private Long homeAddressId;

    private Long vehicleId;

    private Set<Long> roleIds = new HashSet<>();

    private Boolean isVerified;

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

    public void setGroupId(Long groupId) {
	this.groupId = groupId;
    }

    public void setJobIds(List<Long> jobIds) {
	this.jobIds = jobIds;
    }

    public void setHomeAddressId(Long homeAddressId) {
	this.homeAddressId = homeAddressId;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getPhoneNumber() {
	return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    public Set<Long> getRoleIds() {
	return roleIds;
    }

    public void setRoleIds(Set<Long> roleIds) {
	this.roleIds = roleIds;
    }

    public Boolean isVerified() {
	return isVerified;
    }

    public void setVerified(Boolean isVerified) {
	this.isVerified = isVerified;
    }

    public Long getVehicleId() {
	return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
	this.vehicleId = vehicleId;
    }

    //////////////// CONSTRUCTOR////////////////

    public UserDto() {
    }

    public UserDto(Long id, String firstName, String lastName, String username, String email, String phoneNumber,
	    Long groupId, List<Long> jobIds, Long homeAddressId, Long vehicleId, List<Long> roleIds,
	    Boolean isVerified) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.username = username;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.groupId = groupId;
	this.jobIds = jobIds;
	this.homeAddressId = homeAddressId;
	this.vehicleId = vehicleId;
	this.roleIds = new HashSet<>(roleIds);
	this.isVerified = isVerified;
    }

}
