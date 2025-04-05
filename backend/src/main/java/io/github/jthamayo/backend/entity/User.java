package io.github.jthamayo.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(nullable = false, unique = true)
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

    public void serId(Long id) {
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

    public User() {
	this.firstName = "Unknown";
	this.lastName = "Unknown";
	this.email = "Unknown";
    }

    public User(Long id, String firstName, String lastName, String email) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
    }

}
