package io.github.jthamayo.backend.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.github.jthamayo.backend.entity.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")

public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs;

    @OneToOne(optional = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "home_address_id")
    private Address homeAddress;

    @OneToOne(optional = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column
    private Boolean isVerified;

    ////////////////// GETTERS&SETTERS///////////////////////

    public Long getId() {
	return this.id;
    }

    public String getphoneNumber() {
	return phoneNumber;
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

    public List<Job> getJobs() {
	return this.jobs;
    }

    public String getEmail() {
	return this.email;
    }

    public Address getHomeAddress() {
	return homeAddress;
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

    public void setphoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public void setGroup(Group group) {
	this.group = group;
    }

    public void setJobs(List<Job> jobs) {
	this.jobs = jobs;
    }

    public void setHomeAddress(Address homeAddress) {
	this.homeAddress = homeAddress;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String userName) {
	this.username = userName;
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

    public Set<Role> getRoles() {
	return roles;
    }

    public void setRoles(Set<Role> roles) {
	this.roles = roles;
    }

    public Boolean isVerified() {
	return isVerified;
    }

    public void setVerified(Boolean isVerified) {
	this.isVerified = isVerified;
    }

    public Vehicle getVehicle() {
	return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
	this.vehicle = vehicle;
    }

    //////////////// CONSTRUCTOR////////////////

    public User() {

    }

    public User(String firstName, String lastName, String username, String email, String phoneNumber,
	    Boolean isVerified) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.username = username;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.isVerified = isVerified;

    }

    public User(Long id, String firstName, String lastName, String username, String email, String phoneNumber,
	    Boolean isVerified) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.username = username;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.isVerified = isVerified;

    }

    public User(Long id, String firstName, String lastName, String username, String email, String phoneNumber,
	    Group group, List<Job> jobs, Address homeAddress, Vehicle vehicle, Boolean isVerified, Set<Role> roles) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.username = username;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.group = group;
	this.jobs = jobs;
	this.homeAddress = homeAddress;
	this.vehicle = vehicle;
	this.isVerified = isVerified;
	this.roles = roles;
    }

}
