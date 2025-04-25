package io.github.jthamayo.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @Column(nullable = false, unique = true)
    private String number;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    ////////////////// GETTERS&SETTERS///////////////////////

    public Long getId() {
	return this.id;
    }

    public String getNumber() {
	return number;
    }

    public Group getGroup() {
	return group;
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

    public void setNumber(String number) {
	this.number = number;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public void setGroup(Group group) {
	this.group = group;
    }

    //////////////// CONSTRUCTOR////////////////

    public User() {
	this.firstName = "Unknown";
	this.lastName = "Unknown";
	this.email = "Unknown";
	this.number = "Unknown";

    }

    public User(String firstName, String lastName, String email, String number) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.number = number;

    }

    public User(Long id, String firstName, String lastName, String email, String number) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.number = number;

    }

    public User(Long id, String firstName, String lastName, String email, String number, Group group) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.group = group;

    }

    // TODO do remember to override equals user by id and hash

}
