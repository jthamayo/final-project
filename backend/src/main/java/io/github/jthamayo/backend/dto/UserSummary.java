package io.github.jthamayo.backend.dto;

public class UserSummary {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePictureUrl;
    private Boolean hasPendingRequest;

    public UserSummary(Long id, String firstName, String lastName, String username, String email) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.username = username;
	this.email = email;
    }
    
    public UserSummary(String firstName, String lastName, String username, String email, String profilePictureUrl) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.username = username;
	this.email = email;
	this.profilePictureUrl = profilePictureUrl;
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

    public String getProfilePictureUrl() {
	return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
	this.profilePictureUrl = profilePictureUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getHasPendingRequest() {
        return hasPendingRequest;
    }

    public void setHasPendingRequest(Boolean hasPendingRequest) {
        this.hasPendingRequest = hasPendingRequest;
    }
    

}
