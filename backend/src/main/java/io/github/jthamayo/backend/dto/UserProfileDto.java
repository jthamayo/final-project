package io.github.jthamayo.backend.dto;

import java.util.List;

public class UserProfileDto {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private List<JobDto> jobs;
    private AddressDto address;
    private VehicleDto vehicle;
    private String profilePictureUrl;
    private Boolean isVerified;

    ////////////////////////////////////////////////////////////////////////

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
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

    public List<JobDto> getJobs() {
	return jobs;
    }

    public void setJobs(List<JobDto> jobs) {
	this.jobs = jobs;
    }

    public AddressDto getAddresses() {
	return address;
    }

    public void setAddresses(AddressDto address) {
	this.address = address;
    }

    public VehicleDto getVehicles() {
	return vehicle;
    }

    public void setVehicles(VehicleDto vehicle) {
	this.vehicle = vehicle;
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

    public String getProfilePictureUrl() {
	return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
	this.profilePictureUrl = profilePictureUrl;
    }

    public Boolean isVerified() {
	return isVerified;
    }

    public void setVerified(Boolean isVerified) {
	this.isVerified = isVerified;
    }

    ////////////////////////////////////////////////////////////////////////////////

    public UserProfileDto(Long id, String username, String email, String phoneNumber, String firstName, String lastName,
	    List<JobDto> jobs, AddressDto address, VehicleDto vehicle, String profilePictureUrl, Boolean isVerified) {
	this.id = id;
	this.username = username;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.firstName = firstName;
	this.lastName = lastName;
	this.jobs = jobs;
	this.address = address;
	this.vehicle = vehicle;
	this.profilePictureUrl = profilePictureUrl;
	this.isVerified = isVerified;
    }

    public UserProfileDto() {

    }
}
