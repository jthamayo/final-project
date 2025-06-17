package io.github.jthamayo.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private Boolean isIrregular;
    private Boolean isNocturnal;

    ///////////////////////////// GETTERS&SETTERS//////////////////////////////

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Address getAddress() {
	return address;
    }

    public void setAddress(Address address) {
	this.address = address;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Boolean isIrregular() {
	return isIrregular;
    }

    public void setIrregular(Boolean isIrregular) {
	this.isIrregular = isIrregular;
    }

    public Boolean isNocturnal() {
	return isNocturnal;
    }

    public void setNocturnal(Boolean isNocturnal) {
	this.isNocturnal = isNocturnal;
    }

    ////////////////////// CONSTRUCTORS////////////////////////////
    public Job() {

    }

    public Job(Long id, Address address, User user, Boolean isIrregular, Boolean isNocturnal) {
	this.id = id;
	this.address = address;
	this.user = user;
	this.isIrregular = isIrregular;
	this.isNocturnal = isNocturnal;
    }

    public Job(Long id, Boolean isIrregular, Boolean isNocturnal) {
	this.id = id;
	this.isIrregular = isIrregular;
	this.isNocturnal = isNocturnal;
    }

}
